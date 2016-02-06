package com.aquest.emailmarketing.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The Class TrackingConfig.
 */
@Entity
@Table(name="cm_tracking_config")
public class TrackingConfig {
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private int id;
	
	/** The broadcast_id. */
	private String broadcast_id;
	
	/** The bcast_template_id. */
	private int bcast_template_id;
	
	/** The tracking_flg. */
	private int tracking_flg;
	
	/** The tracking_type. */
	private String tracking_type;
	
	/** The utm_campaign. */
	private String utm_campaign;
	
	/** The utm_medium. */
	private String utm_medium;
	
	/** The utm_source. */
	private String utm_source;
	
	/** The utm_content. */
	private String utm_content;
	
	/** The open_ga_flg. */
	private int open_ga_flg;
	
	/** The open_pixel_flg. */
	private int open_pixel_flg;
	
	
	/**
	 * Instantiates a new tracking config.
	 */
	public TrackingConfig() {
		
	}


	/**
	 * Instantiates a new tracking config.
	 *
	 * @param id the id
	 * @param broadcast_id the broadcast_id
	 * @param bcast_template_id the bcast_template_id
	 * @param tracking_flg the tracking_flg
	 * @param tracking_type the tracking_type
	 * @param utm_campaign the utm_campaign
	 * @param utm_medium the utm_medium
	 * @param utm_source the utm_source
	 * @param utm_content the utm_content
	 * @param open_ga_flg the open_ga_flg
	 * @param open_pixel_flg the open_pixel_flg
	 */
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
	 * Gets the tracking_flg.
	 *
	 * @return the tracking_flg
	 */
	public int getTracking_flg() {
		return tracking_flg;
	}


	/**
	 * Sets the tracking_flg.
	 *
	 * @param tracking_flg the new tracking_flg
	 */
	public void setTracking_flg(int tracking_flg) {
		this.tracking_flg = tracking_flg;
	}


	/**
	 * Gets the tracking_type.
	 *
	 * @return the tracking_type
	 */
	public String getTracking_type() {
		return tracking_type;
	}


	/**
	 * Sets the tracking_type.
	 *
	 * @param tracking_type the new tracking_type
	 */
	public void setTracking_type(String tracking_type) {
		this.tracking_type = tracking_type;
	}


	/**
	 * Gets the utm_campaign.
	 *
	 * @return the utm_campaign
	 */
	public String getUtm_campaign() {
		return utm_campaign;
	}


	/**
	 * Sets the utm_campaign.
	 *
	 * @param utm_campaign the new utm_campaign
	 */
	public void setUtm_campaign(String utm_campaign) {
		this.utm_campaign = utm_campaign;
	}


	/**
	 * Gets the utm_medium.
	 *
	 * @return the utm_medium
	 */
	public String getUtm_medium() {
		return utm_medium;
	}


	/**
	 * Sets the utm_medium.
	 *
	 * @param utm_medium the new utm_medium
	 */
	public void setUtm_medium(String utm_medium) {
		this.utm_medium = utm_medium;
	}


	/**
	 * Gets the utm_source.
	 *
	 * @return the utm_source
	 */
	public String getUtm_source() {
		return utm_source;
	}


	/**
	 * Sets the utm_source.
	 *
	 * @param utm_source the new utm_source
	 */
	public void setUtm_source(String utm_source) {
		this.utm_source = utm_source;
	}


	/**
	 * Gets the utm_content.
	 *
	 * @return the utm_content
	 */
	public String getUtm_content() {
		return utm_content;
	}


	/**
	 * Sets the utm_content.
	 *
	 * @param utm_content the new utm_content
	 */
	public void setUtm_content(String utm_content) {
		this.utm_content = utm_content;
	}


	/**
	 * Gets the open_ga_flg.
	 *
	 * @return the open_ga_flg
	 */
	public int getOpen_ga_flg() {
		return open_ga_flg;
	}


	/**
	 * Sets the open_ga_flg.
	 *
	 * @param open_ga_flg the new open_ga_flg
	 */
	public void setOpen_ga_flg(int open_ga_flg) {
		this.open_ga_flg = open_ga_flg;
	}


	/**
	 * Gets the open_pixel_flg.
	 *
	 * @return the open_pixel_flg
	 */
	public int getOpen_pixel_flg() {
		return open_pixel_flg;
	}


	/**
	 * Sets the open_pixel_flg.
	 *
	 * @param open_pixel_flg the new open_pixel_flg
	 */
	public void setOpen_pixel_flg(int open_pixel_flg) {
		this.open_pixel_flg = open_pixel_flg;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrackingConfig [id=" + id + ", broadcast_id=" + broadcast_id + ", bcast_template_id="
				+ bcast_template_id + ", tracking_flg=" + tracking_flg + ", tracking_type=" + tracking_type
				+ ", utm_campaign=" + utm_campaign + ", utm_medium=" + utm_medium + ", utm_source=" + utm_source
				+ ", utm_content=" + utm_content + ", open_ga_flg=" + open_ga_flg + ", open_pixel_flg=" + open_pixel_flg
				+ "]";
	}	
}
