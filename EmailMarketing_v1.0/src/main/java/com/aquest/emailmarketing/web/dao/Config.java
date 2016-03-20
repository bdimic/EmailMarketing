package com.aquest.emailmarketing.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The Class GaConfig.
 */
@Entity
@Table(name="cm_config")
public class Config {
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;	
	/** The category */
	private String category;
	/** The value */
	private String value;
	/**
	 * 
	 */
	public Config() {

	}
	/**
	 * @param id
	 * @param category
	 * @param value
	 */
	public Config(int id, String category, String value) {
		super();
		this.id = id;
		this.category = category;
		this.value = value;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Config [id=" + id + ", category=" + category + ", value=" + value + "]";
	}
}