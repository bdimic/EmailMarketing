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
@Table(name="cm_email_embedded_images")
public class EmbeddedImage {

	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
    private String broadcast_id;
    private int bcast_template_id;
    private String url;    
    
	public EmbeddedImage() {

	}
	
	public EmbeddedImage(int id, String broadcast_id, int bcast_template_id, String url) {
		this.id = id;
		this.broadcast_id = broadcast_id;
		this.bcast_template_id = bcast_template_id;
		this.url = url;
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
	
	public int getBcast_template_id() {
		return bcast_template_id;
	}

	public void setBcast_template_id(int bcast_template_id) {
		this.bcast_template_id = bcast_template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "EmbeddedImage [id=" + id + ", broadcast_id=" + broadcast_id + ", bcast_template_id=" + bcast_template_id
				+ ", url=" + url + "]";
	}
}
