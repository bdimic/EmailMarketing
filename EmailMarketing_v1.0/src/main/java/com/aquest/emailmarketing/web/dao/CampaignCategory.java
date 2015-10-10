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

/**
 *
 * @author bdimic
 */
@Entity
@Table(name="cm_campaign_category")
public class CampaignCategory {
    @Id 
    @GeneratedValue
    @Column(name = "category_id")
    private int category_id;    
    private String category_description;
    
    
    
	public CampaignCategory() {

	}

	public CampaignCategory(int category_id, String category_description) {
		this.category_id = category_id;
		this.category_description = category_description;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	@Override
	public String toString() {
		return "CampaignCategory [category_id=" + category_id
				+ ", category_description=" + category_description + "]";
	}
}