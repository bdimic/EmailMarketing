/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.dao.EmailListDao;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailListService.
 */
@Service("emailListService")
public class EmailListService {
    
    /** The email list dao. */
    private EmailListDao emailListDao;

    /**
     * Sets the email list dao.
     *
     * @param emailListDao the new email list dao
     */
    @Autowired
    public void setEmailListDao(EmailListDao emailListDao) {
        this.emailListDao = emailListDao;
    }
    
    /**
     * Gets the all email list.
     *
     * @param broadcast_id the broadcast_id
     * @return the all email list
     */
    public List<EmailList> getAllEmailList(String broadcast_id) {
    	return emailListDao.getAllEmailList(broadcast_id);
    }
    
    /**
     * Gets the sent email list.
     *
     * @param broadcast_id the broadcast_id
     * @return the sent email list
     */
    public List<EmailList> getSentEmailList(String broadcast_id) {
    	return emailListDao.getSentEmailList(broadcast_id);
    }
    
    /**
     * Gets the email list by id.
     *
     * @param id the id
     * @return the email list by id
     */
    public EmailList getEmailListById(String id) {
    	return emailListDao.getEmailListById(id);
    }

    /**
     * Import emailfrom file.
     *
     * @param filename the filename
     * @param separator the separator
     * @param broadcast_id the broadcast_id
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public List<EmailList> importEmailfromFile(InputStream filename, String separator, String broadcast_id) throws IOException {
    	CSVReader reader = new CSVReader(new InputStreamReader(filename), separator.charAt(0));
        boolean isFirstLine = true;
        boolean valid = true;
        String[] firstLine = null;
        String[] nextLine;
        List<String> notValid = new ArrayList<String>();
        List<EmailList> eList = new ArrayList<EmailList>();
        Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        
        System.out.println(broadcast_id);
        
        while ((nextLine = reader.readNext()) != null) {
        	        	        	        	
            if(isFirstLine) {
                firstLine = nextLine;
                // print list
                for(int k=0;k<firstLine.length;k++){
                    System.out.println(firstLine[k]);
                }
                isFirstLine = false;
            } else {
            	EmailList emailList = new EmailList();
            	
                for(int i=0; i < firstLine.length; i++) {                	
                	System.out.println(firstLine.length);
                	// ovo je dirty verzija. napisati kako treba
                	valid = EmailValidator.getInstance().isValid(nextLine[0]);
                	for(int j=0;j<nextLine.length;j++){
                        System.out.println(nextLine[j]);                        
                    }
                	if(valid == true) {
	                    if(i==0){
	                    	//DONE: add email address validation
	                    	emailList.setBroadcast_id(broadcast_id);
	                    	emailList.setEmail(nextLine[i]);
	                    	System.out.println("Email: "+nextLine[i]);
	                    } else if(i==1) {
	                    	emailList.setName1(firstLine[i]);
	                    	System.out.println("Name1: "+firstLine[i]);
	                    	emailList.setValue1(nextLine[i]);
	                    	System.out.println("Value1: "+nextLine[i]);
	                    }  else if(i==2) {
	                    	emailList.setName2(firstLine[i]);
	                    	emailList.setValue2(nextLine[i]);
	                    } else if(i==3) {
	                    	emailList.setName3(firstLine[i]);
	                    	emailList.setValue3(nextLine[i]);
	                    } else if(i==4) {
	                    	emailList.setName4(firstLine[i]);
	                    	emailList.setValue4(nextLine[i]);
	                    } else if(i==5) {
	                    	emailList.setName5(firstLine[i]);
	                    	emailList.setValue5(nextLine[i]);
	                    } else if(i==6) {
	                    	emailList.setName6(firstLine[i]);
	                    	emailList.setValue6(nextLine[i]);
	                    } else if(i==7) {
	                    	emailList.setName7(firstLine[i]);
	                    	emailList.setValue7(nextLine[i]);
	                    } else if(i==8) {
	                    	emailList.setName8(firstLine[i]);
	                    	emailList.setValue8(nextLine[i]);
	                    } else if(i==9) {
	                    	emailList.setName9(firstLine[i]);
	                    	emailList.setValue9(nextLine[i]);
	                    } else if(i==10) {
	                    	emailList.setName10(firstLine[i]);
	                    	emailList.setValue10(nextLine[i]);
	                    }
	                    System.out.println(emailList);
	                    emailList.setStatus("READY");
	                    emailList.setProcess_dttm(curTimestamp);
	                  
	                    
                	} else {
                		notValid.add(nextLine[0]);
                		System.out.println(nextLine[0]);
                	}
                }      
                if(emailList.getBroadcast_id() != null) {
	                //Save i update ce se raditi u drugoj metodi
	                //emailListDao.saveOrUpdate(emailList);
	                eList.add(emailList);
                }
            }
        }
        for(EmailList elist : eList) {
        	System.out.println(elist);
        }
        reader.close();
        return eList;
    }
    
    /**
     * Save or update.
     *
     * @param emailList the email list
     */
    public void SaveOrUpdate(EmailList emailList) {
    	emailListDao.saveOrUpdate(emailList);
    }
    
    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
    	emailListDao.delete(id);
    }
    
    public boolean exists(String broadcast_id, String email) {
    	return emailListDao.exists(broadcast_id, email);
    } 
}
