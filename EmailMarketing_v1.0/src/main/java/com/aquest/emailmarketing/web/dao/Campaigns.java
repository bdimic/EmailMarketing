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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

// TODO: Auto-generated Javadoc
/**
 * The Class Campaigns.
 */
@Entity
@Table(name="cm_email_communication")
public class Campaigns {
    
    /** The id. */
    @Id 
    @GeneratedValue
    @Column(name = "id")
    private int id;
    
    /** The campaign_id. */
    private String campaign_id;
    
    /** The campaign_source. */
    private String campaign_source;
    
    /** The campaign_name. */
    @NotBlank
    @Size(min=3, max=30)
    private String campaign_name;
    
    /** The campaign_description. */
    @NotBlank
    @Size(min=3, max=250)
    private String campaign_description;
    
    /** The campaign_status. */
    private String campaign_status;
    
    /** The campaign_start_date. */
    private Date campaign_start_date;
    
    /** The campaign_end_date. */
    private Date campaign_end_date;
    
    /** The creation_user. */
    private String creation_user;
    
    /** The creation_dttm. */
    private Timestamp creation_dttm;
    
    /** The last_change_user. */
    private String last_change_user;
    
    /** The last_change_dttm. */
    private Timestamp last_change_dttm;
    
    /** The broadcast_number. */
    @Transient
    private int broadcast_number;
    
    /** The campaign category. */
    @ManyToOne
    @JoinColumn(name="category_id")
    private CampaignCategory campaignCategory;

	

//    /**
//     * Instantiates a new campaigns.
//     */
//    public Campaigns() {
//    	this.campaignCategory = new CampaignCategory();
//    }
//
//	/**
//	 * Instantiates a new campaigns.
//	 *
//	 * @param id the id
//	 * @param campaign_id the campaign_id
//	 * @param campaign_source the campaign_source
//	 * @param campaign_name the campaign_name
//	 * @param campaign_description the campaign_description
//	 * @param campaign_status the campaign_status
//	 * @param campaign_start_date the campaign_start_date
//	 * @param campaign_end_date the campaign_end_date
//	 * @param creation_user the creation_user
//	 * @param creation_dttm the creation_dttm
//	 * @param last_change_user the last_change_user
//	 * @param last_change_dttm the last_change_dttm
//	 */
//	public Campaigns(int id, String campaign_id, String campaign_source,
//			String campaign_name, String campaign_description, 
//			String campaign_status,	Date campaign_start_date, 
//			Date campaign_end_date, String creation_user,
//			Timestamp creation_dttm, String last_change_user,
//			Timestamp last_change_dttm) {
//		this.id = id;
//		this.campaign_id = campaign_id;
//		this.campaign_source = campaign_source;
//		this.campaign_name = campaign_name;
//		this.campaign_description = campaign_description;
//		this.campaign_status = campaign_status;
//		this.campaign_start_date = campaign_start_date;
//		this.campaign_end_date = campaign_end_date;
//
//		this.creation_user = creation_user;
//		this.creation_dttm = creation_dttm;
//		this.last_change_user = last_change_user;
//		this.last_change_dttm = last_change_dttm;
//	}

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
	 * Gets the campaign_id.
	 *
	 * @return the campaign_id
	 */
	public String getCampaign_id() {
		return campaign_id;
	}

	/**
	 * Sets the campaign_id.
	 *
	 * @param campaign_id the new campaign_id
	 */
	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	/**
	 * Gets the campaign_source.
	 *
	 * @return the campaign_source
	 */
	public String getCampaign_source() {
		return campaign_source;
	}

	/**
	 * Sets the campaign_source.
	 *
	 * @param campaign_source the new campaign_source
	 */
	public void setCampaign_source(String campaign_source) {
		this.campaign_source = campaign_source;
	}

	/**
	 * Gets the campaign_name.
	 *
	 * @return the campaign_name
	 */
	public String getCampaign_name() {
		return campaign_name;
	}

	/**
	 * Sets the campaign_name.
	 *
	 * @param campaign_name the new campaign_name
	 */
	public void setCampaign_name(String campaign_name) {
		this.campaign_name = campaign_name;
	}

	/**
	 * Gets the campaign_description.
	 *
	 * @return the campaign_description
	 */
	public String getCampaign_description() {
		return campaign_description;
	}

	/**
	 * Sets the campaign_description.
	 *
	 * @param campaign_description the new campaign_description
	 */
	public void setCampaign_description(String campaign_description) {
		this.campaign_description = campaign_description;
	}

	/**
	 * Gets the campaign_status.
	 *
	 * @return the campaign_status
	 */
	public String getCampaign_status() {
		return campaign_status;
	}

	/**
	 * Sets the campaign_status.
	 *
	 * @param campaign_status the new campaign_status
	 */
	public void setCampaign_status(String campaign_status) {
		this.campaign_status = campaign_status;
	}

	/**
	 * Gets the campaign_start_date.
	 *
	 * @return the campaign_start_date
	 */
	public Date getCampaign_start_date() {
		return campaign_start_date;
	}

	/**
	 * Sets the campaign_start_date.
	 *
	 * @param campaign_start_date the new campaign_start_date
	 */
	public void setCampaign_start_date(Date campaign_start_date) {
		this.campaign_start_date = campaign_start_date;
	}

	/**
	 * Gets the campaign_end_date.
	 *
	 * @return the campaign_end_date
	 */
	public Date getCampaign_end_date() {
		return campaign_end_date;
	}

	/**
	 * Sets the campaign_end_date.
	 *
	 * @param campaign_end_date the new campaign_end_date
	 */
	public void setCampaign_end_date(Date campaign_end_date) {
		this.campaign_end_date = campaign_end_date;
	}

	/**
	 * Gets the category_id.
	 *
	 * @return the category_id
	 */
	public int getCategory_id() {
		return campaignCategory.getCategory_id();
	}

	/**
	 * Gets the creation_user.
	 *
	 * @return the creation_user
	 */
	public String getCreation_user() {
		return creation_user;
	}

	/**
	 * Sets the creation_user.
	 *
	 * @param creation_user the new creation_user
	 */
	public void setCreation_user(String creation_user) {
		this.creation_user = creation_user;
	}

	/**
	 * Gets the creation_dttm.
	 *
	 * @return the creation_dttm
	 */
	public Timestamp getCreation_dttm() {
		return creation_dttm;
	}

	/**
	 * Sets the creation_dttm.
	 *
	 * @param creation_dttm the new creation_dttm
	 */
	public void setCreation_dttm(Timestamp creation_dttm) {
		this.creation_dttm = creation_dttm;
	}

	/**
	 * Gets the last_change_user.
	 *
	 * @return the last_change_user
	 */
	public String getLast_change_user() {
		return last_change_user;
	}

	/**
	 * Sets the last_change_user.
	 *
	 * @param last_change_user the new last_change_user
	 */
	public void setLast_change_user(String last_change_user) {
		this.last_change_user = last_change_user;
	}

	/**
	 * Gets the last_change_dttm.
	 *
	 * @return the last_change_dttm
	 */
	public Timestamp getLast_change_dttm() {
		return last_change_dttm;
	}

	/**
	 * Sets the last_change_dttm.
	 *
	 * @param last_change_dttm the new last_change_dttm
	 */
	public void setLast_change_dttm(Timestamp last_change_dttm) {
		this.last_change_dttm = last_change_dttm;
	}	
	
	/**
	 * Gets the broadcast_number.
	 *
	 * @return the broadcast_number
	 */
	public int getBroadcast_number() {
		return broadcast_number;
	}

	/**
	 * Sets the broadcast_number.
	 *
	 * @param broadcast_number the new broadcast_number
	 */
	public void setBroadcast_number(int broadcast_number) {
		this.broadcast_number = broadcast_number;
	}

	/**
	 * Gets the campaign category.
	 *
	 * @return the campaign category
	 */
	public CampaignCategory getCampaignCategory() {
		return campaignCategory;
	}

	/**
	 * Sets the campaign category.
	 *
	 * @param campaignCategory the new campaign category
	 */
	public void setCampaignCategory(CampaignCategory campaignCategory) {
		this.campaignCategory = campaignCategory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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