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
import javax.persistence.Transient;

/**
 *
 * @author bdimic
 */
@Entity
@Table(name="cm_email_broadcast")
public class Broadcast implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
    private String broadcast_id;
    private String broadcast_source;
    private String broadcast_name;    
    private String campaign_id;
    private String subject;
    private String htmlbody;
    private String htmlbody_tracking;
    private String htmlbody_embed;
    private String plaintext;
    private String status;    
    @ManyToOne
    @JoinColumn(name="profile_id")
    private EmailConfig emailConfig;
    private Timestamp creation_dttm;
    private String creation_user;
    private Timestamp last_change_dttm;
    private String last_change_user;
    private Timestamp execution_dttm;
    private String execution_user;
    @Transient
    private int leadNumber;
    @Transient
    private int openNumber;
    @Transient
    private int clickNumber;

    public Broadcast() {
    	this.emailConfig = new EmailConfig();
    }

    public Broadcast(EmailConfig emailConfig, int id, String broadcast_id, String broadcast_name, String campaign_id, String subject, String baseurl, String htmlbody, String htmlbody_tracking, String htmlbody_embed, String plaintext, String status, Timestamp creation_dttm, String creation_user, Timestamp last_change_dttm, String last_change_user, Timestamp execution_dttm, String execution_user) {
        this.emailConfig = emailConfig;
        this.id = id;
    	this.broadcast_id = broadcast_id;
        this.broadcast_name = broadcast_name;
        this.campaign_id = campaign_id;
        this.subject = subject;
        this.htmlbody = htmlbody;
        this.htmlbody_tracking = htmlbody_tracking;
        this.htmlbody_embed = htmlbody_embed;
        this.plaintext = plaintext;
        this.status = status;
        this.creation_dttm = creation_dttm;
        this.creation_user = creation_user;
        this.last_change_dttm = last_change_dttm;
        this.last_change_user = last_change_user;
        this.execution_dttm = execution_dttm;
        this.execution_user = execution_user;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBroadcast_id() {
        return broadcast_id;
    }
    
    public void setBroadcast_id(String broadcast_id) {
        this.broadcast_id = broadcast_id;
    }

    public String getBroadcast_source() {
		return broadcast_source;
	}

	public void setBroadcast_source(String broadcast_source) {
		this.broadcast_source = broadcast_source;
	}

	public String getBroadcast_name() {
        return broadcast_name;
    }

    public void setBroadcast_name(String broadcast_name) {
        this.broadcast_name = broadcast_name;
    }

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlbody() {
        return htmlbody;
    }

    public void setHtmlbody(String htmlbody) {
        this.htmlbody = htmlbody;
    }
       
    public String getHtmlbody_tracking() {
		return htmlbody_tracking;
	}

	public void setHtmlbody_tracking(String htmlbody_tracking) {
		this.htmlbody_tracking = htmlbody_tracking;
	}

	public String getHtmlbody_embed() {
		return htmlbody_embed;
	}

	public void setHtmlbody_embed(String htmlbody_embed) {
		this.htmlbody_embed = htmlbody_embed;
	}

	public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreation_dttm() {
        return creation_dttm;
    }

    public void setCreation_dttm(Timestamp creation_dttm) {
        this.creation_dttm = creation_dttm;
    }

    public String getCreation_user() {
        return creation_user;
    }

    public void setCreation_user(String creation_user) {
        this.creation_user = creation_user;
    }

    public Timestamp getLast_change_dttm() {
        return last_change_dttm;
    }

    public void setLast_change_dttm(Timestamp last_change_dttm) {
        this.last_change_dttm = last_change_dttm;
    }

    public String getLast_change_user() {
        return last_change_user;
    }

    public void setLast_change_user(String last_change_user) {
        this.last_change_user = last_change_user;
    }

    public Timestamp getExecution_dttm() {
        return execution_dttm;
    }

    public void setExecution_dttm(Timestamp execution_dttm) {
        this.execution_dttm = execution_dttm;
    }

    public String getExecution_user() {
        return execution_user;
    }

    public void setExecution_user(String execution_user) {
        this.execution_user = execution_user;
    }

	public EmailConfig getEmailConfig() {
		return emailConfig;
	}

	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	public int getProfile_id() {
		return emailConfig.getProfile_id();
	}	

	public int getLeadNumber() {
		return leadNumber;
	}

	public void setLeadNumber(int leadNumber) {
		this.leadNumber = leadNumber;
	}

	public int getOpenNumber() {
		return openNumber;
	}

	public void setOpenNumber(int openNumber) {
		this.openNumber = openNumber;
	}

	public int getClickNumber() {
		return clickNumber;
	}

	public void setClickNumber(int clickNumber) {
		this.clickNumber = clickNumber;
	}

	@Override
	public String toString() {
		return "Broadcast [id=" + id + ", broadcast_id=" + broadcast_id + ", broadcast_source=" + broadcast_source
				+ ", broadcast_name=" + broadcast_name + ", campaign_id=" + campaign_id + ", subject=" + subject
				+ ", htmlbody=" + htmlbody + ", htmlbody_tracking=" + htmlbody_tracking + ", htmlbody_embed="
				+ htmlbody_embed + ", plaintext=" + plaintext + ", status=" + status + ", emailConfig=" + emailConfig
				+ ", creation_dttm=" + creation_dttm + ", creation_user=" + creation_user + ", last_change_dttm="
				+ last_change_dttm + ", last_change_user=" + last_change_user + ", execution_dttm=" + execution_dttm
				+ ", execution_user=" + execution_user + "]";
	}	 
}
