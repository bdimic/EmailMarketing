/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aquest.emailmarketing.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignCategory.
 */
@Entity
@Table(name="cm_campaign_category")
public class CampaignCategory {
    
    /** The category_id. */
    @Id 
    @GeneratedValue
    @Column(name = "category_id")
    private int category_id;    
    
    /** The category_description. */
    private String category_description;
    
    
    
	/**
	 * Instantiates a new campaign category.
	 */
	public CampaignCategory() {

	}

	/**
	 * Instantiates a new campaign category.
	 *
	 * @param category_id the category_id
	 * @param category_description the category_description
	 */
	public CampaignCategory(int category_id, String category_description) {
		this.category_id = category_id;
		this.category_description = category_description;
	}

	/**
	 * Gets the category_id.
	 *
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}

	/**
	 * Sets the category_id.
	 *
	 * @param category_id the new category_id
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	/**
	 * Gets the category_description.
	 *
	 * @return the category_description
	 */
	public String getCategory_description() {
		return category_description;
	}

	/**
	 * Sets the category_description.
	 *
	 * @param category_description the new category_description
	 */
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CampaignCategory [category_id=" + category_id
				+ ", category_description=" + category_description + "]";
	}
}