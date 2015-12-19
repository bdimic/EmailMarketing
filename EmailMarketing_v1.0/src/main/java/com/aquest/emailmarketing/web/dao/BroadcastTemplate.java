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
@Table(name="cm_broadcast_template")
public class BroadcastTemplate implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
    private String b_template_name;    
    private String b_template_subject;
    private String htmlbody;
    private String htmlbody_tracking;
    private String htmlbody_embed;
    private String plaintext;    
    @ManyToOne
    @JoinColumn(name="profile_id")
    private EmailConfig emailConfig;
    private Timestamp creation_dttm;
    private String creation_user;
    private Timestamp last_change_dttm;
    private String last_change_user;

    public BroadcastTemplate() {
    	this.emailConfig = new EmailConfig();
    }    

    public BroadcastTemplate(int id, String b_template_name, String b_template_subject, String htmlbody,
			String htmlbody_tracking, String htmlbody_embed, String plaintext, EmailConfig emailConfig,
			Timestamp creation_dttm, String creation_user, Timestamp last_change_dttm, String last_change_user) {
		super();
		this.id = id;
		this.b_template_name = b_template_name;
		this.b_template_subject = b_template_subject;
		this.htmlbody = htmlbody;
		this.htmlbody_tracking = htmlbody_tracking;
		this.htmlbody_embed = htmlbody_embed;
		this.plaintext = plaintext;
		this.emailConfig = emailConfig;
		this.creation_dttm = creation_dttm;
		this.creation_user = creation_user;
		this.last_change_dttm = last_change_dttm;
		this.last_change_user = last_change_user;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}		

    public String getB_template_name() {
		return b_template_name;
	}

	public void setB_template_name(String b_template_name) {
		this.b_template_name = b_template_name;
	}

	public String getB_template_subject() {
		return b_template_subject;
	}

	public void setB_template_subject(String b_template_subject) {
		this.b_template_subject = b_template_subject;
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

	public EmailConfig getEmailConfig() {
		return emailConfig;
	}

	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	public int getProfile_id() {
		return emailConfig.getProfile_id();
	}

	@Override
	public String toString() {
		return "BroadcastTemplate [id=" + id + ", b_template_name=" + b_template_name + ", b_template_subject="
				+ b_template_subject + ", htmlbody=" + htmlbody + ", htmlbody_tracking=" + htmlbody_tracking
				+ ", htmlbody_embed=" + htmlbody_embed + ", plaintext=" + plaintext + ", emailConfig=" + emailConfig
				+ ", creation_dttm=" + creation_dttm + ", creation_user=" + creation_user + ", last_change_dttm="
				+ last_change_dttm + ", last_change_user=" + last_change_user + "]";
	}
}
