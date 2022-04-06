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
@Table(name = "role_detail")
public class Role_Detail implements Serializable {

	private static final long serialVersionUID = -9064062146161587763L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "role_detail_id")
	private int role_detail_id;

	@Column(name = "description")
	private String description;

	@Column(name = "create_by")
	private String create_by;

	@Column(name = "date")
	private Date date;

	public int getRole_detail_id() {
		return role_detail_id;
	}

	public void setRole_detail_id(int role_detail_id) {
		this.role_detail_id = role_detail_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

}
