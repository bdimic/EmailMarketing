/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.EmailList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bdimic
 */
@Controller
public class ImportController {
	
	private EmailListService emailListService;
	private BroadcastService broadcastService;
		
	@Autowired
	public void setEmailListService(EmailListService emailListService) {
		this.emailListService = emailListService;
	}
	
	@Autowired
    public void setBroadcastService(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@RequestMapping(value="/importList", method = RequestMethod.POST)        
	public String ImportList(Model model, HttpServletRequest request) throws IOException, FileNotFoundException, ParserConfigurationException, TransformerException, ServletException, FileUploadException {
            String listfilename = "";
            String separator = "";
            String broadcast_id = "";
            String old_broadcast_id = "";
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
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    fileContent = item.getInputStream();
                    // ... (do your job here)
                }
                System.out.println(listfilename);
                System.out.println(separator);
            }   
            List<EmailList> eList = emailListService.importEmailfromFile(fileContent, separator, broadcast_id);
            System.out.println(broadcast_id);
            int importCount = eList.size();
            model.addAttribute("importCount", importCount);
            model.addAttribute("eList", eList);
            //Broadcast broadcast = broadcastService.getBroadcast(broadcast_id);
            System.out.println("Old broadcast: "+old_broadcast_id);
            if(!old_broadcast_id.isEmpty()) {
					model.addAttribute("old_broadcast_id", old_broadcast_id);
            }
            		model.addAttribute("broadcast_id", broadcast_id);
		return "importlistreport";
	}
	
	@RequestMapping(value="/importListReport", method = RequestMethod.POST)        
	public String ImportListReport(@ModelAttribute("eList") List<EmailList> emailList, Model model, HttpServletRequest request) {
		String broadcast_id = "";
		String old_broadcast_id = "";
		for(EmailList elist: emailList) {
			System.out.println(elist);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        model.addAttribute("broadcast", broadcast);
		return "definecontent";
	}
}
