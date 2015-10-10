package com.aquest.emailmarketing.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="cm_email_config")
public class EmailConfig {
	@Id 
    @GeneratedValue
    @Column(name = "profile_id")
    private int profile_id;
	@NotBlank()
	private String profile_name;
	@NotBlank()
	private String hostname;
	@Max(value=65535)
	private int port;
	private String username;
	private String password;
	@NotBlank()
	private String from_address;
	private boolean debug;
	private boolean sslonconnect;
	private long wait;
	@NotBlank()
	private String reply_to;
	private String bounce_address;
	
	public EmailConfig() {
		
	}	
	
	public EmailConfig(int profile_id, String profile_name, String hostname, int port, String username,
			String password, String from_address, boolean debug, boolean sslonconnect,
			long wait, String reply_to, String bounce_address) {
		this.profile_id = profile_id;
		this.profile_name = profile_name;
		this.hostname = hostname;
		this.port = port;
		this.username = username;
		this.password = password;
		this.from_address = from_address;
		this.debug = debug;
		this.sslonconnect = sslonconnect;
		this.wait = wait;
		this.reply_to = reply_to;
		this.bounce_address = bounce_address;
	}

	
	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}

	public String getFrom_address() {
		return from_address;
	}

	public void setFrom_address(String from_address) {
		this.from_address = from_address;
	}

	public String getProfile_name() {
		return profile_name;
	}
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public boolean isSslonconnect() {
		return sslonconnect;
	}
	public void setSslonconnect(boolean sslonconnect) {
		this.sslonconnect = sslonconnect;
	}
	public long getWait() {
		return wait;
	}
	public void setWait(long wait) {
		this.wait = wait;
	}
	public String getReply_to() {
		return reply_to;
	}
	public void setReply_to(String reply_to) {
		this.reply_to = reply_to;
	}
	public String getBounce_address() {
		return bounce_address;
	}
	public void setBounce_address(String bounce_address) {
		this.bounce_address = bounce_address;
	}

	@Override
	public String toString() {
		return "EmailConfig [profile_id=" + profile_id + ", profile_name=" + profile_name
				+ ", hostname=" + hostname + ", port=" + port + ", username="
				+ username + ", password=" + password + ", from=" + from_address
				+ ", debug=" + debug + ", sslonconnect=" + sslonconnect
				+ ", wait=" + wait + ", reply_to=" + reply_to
				+ ", bounce_address=" + bounce_address + "]";
	}
}
