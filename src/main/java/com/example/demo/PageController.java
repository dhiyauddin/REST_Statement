package com.example.demo;

import java.io.OutputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.manager.StatusManagerServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.table.Financial_Detail;

import com.example.table.User_Detail;

@Controller
public class PageController extends PageService {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/RegisterForm")
	public String registerPage(Map<String, Object> model) {

		model.put("message", this.message);
		return "RegisterForm";
	}

	@RequestMapping("/registerProcess")
	public String registerProcess(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("uname") String username, @RequestParam("psw") String password,
			@RequestParam("c_contact_no") String c_contact_no) {
		String page = "";
		User_Detail el = new User_Detail();
		el.setUser_id(username);
		el.setRole_id(2);
		el.setCompany_contact_no(c_contact_no);
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		el.setDate(date);

		int result = 0;
		result = registerUser(el);
		if (result > 0) {
			model.put("messageSuccess", "Successful Register Account!");
			page = "RegisterSuccess";
		} else {
			model.put("messageFailure", "Unsuccessful Register Account! Please register again.");
			page = "RegisterFail";
		}

		return page;
	}

	@RequestMapping({ "/", "Login" })
	public String loginPage(Map<String, Object> model) {

		model.put("message", this.message);
		return "Login";
	}

	@RequestMapping("/loginProcess")
	public String loginProcess(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("uname") String username, @RequestParam("psw") String password) {
		String page = "";
		Login login = new Login();
		login.setUsername(username);
		login.setPassword(password);
		List<User_Detail> user = validateUser(login);
		String userId = "";
		if (null != user && !user.isEmpty()) {
			userId = user.get(0).getUser_id();
			request.getSession().setAttribute("loginUser", userId);
			model.put("message", this.message);
			page = "Home";
		} else {
			model.put("message", this.message);
			page = "Login";
		}

		return page;
	}

	@RequestMapping("/logoutProcess")
	public String logoutPage(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		model.put("message", this.message);
		return "Login";
	}

	@RequestMapping("/Home")
	public String home(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		String create_by = "";
		create_by = (String) request.getSession().getAttribute("loginUser");
		model.put("message", this.message);
		return "Home";
	}

	@RequestMapping("/Financial")
	public String financial(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		String create_by = "";
		create_by = (String) request.getSession().getAttribute("loginUser");
		List<Financial_Detail> dataList = new ArrayList<Financial_Detail>();
		dataList = financialDataList(create_by);
		model.put("financialList", dataList);
		return "Financial";
	}

	@RequestMapping("/FinancialForm")
	public String financialForm(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		String create_by = "";
		create_by = (String) request.getSession().getAttribute("loginUser");
		model.put("message", this.message);
		return "FinancialForm";
	}

	@RequestMapping(value = "/FinancialSubmit", method = RequestMethod.GET)
	public String financialSubmit(Map<String, Object> model, @RequestParam("financial_detail") String financial_detail,
			@RequestParam("financial_myr") double financial_myr,
			@RequestParam("financial_transaction_type") String financial_transaction_type,
			@RequestParam("financial_transaction_date") String financial_transaction_date, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String create_by = "";
			create_by = (String) request.getSession().getAttribute("loginUser");
			Financial_Detail e1 = new Financial_Detail();
			Date fn_trans_date = java.sql.Date.valueOf(financial_transaction_date);
			e1.setFn_trans_date(fn_trans_date);
			e1.setFn_detail(financial_detail);
			e1.setFn_myr(financial_myr);
			e1.setFn_trans_type(financial_transaction_type);
			e1.setCreate_by(create_by);
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			e1.setFn_date(date);
			String result = "";
			result = financialSubmitData(e1);
			model.put("message", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "FinancialForm";
	}

	@RequestMapping("/FinancialDownload")
	public String financialDownloadList(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		String create_by = "";
		create_by = (String) request.getSession().getAttribute("loginUser");

		try {
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"Financials.pdf\""));
			OutputStream out = response.getOutputStream();
			// get total_debit,total_credit and balance and pass as parameter;
			// total_debit
			String debit = "Debit";
			String credit = "Credit";
			DecimalFormat df = new DecimalFormat("####0.00");

			double total_debit = getTotalByType(create_by, debit);
			double total_credit = getTotalByType(create_by, credit);
			double balance = total_debit - total_credit;

			String total_debit_str = String.valueOf(df.format(total_debit));
			String total_credit_str = String.valueOf(df.format(total_credit));
			String balance_str = String.valueOf(df.format(balance));

			createFinancialPdfReport(financialDataList(create_by), out, create_by, total_debit_str, total_credit_str,
					balance_str);
		} catch (Exception e) {
			System.out.println(e);
		}
		model.put("message", this.message);
		return "Financial";
	}
}
