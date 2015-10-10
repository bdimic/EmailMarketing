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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bdimic
 */
@Entity
@Table(name="cm_email_broadcast_list")
public class EmailList implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private long id;
	private String broadcast_id;
	private String email;
	private String name1;
	private String value1;
	private String name2;
	private String value2;
	private String name3;
	private String value3;
	private String name4;
	private String value4;
	private String name5;
	private String value5;
	private String name6;
	private String value6;
	private String name7;
	private String value7;
	private String name8;
	private String value8;
	private String name9;
	private String value9;
	private String name10;
	private String value10;	
	
	public EmailList() {

	}
	
	public EmailList(long id, String broadcast_id, String email, String name1,
			String value1, String name2, String value2, String name3,
			String value3, String name4, String value4, String name5,
			String value5, String name6, String value6, String name7,
			String value7, String name8, String value8, String name9,
			String value9, String name10, String value10) {
		this.id = id;
		this.broadcast_id = broadcast_id;
		this.email = email;
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBroadcast_id() {
		return broadcast_id;
	}
	public void setBroadcast_id(String broadcast_id) {
		this.broadcast_id = broadcast_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getName5() {
		return name5;
	}
	public void setName5(String name5) {
		this.name5 = name5;
	}
	public String getValue5() {
		return value5;
	}
	public void setValue5(String value5) {
		this.value5 = value5;
	}
	public String getName6() {
		return name6;
	}
	public void setName6(String name6) {
		this.name6 = name6;
	}
	public String getValue6() {
		return value6;
	}
	public void setValue6(String value6) {
		this.value6 = value6;
	}
	public String getName7() {
		return name7;
	}
	public void setName7(String name7) {
		this.name7 = name7;
	}
	public String getValue7() {
		return value7;
	}
	public void setValue7(String value7) {
		this.value7 = value7;
	}
	public String getName8() {
		return name8;
	}
	public void setName8(String name8) {
		this.name8 = name8;
	}
	public String getValue8() {
		return value8;
	}
	public void setValue8(String value8) {
		this.value8 = value8;
	}
	public String getName9() {
		return name9;
	}
	public void setName9(String name9) {
		this.name9 = name9;
	}
	public String getValue9() {
		return value9;
	}
	public void setValue9(String value9) {
		this.value9 = value9;
	}
	public String getName10() {
		return name10;
	}
	public void setName10(String name10) {
		this.name10 = name10;
	}
	public String getValue10() {
		return value10;
	}
	public void setValue10(String value10) {
		this.value10 = value10;
	}

	@Override
	public String toString() {
		return "EmailList [id=" + id + ", broadcast_id=" + broadcast_id
				+ ", email=" + email + ", name1=" + name1 + ", value1="
				+ value1 + ", name2=" + name2 + ", value2=" + value2
				+ ", name3=" + name3 + ", value3=" + value3 + ", name4="
				+ name4 + ", value4=" + value4 + ", name5=" + name5
				+ ", value5=" + value5 + ", name6=" + name6 + ", value6="
				+ value6 + ", name7=" + name7 + ", value7=" + value7
				+ ", name8=" + name8 + ", value8=" + value8 + ", name9="
				+ name9 + ", value9=" + value9 + ", name10=" + name10
				+ ", value10=" + value10 + "]";
	}
}