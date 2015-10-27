/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aquest.emailmarketing.web.dao;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author bdimic
 */
@Entity
@Table(name="cm_email_communication")
public class Campaigns {
    @Id 
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String campaign_id;
    private String campaign_source;
    @NotBlank
    @Size(min=3, max=30)
    private String campaign_name;
    @NotBlank
    @Size(min=3, max=250)
    private String campaign_description;
    private String campaign_status;
    private Date campaign_start_date;
    private Date campaign_end_date;
    private String creation_user;
    private Timestamp creation_dttm;
    private String last_change_user;
    private Timestamp last_change_dttm;
    @ManyToOne
    @JoinColumn(name="category_id")
    private CampaignCategory campaignCategory;

	

    public Campaigns() {
    	this.campaignCategory = new CampaignCategory();
    }

	public Campaigns(int id, String campaign_id, String campaign_source,
			String campaign_name, String campaign_description, 
			String campaign_status,	Date campaign_start_date, 
			Date campaign_end_date, String creation_user,
			Timestamp creation_dttm, String last_change_user,
			Timestamp last_change_dttm) {
		this.id = id;
		this.campaign_id = campaign_id;
		this.campaign_source = campaign_source;
		this.campaign_name = campaign_name;
		this.campaign_description = campaign_description;
		this.campaign_status = campaign_status;
		this.campaign_start_date = campaign_start_date;
		this.campaign_end_date = campaign_end_date;

		this.creation_user = creation_user;
		this.creation_dttm = creation_dttm;
		this.last_change_user = last_change_user;
		this.last_change_dttm = last_change_dttm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCampaign_source() {
		return campaign_source;
	}

	public void setCampaign_source(String campaign_source) {
		this.campaign_source = campaign_source;
	}

	public String getCampaign_name() {
		return campaign_name;
	}

	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	public String getCampaign_description() {
		return campaign_description;
	}

	public void setCampaign_description(String campaign_description) {
		this.campaign_description = campaign_description;
	}

	public String getCampaign_status() {
		return campaign_status;
	}

	public void setCampaign_status(String campaign_status) {
		this.campaign_status = campaign_status;
	}

	public Date getCampaign_start_date() {
		return campaign_start_date;
	}

	public void setCampaign_start_date(Date campaign_start_date) {
		this.campaign_start_date = campaign_start_date;
	}

	public Date getCampaign_end_date() {
		return campaign_end_date;
	}

	public void setCampaign_end_date(Date campaign_end_date) {
		this.campaign_end_date = campaign_end_date;
	}

	public int getCategory_id() {
		return campaignCategory.getCategory_id();
	}

	public String getCreation_user() {
		return creation_user;
	}

	public void setCreation_user(String creation_user) {
		this.creation_user = creation_user;
	}

	public Timestamp getCreation_dttm() {
		return creation_dttm;
	}

	public void setCreation_dttm(Timestamp creation_dttm) {
		this.creation_dttm = creation_dttm;
	}

	public String getLast_change_user() {
		return last_change_user;
	}

	public void setLast_change_user(String last_change_user) {
		this.last_change_user = last_change_user;
	}

	public Timestamp getLast_change_dttm() {
		return last_change_dttm;
	}

	public void setLast_change_dttm(Timestamp last_change_dttm) {
		this.last_change_dttm = last_change_dttm;
	}
	
	public CampaignCategory getCampaignCategory() {
		return campaignCategory;
	}

	public void setCampaignCategory(CampaignCategory campaignCategory) {
		this.campaignCategory = campaignCategory;
	}

	@Override
	public String toString() {
		return "Campaigns [id=" + id + ", campaign_id=" + campaign_id + ", campaign_source=" + campaign_source + ", campaign_name="
				+ campaign_name + ", campaign_description="
				+ campaign_description + ", campaign_status=" + campaign_status
				+ ", campaign_start_date=" + campaign_start_date
				+ ", campaign_end_date=" + campaign_end_date
				+ ", creation_user=" + creation_user + ", creation_dttm="
				+ creation_dttm + ", last_change_user=" + last_change_user
				+ ", last_change_dttm=" + last_change_dttm + "]";
	}    
}