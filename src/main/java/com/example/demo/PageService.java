package com.example.demo;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import com.example.table.Financial_Detail;
import com.example.table.User_Detail;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PageService {

	protected List<Financial_Detail> financialDataList(String create_by) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		String hql = "from com.example.table.Financial_Detail where create_by='" + create_by + "'";
		Query query = session.createQuery(hql);
		List results = query.list();
		t.commit();
		System.out.println("successfully executed");
		factory.close();
		session.close();

		List<Financial_Detail> data = new ArrayList<Financial_Detail>();
		data = results;
		return data;
	}

	protected double getTotalByType(String create_by, String type) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		String hql = "from com.example.table.Financial_Detail where create_by='" + create_by + "'"
				+ " and fn_trans_type='" + type + "'";
		Query query = session.createQuery(hql);
		List results = query.list();
		t.commit();
		System.out.println("successfully executed");
		factory.close();
		session.close();

		List<Financial_Detail> data = new ArrayList<Financial_Detail>();
		data = results;

		double totalByType = 0.00;
		DecimalFormat df = new DecimalFormat("####0.00");

		for (Financial_Detail p : data) {
			totalByType = totalByType + p.getFn_myr();
		}
		return totalByType;
	}

	/*
	 * protected List<Financial_Detail> financialDataListByTransactionType(String
	 * financial_transaction_type, String create_by) { StandardServiceRegistry ssr =
	 * new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	 * Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
	 * 
	 * SessionFactory factory = meta.getSessionFactoryBuilder().build(); Session
	 * session = factory.openSession(); Transaction t = session.beginTransaction();
	 * 
	 * List crit = session.createCriteria(Financial_Detail.class)
	 * .add(Restrictions.eq("fn_trans_type", new
	 * Integer(financial_transaction_type))) .add(Restrictions.eq("create_by", new
	 * String(create_by))).list();
	 * 
	 * t.commit(); System.out.println("successfully executed"); factory.close();
	 * session.close();
	 * 
	 * List<Financial_Detail> data = new ArrayList<Financial_Detail>(); data = crit;
	 * return data; }
	 */

	protected String financialSubmitData(Financial_Detail data) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		session.save(data);
		t.commit();
		System.out.println("successfully saved");
		factory.close();
		session.close();

		return "Successfully Saved.";
	}

	protected void createFinancialPdfReport(List<Financial_Detail> financialDataList, OutputStream out,
			String created_by, String Total_Debit, String Total_Credit, String Balance) throws Exception {
		// Fetching the .jrxml file from the resources folder.
		final InputStream stream = this.getClass().getResourceAsStream("/Financials.jrxml");

		// Compile the Jasper report from .jrxml to .japser
		final JasperReport report = JasperCompileManager.compileReport(stream);

		// Fetching the employees from the data source.
		final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(financialDataList);

		// Adding the additional parameters to the pdf.
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("CreatedBy", created_by);
		parameters.put("Total_Debit", Total_Debit);
		parameters.put("Total_Credit", Total_Credit);
		parameters.put("Balance", Balance);

		// Filling the report with the employee data and additional parameters
		// information.
		final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

		// Users can change as per their project requrirements or can take it as request
		// input requirement.
		// For simplicity, this tutorial will automatically place the file under the
		// "c:" drive.
		// If users want to download the pdf file on the browser, then they need to use
		// the "Content-Disposition" technique.
		final String filePath = "\\";
		// Export the report to a PDF file.

		// JasperExportManager.exportReportToPdfFile(print, filePath + "Invoice.pdf");
		JasperExportManager.exportReportToPdfStream(print, out);
	}

	protected List<User_Detail> validateUser(Login login) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		List crit = session.createCriteria(User_Detail.class)
				.add(Restrictions.eq("user_id", new String(login.getUsername()))).list();

		t.commit();
		System.out.println("successfully executed");
		factory.close();
		session.close();

		List<User_Detail> data = new ArrayList<User_Detail>();
		data = crit;
		return data;

	}

	protected int registerUser(User_Detail data) {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		session.save(data);
		t.commit();
		System.out.println("successfully saved");

		factory.close();
		session.close();

		return 1;
	}

}
