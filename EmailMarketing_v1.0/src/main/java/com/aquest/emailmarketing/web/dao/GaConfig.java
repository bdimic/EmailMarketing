package com.aquest.emailmarketing.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="cm_ga_config")
public class GaConfig {
	
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
	@NotBlank()
	private String application_name;
	@NotBlank()
	private String table_id;
	@NotBlank()
	private String P12_key_file_name;
	@NotBlank()
	private String api_email;
	
	public GaConfig() {
		
	}

	public GaConfig(int id, String application_name, String table_id, String p12_key_file_name, String api_email) {
		super();
		this.id = id;
		this.application_name = application_name;
		this.table_id = table_id;
		P12_key_file_name = p12_key_file_name;
		this.api_email = api_email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}

	public String getP12_key_file_name() {
		return P12_key_file_name;
	}

	public void setP12_key_file_name(String p12_key_file_name) {
		P12_key_file_name = p12_key_file_name;
	}

	public String getApi_email() {
		return api_email;
	}

	public void setApi_email(String api_email) {
		this.api_email = api_email;
	}

	@Override
	public String toString() {
		return "GaConfig [id=" + id + ", application_name=" + application_name + ", table_id=" + table_id
				+ ", P12_key_file_name=" + P12_key_file_name + ", api_email=" + api_email + "]";
	}
}