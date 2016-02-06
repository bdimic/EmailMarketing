/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

// TODO: Auto-generated Javadoc
/**
 * The Class Broadcast.
 */
@Entity
@Table(name="cm_email_broadcast")
public class Broadcast implements Serializable {
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
    
    /** The broadcast_id. */
    private String broadcast_id;
    
    /** The broadcast_source. */
    private String broadcast_source;
    
    /** The bcast_template_id. */
    private Integer bcast_template_id;
    
    /** The broadcast_name. */
    private String broadcast_name;    
    
    /** The campaign_id. */
    private String campaign_id;
    
    /** The subject. */
    private String subject;
    
    /** The htmlbody. */
    private String htmlbody;
    
    /** The htmlbody_tracking. */
    private String htmlbody_tracking;
    
    /** The htmlbody_embed. */
    private String htmlbody_embed;
    
    /** The plaintext. */
    private String plaintext;
    
    /** The status. */
    private String status;    
    
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
    
    /** The execution_dttm. */
    private Timestamp execution_dttm;
    
    /** The execution_user. */
    private String execution_user;
    
    /** The lead_number. */
    @Transient
    private int lead_number;
    
    /** The open_number. */
    @Transient
    private int open_number;
    
    /** The click_number. */
    @Transient
    private int click_number;

    /**
     * Instantiates a new broadcast.
     */
    public Broadcast() {
    	this.emailConfig = new EmailConfig();
    }

    /**
     * Instantiates a new broadcast.
     *
     * @param emailConfig the email config
     * @param id the id
     * @param broadcast_id the broadcast_id
     * @param broadcast_name the broadcast_name
     * @param bcast_template_id the bcast_template_id
     * @param campaign_id the campaign_id
     * @param subject the subject
     * @param baseurl the baseurl
     * @param htmlbody the htmlbody
     * @param htmlbody_tracking the htmlbody_tracking
     * @param htmlbody_embed the htmlbody_embed
     * @param plaintext the plaintext
     * @param status the status
     * @param creation_dttm the creation_dttm
     * @param creation_user the creation_user
     * @param last_change_dttm the last_change_dttm
     * @param last_change_user the last_change_user
     * @param execution_dttm the execution_dttm
     * @param execution_user the execution_user
     */
    public Broadcast(EmailConfig emailConfig, int id, String broadcast_id, String broadcast_name, Integer bcast_template_id, String campaign_id, String subject, String baseurl, String htmlbody, String htmlbody_tracking, String htmlbody_embed, String plaintext, String status, Timestamp creation_dttm, String creation_user, Timestamp last_change_dttm, String last_change_user, Timestamp execution_dttm, String execution_user) {
        this.emailConfig = emailConfig;
        this.id = id;
    	this.broadcast_id = broadcast_id;
    	this.bcast_template_id = bcast_template_id;
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
	 * Gets the broadcast_id.
	 *
	 * @return the broadcast_id
	 */
	public String getBroadcast_id() {
        return broadcast_id;
    }
    
    /**
     * Sets the broadcast_id.
     *
     * @param broadcast_id the new broadcast_id
     */
    public void setBroadcast_id(String broadcast_id) {
        this.broadcast_id = broadcast_id;
    }

    /**
     * Gets the broadcast_source.
     *
     * @return the broadcast_source
     */
    public String getBroadcast_source() {
		return broadcast_source;
	}

	/**
	 * Sets the broadcast_source.
	 *
	 * @param broadcast_source the new broadcast_source
	 */
	public void setBroadcast_source(String broadcast_source) {
		this.broadcast_source = broadcast_source;
	}

	/**
	 * Gets the bcast_template_id.
	 *
	 * @return the bcast_template_id
	 */
	public Integer getBcast_template_id() {
		return bcast_template_id;
	}

	/**
	 * Sets the bcast_template_id.
	 *
	 * @param bcast_template_id the new bcast_template_id
	 */
	public void setBcast_template_id(Integer bcast_template_id) {
		this.bcast_template_id = bcast_template_id;
	}

	/**
	 * Gets the broadcast_name.
	 *
	 * @return the broadcast_name
	 */
	public String getBroadcast_name() {
        return broadcast_name;
    }

    /**
     * Sets the broadcast_name.
     *
     * @param broadcast_name the new broadcast_name
     */
    public void setBroadcast_name(String broadcast_name) {
        this.broadcast_name = broadcast_name;
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
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject the new subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
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
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
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
     * Gets the execution_dttm.
     *
     * @return the execution_dttm
     */
    public Timestamp getExecution_dttm() {
        return execution_dttm;
    }

    /**
     * Sets the execution_dttm.
     *
     * @param execution_dttm the new execution_dttm
     */
    public void setExecution_dttm(Timestamp execution_dttm) {
        this.execution_dttm = execution_dttm;
    }

    /**
     * Gets the execution_user.
     *
     * @return the execution_user
     */
    public String getExecution_user() {
        return execution_user;
    }

    /**
     * Sets the execution_user.
     *
     * @param execution_user the new execution_user
     */
    public void setExecution_user(String execution_user) {
        this.execution_user = execution_user;
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

	/**
	 * Gets the lead_number.
	 *
	 * @return the lead_number
	 */
	public int getLead_number() {
		return lead_number;
	}

	/**
	 * Sets the lead_number.
	 *
	 * @param lead_number the new lead_number
	 */
	public void setLead_number(int lead_number) {
		this.lead_number = lead_number;
	}

	/**
	 * Gets the open_number.
	 *
	 * @return the open_number
	 */
	public int getOpen_number() {
		return open_number;
	}

	/**
	 * Sets the open_number.
	 *
	 * @param open_number the new open_number
	 */
	public void setOpen_number(int open_number) {
		this.open_number = open_number;
	}

	/**
	 * Gets the click_number.
	 *
	 * @return the click_number
	 */
	public int getClick_number() {
		return click_number;
	}

	/**
	 * Sets the click_number.
	 *
	 * @param click_number the new click_number
	 */
	public void setClick_number(int click_number) {
		this.click_number = click_number;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
