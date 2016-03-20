/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailList.
 */
@Entity
@Table(name="cm_email_broadcast_list")
public class EmailList implements Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private long id;
	
	/** The broadcast_id. */
	private String broadcast_id;
	
	/** The email. */
	private String email;
	
	/** The status. */
	private String status;
	
	/** The process_dttm. */
	private Timestamp process_dttm;
	
	/** The name1. */
	private String name1;
	
	/** The value1. */
	private String value1;
	
	/** The name2. */
	private String name2;
	
	/** The value2. */
	private String value2;
	
	/** The name3. */
	private String name3;
	
	/** The value3. */
	private String value3;
	
	/** The name4. */
	private String name4;
	
	/** The value4. */
	private String value4;
	
	/** The name5. */
	private String name5;
	
	/** The value5. */
	private String value5;
	
	/** The name6. */
	private String name6;
	
	/** The value6. */
	private String value6;
	
	/** The name7. */
	private String name7;
	
	/** The value7. */
	private String value7;
	
	/** The name8. */
	private String name8;
	
	/** The value8. */
	private String value8;
	
	/** The name9. */
	private String name9;
	
	/** The value9. */
	private String value9;
	
	/** The name10. */
	private String name10;
	
	/** The value10. */
	private String value10;	
	
	/**
	 * Instantiates a new email list.
	 */
	public EmailList() {

	}
	
	/**
	 * Instantiates a new email list.
	 *
	 * @param id the id
	 * @param broadcast_id the broadcast_id
	 * @param email the email
	 * @param status the status
	 * @param process_dttm the process_dttm
	 * @param name1 the name1
	 * @param value1 the value1
	 * @param name2 the name2
	 * @param value2 the value2
	 * @param name3 the name3
	 * @param value3 the value3
	 * @param name4 the name4
	 * @param value4 the value4
	 * @param name5 the name5
	 * @param value5 the value5
	 * @param name6 the name6
	 * @param value6 the value6
	 * @param name7 the name7
	 * @param value7 the value7
	 * @param name8 the name8
	 * @param value8 the value8
	 * @param name9 the name9
	 * @param value9 the value9
	 * @param name10 the name10
	 * @param value10 the value10
	 */
	public EmailList(long id, String broadcast_id, String email, String status, 
			Timestamp process_dttm, String name1,
			String value1, String name2, String value2, String name3,
			String value3, String name4, String value4, String name5,
			String value5, String name6, String value6, String name7,
			String value7, String name8, String value8, String name9,
			String value9, String name10, String value10) {
		this.id = id;
		this.broadcast_id = broadcast_id;
		this.email = email;
		this.status = status;
		this.process_dttm = process_dttm;
		this.name1 = name1;
		this.value1 = value1;
		this.name2 = name2;
		this.value2 = value2;
		this.name3 = name3;
		this.value3 = value3;
		this.name4 = name4;
		this.value4 = value4;
		this.name5 = name5;
		this.value5 = value5;
		this.name6 = name6;
		this.value6 = value6;
		this.name7 = name7;
		this.value7 = value7;
		this.name8 = name8;
		this.value8 = value8;
		this.name9 = name9;
		this.value9 = value9;
		this.name10 = name10;
		this.value10 = value10;
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
	 * Gets the broadcast_id.
	 *
	 * @return the broadcast_id
	 */
	public String getBroadcast_id() {
		return broadcast_id;
	}
	
	/**
	 * Sets the broadcast_id.
	 *
	 * @param broadcast_id the new broadcast_id
	 */
	public void setBroadcast_id(String broadcast_id) {
		this.broadcast_id = broadcast_id;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the process_dttm
	 */
	public Timestamp getProcess_dttm() {
		return process_dttm;
	}

	/**
	 * @param process_dttm the process_dttm to set
	 */
	public void setProcess_dttm(Timestamp process_dttm) {
		this.process_dttm = process_dttm;
	}

	/**
	 * Gets the name1.
	 *
	 * @return the name1
	 */
	public String getName1() {
		return name1;
	}
	
	/**
	 * Sets the name1.
	 *
	 * @param name1 the new name1
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}
	
	/**
	 * Gets the value1.
	 *
	 * @return the value1
	 */
	public String getValue1() {
		return value1;
	}
	
	/**
	 * Sets the value1.
	 *
	 * @param value1 the new value1
	 */
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	
	/**
	 * Gets the name2.
	 *
	 * @return the name2
	 */
	public String getName2() {
		return name2;
	}
	
	/**
	 * Sets the name2.
	 *
	 * @param name2 the new name2
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}
	
	/**
	 * Gets the value2.
	 *
	 * @return the value2
	 */
	public String getValue2() {
		return value2;
	}
	
	/**
	 * Sets the value2.
	 *
	 * @param value2 the new value2
	 */
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	/**
	 * Gets the name3.
	 *
	 * @return the name3
	 */
	public String getName3() {
		return name3;
	}
	
	/**
	 * Sets the name3.
	 *
	 * @param name3 the new name3
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}
	
	/**
	 * Gets the value3.
	 *
	 * @return the value3
	 */
	public String getValue3() {
		return value3;
	}
	
	/**
	 * Sets the value3.
	 *
	 * @param value3 the new value3
	 */
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	
	/**
	 * Gets the name4.
	 *
	 * @return the name4
	 */
	public String getName4() {
		return name4;
	}
	
	/**
	 * Sets the name4.
	 *
	 * @param name4 the new name4
	 */
	public void setName4(String name4) {
		this.name4 = name4;
	}
	
	/**
	 * Gets the value4.
	 *
	 * @return the value4
	 */
	public String getValue4() {
		return value4;
	}
	
	/**
	 * Sets the value4.
	 *
	 * @param value4 the new value4
	 */
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	
	/**
	 * Gets the name5.
	 *
	 * @return the name5
	 */
	public String getName5() {
		return name5;
	}
	
	/**
	 * Sets the name5.
	 *
	 * @param name5 the new name5
	 */
	public void setName5(String name5) {
		this.name5 = name5;
	}
	
	/**
	 * Gets the value5.
	 *
	 * @return the value5
	 */
	public String getValue5() {
		return value5;
	}
	
	/**
	 * Sets the value5.
	 *
	 * @param value5 the new value5
	 */
	public void setValue5(String value5) {
		this.value5 = value5;
	}
	
	/**
	 * Gets the name6.
	 *
	 * @return the name6
	 */
	public String getName6() {
		return name6;
	}
	
	/**
	 * Sets the name6.
	 *
	 * @param name6 the new name6
	 */
	public void setName6(String name6) {
		this.name6 = name6;
	}
	
	/**
	 * Gets the value6.
	 *
	 * @return the value6
	 */
	public String getValue6() {
		return value6;
	}
	
	/**
	 * Sets the value6.
	 *
	 * @param value6 the new value6
	 */
	public void setValue6(String value6) {
		this.value6 = value6;
	}
	
	/**
	 * Gets the name7.
	 *
	 * @return the name7
	 */
	public String getName7() {
		return name7;
	}
	
	/**
	 * Sets the name7.
	 *
	 * @param name7 the new name7
	 */
	public void setName7(String name7) {
		this.name7 = name7;
	}
	
	/**
	 * Gets the value7.
	 *
	 * @return the value7
	 */
	public String getValue7() {
		return value7;
	}
	
	/**
	 * Sets the value7.
	 *
	 * @param value7 the new value7
	 */
	public void setValue7(String value7) {
		this.value7 = value7;
	}
	
	/**
	 * Gets the name8.
	 *
	 * @return the name8
	 */
	public String getName8() {
		return name8;
	}
	
	/**
	 * Sets the name8.
	 *
	 * @param name8 the new name8
	 */
	public void setName8(String name8) {
		this.name8 = name8;
	}
	
	/**
	 * Gets the value8.
	 *
	 * @return the value8
	 */
	public String getValue8() {
		return value8;
	}
	
	/**
	 * Sets the value8.
	 *
	 * @param value8 the new value8
	 */
	public void setValue8(String value8) {
		this.value8 = value8;
	}
	
	/**
	 * Gets the name9.
	 *
	 * @return the name9
	 */
	public String getName9() {
		return name9;
	}
	
	/**
	 * Sets the name9.
	 *
	 * @param name9 the new name9
	 */
	public void setName9(String name9) {
		this.name9 = name9;
	}
	
	/**
	 * Gets the value9.
	 *
	 * @return the value9
	 */
	public String getValue9() {
		return value9;
	}
	
	/**
	 * Sets the value9.
	 *
	 * @param value9 the new value9
	 */
	public void setValue9(String value9) {
		this.value9 = value9;
	}
	
	/**
	 * Gets the name10.
	 *
	 * @return the name10
	 */
	public String getName10() {
		return name10;
	}
	
	/**
	 * Sets the name10.
	 *
	 * @param name10 the new name10
	 */
	public void setName10(String name10) {
		this.name10 = name10;
	}
	
	/**
	 * Gets the value10.
	 *
	 * @return the value10
	 */
	public String getValue10() {
		return value10;
	}
	
	/**
	 * Sets the value10.
	 *
	 * @param value10 the new value10
	 */
	public void setValue10(String value10) {
		this.value10 = value10;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailList [id=" + id + ", broadcast_id=" + broadcast_id
				+ ", email=" + email + ", status=" + status + ", process_dttm=" + process_dttm 
				+ ", name1=" + name1 + ", value1=" + value1
				+ ", name2=" + name2 + ", value2=" + value2
				+ ", name3=" + name3 + ", value3=" + value3 + ", name4="
				+ name4 + ", value4=" + value4 + ", name5=" + name5
				+ ", value5=" + value5 + ", name6=" + name6 + ", value6="
				+ value6 + ", name7=" + name7 + ", value7=" + value7
				+ ", name8=" + name8 + ", value8=" + value8 + ", name9="
				+ name9 + ", value9=" + value9 + ", name10=" + name10
				+ ", value10=" + value10 + "]";
	}
}