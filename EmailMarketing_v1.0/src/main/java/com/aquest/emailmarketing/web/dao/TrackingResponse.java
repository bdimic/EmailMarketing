package com.aquest.emailmarketing.web.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


// TODO: Auto-generated Javadoc
/**
 * The Class TrackingResponse.
 */
@Entity
@Table(name="cm_tracking_response")
public class TrackingResponse {
	
	/** The id. */
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private long id;
	
	/** The unique_id. */
	private long unique_id;
	
	/** The broadcast_id. */
	private String broadcast_id;
	
	/** The email. */
	private String email;
	
	/** The response_type. */
	private String response_type;
	
	/** The response_source. */
	private String response_source;
	
	/** The response_url. */
	private String response_url;
	
	/** The response_time. */
	private Timestamp response_time;
	
	/** The processed_dttm. */
	private Timestamp processed_dttm;
	
	
	/**
	 * Instantiates a new tracking response.
	 */
	public TrackingResponse() {
		
	}


	/**
	 * Instantiates a new tracking response.
	 *
	 * @param id the id
	 * @param unique_id the unique_id
	 * @param broadcast_id the broadcast_id
	 * @param email the email
	 * @param response_type the response_type
	 * @param response_source the response_source
	 * @param response_url the response_url
	 * @param response_time the response_time
	 * @param processed_dttm the processed_dttm
	 */
	public TrackingResponse(long id, long unique_id, String broadcast_id, String email, String response_type,
			String response_source, String response_url, Timestamp response_time, Timestamp processed_dttm) {
		this.id = id;
		this.unique_id = unique_id;
		this.broadcast_id = broadcast_id;
		this.email = email;
		this.response_type = response_type;
		this.response_source = response_source;
		this.response_url = response_url;
		this.response_time = response_time;
		this.processed_dttm = processed_dttm;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * Gets the unique_id.
	 *
	 * @return the unique_id
	 */
	public long getUnique_id() {
		return unique_id;
	}


	/**
	 * Sets the unique_id.
	 *
	 * @param unique_id the new unique_id
	 */
	public void setUnique_id(long unique_id) {
		this.unique_id = unique_id;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * Gets the response_type.
	 *
	 * @return the response_type
	 */
	public String getResponse_type() {
		return response_type;
	}


	/**
	 * Sets the response_type.
	 *
	 * @param response_type the new response_type
	 */
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}


	/**
	 * Gets the response_source.
	 *
	 * @return the response_source
	 */
	public String getResponse_source() {
		return response_source;
	}


	/**
	 * Sets the response_source.
	 *
	 * @param response_source the new response_source
	 */
	public void setResponse_source(String response_source) {
		this.response_source = response_source;
	}


	/**
	 * Gets the response_url.
	 *
	 * @return the response_url
	 */
	public String getResponse_url() {
		return response_url;
	}


	/**
	 * Sets the response_url.
	 *
	 * @param response_url the new response_url
	 */
	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}


	/**
	 * Gets the response_time.
	 *
	 * @return the response_time
	 */
	public Timestamp getResponse_time() {
		return response_time;
	}


	/**
	 * Sets the response_time.
	 *
	 * @param response_time the new response_time
	 */
	public void setResponse_time(Timestamp response_time) {
		this.response_time = response_time;
	}


	/**
	 * Gets the processed_dttm.
	 *
	 * @return the processed_dttm
	 */
	public Timestamp getProcessed_dttm() {
		return processed_dttm;
	}


	/**
	 * Sets the processed_dttm.
	 *
	 * @param processed_dttm the new processed_dttm
	 */
	public void setProcessed_dttm(Timestamp processed_dttm) {
		this.processed_dttm = processed_dttm;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrackingResponse [id=" + id + ", unique_id=" + unique_id + ", broadcast_id=" + broadcast_id + ", email="
				+ email + ", response_type=" + response_type + ", response_source=" + response_source
				+ ", response_url=" + response_url + ", response_time=" + response_time + ", processed_dttm="
				+ processed_dttm + "]";
	}
}
