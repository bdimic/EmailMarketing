package com.aquest.emailmarketing.web.dao;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailListForm.
 */
public class EmailListForm {
	
	/** The email list. */
	private List<EmailList> emailList;

	/**
	 * Gets the email list.
	 *
	 * @return the email list
	 */
	public List<EmailList> getEmailList() {
		return emailList;
	}

	/**
	 * Sets the email list.
	 *
	 * @param emailList the new email list
	 */
	public void setEmailList(List<EmailList> emailList) {
		this.emailList = emailList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailListForm [emailList=" + emailList + "]";
	}
	
	
}
