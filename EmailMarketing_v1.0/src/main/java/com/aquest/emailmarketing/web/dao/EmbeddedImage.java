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
 * The Class EmbeddedImage.
 */
@Entity
@Table(name="cm_email_embedded_images")
public class EmbeddedImage {

	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
    
    /** The broadcast_id. */
    private String broadcast_id;
    
    /** The bcast_template_id. */
    private int bcast_template_id;
    
    /** The url. */
    private String url;    
    
	/**
	 * Instantiates a new embedded image.
	 */
	public EmbeddedImage() {

	}
	
	/**
	 * Instantiates a new embedded image.
	 *
	 * @param id the id
	 * @param broadcast_id the broadcast_id
	 * @param bcast_template_id the bcast_template_id
	 * @param url the url
	 */
	public EmbeddedImage(int id, String broadcast_id, int bcast_template_id, String url) {
		this.id = id;
		this.broadcast_id = broadcast_id;
		this.bcast_template_id = bcast_template_id;
		this.url = url;
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
	 * Gets the bcast_template_id.
	 *
	 * @return the bcast_template_id
	 */
	public int getBcast_template_id() {
		return bcast_template_id;
	}

	/**
	 * Sets the bcast_template_id.
	 *
	 * @param bcast_template_id the new bcast_template_id
	 */
	public void setBcast_template_id(int bcast_template_id) {
		this.bcast_template_id = bcast_template_id;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmbeddedImage [id=" + id + ", broadcast_id=" + broadcast_id + ", bcast_template_id=" + bcast_template_id
				+ ", url=" + url + "]";
	}
}
