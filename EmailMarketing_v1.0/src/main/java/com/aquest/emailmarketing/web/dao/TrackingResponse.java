package com.aquest.emailmarketing.web.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="cm_tracking_response")
public class TrackingResponse {
	
	@Id 
    @GeneratedValue
    @Column(name = "id")
	private long id;
	private long unique_id;
	private String broadcast_id;
	private String email;
	private String response_type;
	private String response_source;
	private String response_url;
	private Timestamp response_time;
	private Timestamp processed_dttm;
	
	
	public TrackingResponse() {
		
	}


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


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUnique_id() {
		return unique_id;
	}


	public void setUnique_id(long unique_id) {
		this.unique_id = unique_id;
	}


	public String getBroadcast_id() {
		return broadcast_id;
	}


	public void setBroadcast_id(String broadcast_id) {
		this.broadcast_id = broadcast_id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getResponse_type() {
		return response_type;
	}


	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}


	public String getResponse_source() {
		return response_source;
	}


	public void setResponse_source(String response_source) {
		this.response_source = response_source;
	}


	public String getResponse_url() {
		return response_url;
	}


	public void setResponse_url(String response_url) {
		this.response_url = response_url;
	}


	public Timestamp getResponse_time() {
		return response_time;
	}


	public void setResponse_time(Timestamp response_time) {
		this.response_time = response_time;
	}


	public Timestamp getProcessed_dttm() {
		return processed_dttm;
	}


	public void setProcessed_dttm(Timestamp processed_dttm) {
		this.processed_dttm = processed_dttm;
	}


	@Override
	public String toString() {
		return "TrackingResponse [id=" + id + ", unique_id=" + unique_id + ", broadcast_id=" + broadcast_id + ", email="
				+ email + ", response_type=" + response_type + ", response_source=" + response_source
				+ ", response_url=" + response_url + ", response_time=" + response_time + ", processed_dttm="
				+ processed_dttm + "]";
	}
}
