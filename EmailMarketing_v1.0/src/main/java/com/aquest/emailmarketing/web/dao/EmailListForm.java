package com.aquest.emailmarketing.web.dao;

import java.util.List;

public class EmailListForm {
	private List<EmailList> emailList;

	public List<EmailList> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<EmailList> emailList) {
		this.emailList = emailList;
	}

	@Override
	public String toString() {
		return "EmailListForm [emailList=" + emailList + "]";
	}
	
	
}
