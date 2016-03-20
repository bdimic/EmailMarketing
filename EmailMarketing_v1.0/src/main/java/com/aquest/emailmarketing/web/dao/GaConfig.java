package com.aquest.emailmarketing.web.dao;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;


// TODO: Auto-generated Javadoc
/**
 * The Class GaConfig.
 */
@Entity
@Table(name="cm_ga_config")
public class GaConfig {
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
	
	/** The application_name. */
	@NotBlank()
	private String application_name;
	
	/** The table_id. */
	@NotBlank()
	private String table_id;
	
	/** The P12_key_file_name. */
	private String P12_key_file_name;
	
	/** The P12_file. */
	private byte[] P12_file;
	
	/** The api_email. */
	@NotBlank()
	private String api_email;
	
	/**
	 * Instantiates a new ga config.
	 */
	public GaConfig() {
		
	}

	/**
	 * Instantiates a new ga config.
	 *
	 * @param id the id
	 * @param application_name the application_name
	 * @param table_id the table_id
	 * @param p12_key_file_name the p12_key_file_name
	 * @param p12_file
	 * @param api_email the api_email
	 */
	public GaConfig(int id, String application_name, String table_id, String p12_key_file_name, byte[] p12_file, String api_email) {
		super();
		this.id = id;
		this.application_name = application_name;
		this.table_id = table_id;
		this.P12_key_file_name = p12_key_file_name;
		this.P12_file = p12_file;
		this.api_email = api_email;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the application_name.
	 *
	 * @return the application_name
	 */
	public String getApplication_name() {
		return application_name;
	}

	/**
	 * Sets the application_name.
	 *
	 * @param application_name the new application_name
	 */
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	/**
	 * Gets the table_id.
	 *
	 * @return the table_id
	 */
	public String getTable_id() {
		return table_id;
	}

	/**
	 * Sets the table_id.
	 *
	 * @param table_id the new table_id
	 */
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}

	/**
	 * Gets the p12_key_file_name.
	 *
	 * @return the p12_key_file_name
	 */
	public String getP12_key_file_name() {
		return P12_key_file_name;
	}

	/**
	 * Sets the p12_key_file_name.
	 *
	 * @param p12_key_file_name the new p12_key_file_name
	 */
	public void setP12_key_file_name(String p12_key_file_name) {
		P12_key_file_name = p12_key_file_name;
	}
		
	/**
	 * @return the p12_file
	 */
	public byte[] getP12_file() {
		return P12_file;
	}

	/**
	 * @param p12_file the p12_file to set
	 */
	public void setP12_file(byte[] p12_file) {
		P12_file = p12_file;
	}

	/**
	 * Gets the api_email.
	 *
	 * @return the api_email
	 */
	public String getApi_email() {
		return api_email;
	}

	/**
	 * Sets the api_email.
	 *
	 * @param api_email the new api_email
	 */
	public void setApi_email(String api_email) {
		this.api_email = api_email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GaConfig [id=" + id + ", application_name=" + application_name + ", table_id=" + table_id
				+ ", P12_key_file_name=" + P12_key_file_name + ", api_email=" + api_email + "]";
	}
}