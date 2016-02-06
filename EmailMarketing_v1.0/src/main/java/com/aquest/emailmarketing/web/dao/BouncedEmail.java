package com.aquest.emailmarketing.web.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class BouncedEmail.
 */
@Entity
@Table(name="cm_bounce_email")
public class BouncedEmail {
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
    private long id;
	
	/** The email_address. */
	private String email_address;
	
	/** The bounce_reason. */
	private String bounce_reason;
	
	/** The send_date. */
	private Date send_date;
	
	/**
	 * Instantiates a new bounced email.
	 */
	public BouncedEmail() {
		
	}

	/**
	 * Instantiates a new bounced email.
	 *
	 * @param id the id
	 * @param email_address the email_address
	 * @param bounce_reason the bounce_reason
	 * @param send_date the send_date
	 */
	public BouncedEmail(long id, String email_address, String bounce_reason, Date send_date) {
		super();
		this.id = id;
		this.email_address = email_address;
		this.bounce_reason = bounce_reason;
		this.send_date = send_date;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the email_address.
	 *
	 * @return the email_address
	 */
	public String getEmail_address() {
		return email_address;
	}

	/**
	 * Sets the email_address.
	 *
	 * @param email_address the new email_address
	 */
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	/**
	 * Gets the bounce_reason.
	 *
	 * @return the bounce_reason
	 */
	public String getBounce_reason() {
		return bounce_reason;
	}

	/**
	 * Sets the bounce_reason.
	 *
	 * @param bounce_reason the new bounce_reason
	 */
	public void setBounce_reason(String bounce_reason) {
		this.bounce_reason = bounce_reason;
	}

	/**
	 * Gets the send_date.
	 *
	 * @return the send_date
	 */
	public Date getSend_date() {
		return send_date;
	}

	/**
	 * Sets the send_date.
	 *
	 * @param send_date the new send_date
	 */
	public void setSend_date(Date send_date) {
		this.send_date = send_date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BouncedEmail [id=" + id + ", email_address=" + email_address + ", bounce_reason=" + bounce_reason
				+ ", send_date=" + send_date + "]";
	}
}
