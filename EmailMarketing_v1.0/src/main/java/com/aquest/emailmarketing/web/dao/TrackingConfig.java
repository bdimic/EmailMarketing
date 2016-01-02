package com.aquest.emailmarketing.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="cm_tracking_config")
public class TrackingConfig {
	
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
	private String broadcast_id;
	private int bcast_template_id;
	private int tracking_flg;
	private String tracking_type;
	private String utm_campaign;
	private String utm_medium;
	private String utm_source;
	private String utm_content;
	private int open_ga_flg;
	private int open_pixel_flg;
	
	
	public TrackingConfig() {
		
	}


	public TrackingConfig(int id, String broadcast_id, int bcast_template_id, int tracking_flg, String tracking_type, String utm_campaign,
			String utm_medium, String utm_source, String utm_content, int open_ga_flg, int open_pixel_flg) {
		super();
		this.id = id;
		this.broadcast_id = broadcast_id;
		this.bcast_template_id = bcast_template_id;
		this.tracking_flg = tracking_flg;
		this.tracking_type = tracking_type;
		this.utm_campaign = utm_campaign;
		this.utm_medium = utm_medium;
		this.utm_source = utm_source;
		this.utm_content = utm_content;
		this.open_ga_flg = open_ga_flg;
		this.open_pixel_flg = open_pixel_flg;
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


	public int getTracking_flg() {
		return tracking_flg;
	}


	public void setTracking_flg(int tracking_flg) {
		this.tracking_flg = tracking_flg;
	}


	public String getTracking_type() {
		return tracking_type;
	}


	public void setTracking_type(String tracking_type) {
		this.tracking_type = tracking_type;
	}


	public String getUtm_campaign() {
		return utm_campaign;
	}


	public void setUtm_campaign(String utm_campaign) {
		this.utm_campaign = utm_campaign;
	}


	public String getUtm_medium() {
		return utm_medium;
	}


	public void setUtm_medium(String utm_medium) {
		this.utm_medium = utm_medium;
	}


	public String getUtm_source() {
		return utm_source;
	}


	public void setUtm_source(String utm_source) {
		this.utm_source = utm_source;
	}


	public String getUtm_content() {
		return utm_content;
	}


	public void setUtm_content(String utm_content) {
		this.utm_content = utm_content;
	}


	public int getOpen_ga_flg() {
		return open_ga_flg;
	}


	public void setOpen_ga_flg(int open_ga_flg) {
		this.open_ga_flg = open_ga_flg;
	}


	public int getOpen_pixel_flg() {
		return open_pixel_flg;
	}


	public void setOpen_pixel_flg(int open_pixel_flg) {
		this.open_pixel_flg = open_pixel_flg;
	}


	@Override
	public String toString() {
		return "TrackingConfig [id=" + id + ", broadcast_id=" + broadcast_id + ", bcast_template_id="
				+ bcast_template_id + ", tracking_flg=" + tracking_flg + ", tracking_type=" + tracking_type
				+ ", utm_campaign=" + utm_campaign + ", utm_medium=" + utm_medium + ", utm_source=" + utm_source
				+ ", utm_content=" + utm_content + ", open_ga_flg=" + open_ga_flg + ", open_pixel_flg=" + open_pixel_flg
				+ "]";
	}	
}
