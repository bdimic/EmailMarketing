package com.aquest.emailmarketing.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cm_bounce_email")
public class BouncedEmail {
	@Id 
    @GeneratedValue
    @Column(name = "id")
    private long id;
	private String email_address;
	private String bounce_reason;
	private Date send_date;
	
	public BouncedEmail() {
		
	}

	public BouncedEmail(long id, String email_address, String bounce_reason, Date send_date) {
		super();
		this.id = id;
		this.email_address = email_address;
		this.bounce_reason = bounce_reason;
		this.send_date = send_date;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getBounce_reason() {
		return bounce_reason;
	}

	public void setBounce_reason(String bounce_reason) {
		this.bounce_reason = bounce_reason;
	}

	public Date getSend_date() {
		return send_date;
	}

	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	@Override
	public String toString() {
		return "BouncedEmail [id=" + id + ", email_address=" + email_address + ", bounce_reason=" + bounce_reason
				+ ", send_date=" + send_date + "]";
	}
}
