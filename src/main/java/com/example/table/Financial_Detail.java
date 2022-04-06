package com.example.table;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financial_detail")
public class Financial_Detail implements Serializable {

	private static final long serialVersionUID = -8455289127550638706L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "fn_detail_id")
	private int fn_detail_id;

	@Column(name = "fn_myr")
	private double fn_myr;

	@Column(name = "fn_detail")
	private String fn_detail;

	@Column(name = "fn_trans_type")
	private String fn_trans_type;

	@Column(name = "create_by")
	private String create_by;

	@Column(name = "fn_trans_date")
	private Date fn_trans_date;

	@Column(name = "fn_date")
	private Date fn_date;

	public int getFn_detail_id() {
		return fn_detail_id;
	}

	public void setFn_detail_id(int fn_detail_id) {
		this.fn_detail_id = fn_detail_id;
	}

	public double getFn_myr() {
		return fn_myr;
	}

	public void setFn_myr(double fn_myr) {
		this.fn_myr = fn_myr;
	}

	public String getFn_detail() {
		return fn_detail;
	}

	public void setFn_detail(String fn_detail) {
		this.fn_detail = fn_detail;
	}

	public String getFn_trans_type() {
		return fn_trans_type;
	}

	public void setFn_trans_type(String fn_trans_type) {
		this.fn_trans_type = fn_trans_type;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public Date getFn_date() {
		return fn_date;
	}

	public void setFn_date(Date fn_date) {
		this.fn_date = fn_date;
	}

	public Date getFn_trans_date() {
		return fn_trans_date;
	}

	public void setFn_trans_date(Date fn_trans_date) {
		this.fn_trans_date = fn_trans_date;
	}
}
