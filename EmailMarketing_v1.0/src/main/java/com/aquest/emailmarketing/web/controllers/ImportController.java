/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.dao.EmailListForm;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.EmailListService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

// TODO: Auto-generated Javadoc
/**
 * The Class ImportController.
 */
@Controller
@SessionAttributes("emailListForm")
public class ImportController {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(com.aquest.emailmarketing.web.controllers.ImportController.class);
	
	/** The email list service. */
	private EmailListService emailListService;
	
	/** The broadcast service. */
	private BroadcastService broadcastService;
			
	/**
	 * Sets the email list service.
	 *
	 * @param emailListService the new email list service
	 */
	@Autowired
	public void setEmailListService(EmailListService emailListService) {
		this.emailListService = emailListService;
	}
	
	/**
	 * Sets the broadcast service.
	 *
	 * @param broadcastService the new broadcast service
	 */
	@Autowired
    public void setBroadcastService(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	/**
	 * Import list.
	 *
	 * @param model the model
	 * @param request the request
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException the file not found exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 * @throws ServletException the servlet exception
	 * @throws FileUploadException the file upload exception
	 */
	@RequestMapping(value="/importList", method = RequestMethod.POST)        
	public String ImportList(Model model, HttpServletRequest request) throws IOException, FileNotFoundException, ParserConfigurationException, TransformerException, ServletException, FileUploadException {
            String listfilename = "";
            String separator = "";
            String broadcast_id = "";
            String old_broadcast_id = "";
            EmailListForm emailListForm = new EmailListForm();
            
            InputStream fileContent = null;
            
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    if(fieldName.equals("listfilename")) {
                        listfilename = fieldValue;
                    } else if(fieldName.equals("separator")) {
                        separator = fieldValue;
                    } else if(fieldName.equals("broadcast_id")) {
                    	broadcast_id = fieldValue;
                    } else if(fieldName.equals("old_broadcast_id")) {
                    	old_broadcast_id = fieldValue;
                    }
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    fileContent = item.getInputStream();
                }
                System.out.println(listfilename);
                System.out.println(separator);
            }   
            List<EmailList> eList = emailListService.importEmailfromFile(fileContent, separator, broadcast_id);
            emailListForm.setEmailList(eList);
            System.out.println(broadcast_id);
            int importCount = eList.size();
            model.addAttribute("importCount", importCount);
            model.addAttribute("emailListForm", emailListForm);
            Broadcast broadcast = broadcastService.getBroadcast(broadcast_id);
            System.out.println("Old broadcast: "+old_broadcast_id);
            if(!old_broadcast_id.isEmpty()) {
					model.addAttribute("old_broadcast_id", old_broadcast_id);
            }
            String message = null;
            if(broadcast.getBcast_template_id() != null){
            	message = "template";
            }
            model.addAttribute("message", message);
            model.addAttribute("broadcast_id", broadcast_id);
            return "importlistreport";
	}
	
	/**
	 * Import list report.
	 *
	 * @param emailList the email list
	 * @param model the model
	 * @param broadcast_id the broadcast_id
	 * @param old_broadcast_id the old_broadcast_id
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value="/importListReport", method = RequestMethod.POST)        
	public String ImportListReport(@ModelAttribute("emailListForm") EmailListForm emailList, Model model,
			@RequestParam(value = "broadcast_id") String broadcast_id,
			@RequestParam(value = "old_broadcast_id") String old_broadcast_id,
			HttpServletRequest request) {
		System.out.println(emailList);
		List<EmailList> eList = emailList.getEmailList();
		for(EmailList elist: eList) {
			System.out.println(elist);
			emailListService.SaveOrUpdate(elist);
		}
		//emailListService.SaveOrUpdate(emailList);
		Broadcast broadcast = broadcastService.getBroadcast(broadcast_id);
        System.out.println("Old broadcast: "+old_broadcast_id);
        if(!old_broadcast_id.isEmpty()) {
            try {
				Broadcast old_broadcast = broadcastService.getBroadcast(old_broadcast_id);
				broadcast.setHtmlbody(old_broadcast.getHtmlbody());
				broadcast.setPlaintext(old_broadcast.getPlaintext());
				model.addAttribute("old_broadcast", old_broadcast);
			} catch (Exception e) {
				logger.error(e);
			}
        }
        if(broadcast.getBcast_template_id() == null){
        	model.addAttribute("broadcast", broadcast);
    		return "definecontent";
        }
        else{
        	model.addAttribute("broadcast", broadcast);
        	String message = "template";
			model.addAttribute("message", message);
        	return "sendbroadcast";
        }
	}
}
