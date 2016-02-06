package com.aquest.emailmarketing.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.NotBlank;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailConfig.
 */
@Entity
@Table(name="cm_email_config")
public class EmailConfig {
	
	/** The profile_id. */
	@Id 
    @GeneratedValue
    @Column(name = "profile_id")
    private int profile_id;
	
	/** The profile_name. */
	@NotBlank()
	private String profile_name;
	
	/** The hostname. */
	@NotBlank()
	private String hostname;
	
	/** The port. */
	@Max(value=65535)
	private int port;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The from_address. */
	@NotBlank()
	private String from_address;
	
	/** The debug. */
	private boolean debug;
	
	/** The sslonconnect. */
	private boolean sslonconnect;
	
	/** The wait. */
	private long wait;
	
	/** The reply_to. */
	@NotBlank()
	private String reply_to;
	
	/** The bounce_address. */
	private String bounce_address;
	
	/** The bounce_protocol. */
	private String bounce_protocol;
	
	/** The bounce_host. */
	private String bounce_host;
	
	/** The bounce_port. */
	private String bounce_port;
	
	/** The bounce_username. */
	private String bounce_username;
	
	/** The bounce_password. */
	private String bounce_password;
	
	/**
	 * Instantiates a new email config.
	 */
	public EmailConfig() {
		
	}	
	
	/**
	 * Instantiates a new email config.
	 *
	 * @param profile_id the profile_id
	 * @param profile_name the profile_name
	 * @param hostname the hostname
	 * @param port the port
	 * @param username the username
	 * @param password the password
	 * @param from_address the from_address
	 * @param debug the debug
	 * @param sslonconnect the sslonconnect
	 * @param wait the wait
	 * @param reply_to the reply_to
	 * @param bounce_address the bounce_address
	 */
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

	
	/**
	 * Gets the profile_id.
	 *
	 * @return the profile_id
	 */
	public int getProfile_id() {
		return profile_id;
	}

	/**
	 * Sets the profile_id.
	 *
	 * @param profile_id the new profile_id
	 */
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}

	/**
	 * Gets the from_address.
	 *
	 * @return the from_address
	 */
	public String getFrom_address() {
		return from_address;
	}

	/**
	 * Sets the from_address.
	 *
	 * @param from_address the new from_address
	 */
	public void setFrom_address(String from_address) {
		this.from_address = from_address;
	}

	/**
	 * Gets the profile_name.
	 *
	 * @return the profile_name
	 */
	public String getProfile_name() {
		return profile_name;
	}
	
	/**
	 * Sets the profile_name.
	 *
	 * @param profile_name the new profile_name
	 */
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}
	
	/**
	 * Gets the hostname.
	 *
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}
	
	/**
	 * Sets the hostname.
	 *
	 * @param hostname the new hostname
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Checks if is debug.
	 *
	 * @return true, if is debug
	 */
	public boolean isDebug() {
		return debug;
	}
	
	/**
	 * Sets the debug.
	 *
	 * @param debug the new debug
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	/**
	 * Checks if is sslonconnect.
	 *
	 * @return true, if is sslonconnect
	 */
	public boolean isSslonconnect() {
		return sslonconnect;
	}
	
	/**
	 * Sets the sslonconnect.
	 *
	 * @param sslonconnect the new sslonconnect
	 */
	public void setSslonconnect(boolean sslonconnect) {
		this.sslonconnect = sslonconnect;
	}
	
	/**
	 * Gets the wait.
	 *
	 * @return the wait
	 */
	public long getWait() {
		return wait;
	}
	
	/**
	 * Sets the wait.
	 *
	 * @param wait the new wait
	 */
	public void setWait(long wait) {
		this.wait = wait;
	}
	
	/**
	 * Gets the reply_to.
	 *
	 * @return the reply_to
	 */
	public String getReply_to() {
		return reply_to;
	}
	
	/**
	 * Sets the reply_to.
	 *
	 * @param reply_to the new reply_to
	 */
	public void setReply_to(String reply_to) {
		this.reply_to = reply_to;
	}
	
	/**
	 * Gets the bounce_address.
	 *
	 * @return the bounce_address
	 */
	public String getBounce_address() {
		return bounce_address;
	}
	
	/**
	 * Sets the bounce_address.
	 *
	 * @param bounce_address the new bounce_address
	 */
	public void setBounce_address(String bounce_address) {
		this.bounce_address = bounce_address;
	}

	/**
	 * Gets the bounce_protocol.
	 *
	 * @return the bounce_protocol
	 */
	public String getBounce_protocol() {
		return bounce_protocol;
	}

	/**
	 * Sets the bounce_protocol.
	 *
	 * @param bounce_protocol the new bounce_protocol
	 */
	public void setBounce_protocol(String bounce_protocol) {
		this.bounce_protocol = bounce_protocol;
	}

	/**
	 * Gets the bounce_host.
	 *
	 * @return the bounce_host
	 */
	public String getBounce_host() {
		return bounce_host;
	}

	/**
	 * Sets the bounce_host.
	 *
	 * @param bounce_host the new bounce_host
	 */
	public void setBounce_host(String bounce_host) {
		this.bounce_host = bounce_host;
	}

	/**
	 * Gets the bounce_port.
	 *
	 * @return the bounce_port
	 */
	public String getBounce_port() {
		return bounce_port;
	}

	/**
	 * Sets the bounce_port.
	 *
	 * @param bounce_port the new bounce_port
	 */
	public void setBounce_port(String bounce_port) {
		this.bounce_port = bounce_port;
	}

	/**
	 * Gets the bounce_username.
	 *
	 * @return the bounce_username
	 */
	public String getBounce_username() {
		return bounce_username;
	}

	/**
	 * Sets the bounce_username.
	 *
	 * @param bounce_username the new bounce_username
	 */
	public void setBounce_username(String bounce_username) {
		this.bounce_username = bounce_username;
	}

	/**
	 * Gets the bounce_password.
	 *
	 * @return the bounce_password
	 */
	public String getBounce_password() {
		return bounce_password;
	}

	/**
	 * Sets the bounce_password.
	 *
	 * @param bounce_password the new bounce_password
	 */
	public void setBounce_password(String bounce_password) {
		this.bounce_password = bounce_password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailConfig [profile_id=" + profile_id + ", profile_name=" + profile_name + ", hostname=" + hostname
				+ ", port=" + port + ", username=" + username + ", password=" + password + ", from_address="
				+ from_address + ", debug=" + debug + ", sslonconnect=" + sslonconnect + ", wait=" + wait
				+ ", reply_to=" + reply_to + ", bounce_address=" + bounce_address + ", bounce_protocol="
				+ bounce_protocol + ", bounce_host=" + bounce_host + ", bounce_port=" + bounce_port
				+ ", bounce_username=" + bounce_username + ", bounce_password=" + bounce_password + "]";
	}
}
