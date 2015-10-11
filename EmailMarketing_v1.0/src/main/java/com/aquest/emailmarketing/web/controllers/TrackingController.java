/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.EmailListService;
import com.aquest.emailmarketing.web.service.ImportService;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author bdimic
 */
@Controller
public class TrackingController {
	
	private BroadcastService broadcastService;
	
	@Autowired
    public void setBroadcastService(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

	@RequestMapping(value="/openTrack", method = RequestMethod.GET)        
	public void getImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("trackingId") String trackingId) throws IOException, FileNotFoundException, ParserConfigurationException, TransformerException, ServletException, FileUploadException {
		BufferedImage pixel;
		
		System.out.println(trackingId);
		// dodati logiku za ubacivanje response-a.
		
		pixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	    pixel.setRGB(0, 0, (0xFF));
	    
	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(pixel, "png", os);
	    System.out.println("Neko je pristupio!!!");
	}
	
	
}
