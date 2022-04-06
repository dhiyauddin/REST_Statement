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
@Table(name = "user_detail")
public class User_Detail implements Serializable {

	private static final long serialVersionUID = -4315871523259449596L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "user_detail_id")
	private int user_detail_id;

	@Column(name = "user_id")
	private String user_id;

	@Column(name = "role_id")
	private int role_id;

	@Column(name = "company_contact_no")
	private String company_contact_no;

	@Column(name = "create_by")
	private String create_by;

	@Column(name = "date")
	private Date date;

	public int getUser_detail_id() {
		return user_detail_id;
	}

	public void setUser_detail_id(int user_detail_id) {
		this.user_detail_id = user_detail_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCompany_contact_no() {
		return company_contact_no;
	}

	public void setCompany_contact_no(String company_contact_no) {
		this.company_contact_no = company_contact_no;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

}
