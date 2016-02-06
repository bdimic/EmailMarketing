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

// TODO: Auto-generated Javadoc
/**
 * The Class BroadcastTemplate.
 */
@Entity
@Table(name="cm_broadcast_template")
public class BroadcastTemplate implements Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
    
    /** The b_template_name. */
    private String b_template_name;    
    
    /** The b_template_subject. */
    private String b_template_subject;
    
    /** The htmlbody. */
    private String htmlbody;
    
    /** The htmlbody_tracking. */
    private String htmlbody_tracking;
    
    /** The htmlbody_embed. */
    private String htmlbody_embed;
    
    /** The plaintext. */
    private String plaintext;    
    
    /** The email config. */
    @ManyToOne
    @JoinColumn(name="profile_id")
    private EmailConfig emailConfig;
    
    /** The creation_dttm. */
    private Timestamp creation_dttm;
    
    /** The creation_user. */
    private String creation_user;
    
    /** The last_change_dttm. */
    private Timestamp last_change_dttm;
    
    /** The last_change_user. */
    private String last_change_user;

    /**
     * Instantiates a new broadcast template.
     */
    public BroadcastTemplate() {
    	this.emailConfig = new EmailConfig();
    }    

    /**
     * Instantiates a new broadcast template.
     *
     * @param id the id
     * @param b_template_name the b_template_name
     * @param b_template_subject the b_template_subject
     * @param htmlbody the htmlbody
     * @param htmlbody_tracking the htmlbody_tracking
     * @param htmlbody_embed the htmlbody_embed
     * @param plaintext the plaintext
     * @param emailConfig the email config
     * @param creation_dttm the creation_dttm
     * @param creation_user the creation_user
     * @param last_change_dttm the last_change_dttm
     * @param last_change_user the last_change_user
     */
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
     * Gets the b_template_name.
     *
     * @return the b_template_name
     */
    public String getB_template_name() {
		return b_template_name;
	}

	/**
	 * Sets the b_template_name.
	 *
	 * @param b_template_name the new b_template_name
	 */
	public void setB_template_name(String b_template_name) {
		this.b_template_name = b_template_name;
	}

	/**
	 * Gets the b_template_subject.
	 *
	 * @return the b_template_subject
	 */
	public String getB_template_subject() {
		return b_template_subject;
	}

	/**
	 * Sets the b_template_subject.
	 *
	 * @param b_template_subject the new b_template_subject
	 */
	public void setB_template_subject(String b_template_subject) {
		this.b_template_subject = b_template_subject;
	}

	/**
	 * Gets the htmlbody.
	 *
	 * @return the htmlbody
	 */
	public String getHtmlbody() {
        return htmlbody;
    }

    /**
     * Sets the htmlbody.
     *
     * @param htmlbody the new htmlbody
     */
    public void setHtmlbody(String htmlbody) {
        this.htmlbody = htmlbody;
    }
       
    /**
     * Gets the htmlbody_tracking.
     *
     * @return the htmlbody_tracking
     */
    public String getHtmlbody_tracking() {
		return htmlbody_tracking;
	}

	/**
	 * Sets the htmlbody_tracking.
	 *
	 * @param htmlbody_tracking the new htmlbody_tracking
	 */
	public void setHtmlbody_tracking(String htmlbody_tracking) {
		this.htmlbody_tracking = htmlbody_tracking;
	}

	/**
	 * Gets the htmlbody_embed.
	 *
	 * @return the htmlbody_embed
	 */
	public String getHtmlbody_embed() {
		return htmlbody_embed;
	}

	/**
	 * Sets the htmlbody_embed.
	 *
	 * @param htmlbody_embed the new htmlbody_embed
	 */
	public void setHtmlbody_embed(String htmlbody_embed) {
		this.htmlbody_embed = htmlbody_embed;
	}

	/**
	 * Gets the plaintext.
	 *
	 * @return the plaintext
	 */
	public String getPlaintext() {
        return plaintext;
    }

    /**
     * Sets the plaintext.
     *
     * @param plaintext the new plaintext
     */
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
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
	 * Gets the email config.
	 *
	 * @return the email config
	 */
	public EmailConfig getEmailConfig() {
		return emailConfig;
	}

	/**
	 * Sets the email config.
	 *
	 * @param emailConfig the new email config
	 */
	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}
	
	/**
	 * Gets the profile_id.
	 *
	 * @return the profile_id
	 */
	public int getProfile_id() {
		return emailConfig.getProfile_id();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BroadcastTemplate [id=" + id + ", b_template_name=" + b_template_name + ", b_template_subject="
				+ b_template_subject + ", htmlbody=" + htmlbody + ", htmlbody_tracking=" + htmlbody_tracking
				+ ", htmlbody_embed=" + htmlbody_embed + ", plaintext=" + plaintext + ", emailConfig=" + emailConfig
				+ ", creation_dttm=" + creation_dttm + ", creation_user=" + creation_user + ", last_change_dttm="
				+ last_change_dttm + ", last_change_user=" + last_change_user + "]";
	}
}
