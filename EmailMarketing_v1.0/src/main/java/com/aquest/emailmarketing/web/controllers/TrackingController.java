/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.dao.TrackingResponse;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.EmailListService;
import com.aquest.emailmarketing.web.service.GoogleAnalyticsService;
import com.aquest.emailmarketing.web.service.TrackingResponseService;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Auto-generated Javadoc
/**
 * The Class TrackingController.
 */
@Controller
public class TrackingController {
	
	/** The tracking response service. */
	private TrackingResponseService trackingResponseService;
	
	/** The email list service. */
	private EmailListService emailListService;
	
	/** The google analytics service. */
	private GoogleAnalyticsService googleAnalyticsService;
	
	/**
	 * Sets the tracking response service.
	 *
	 * @param trackingResponseService the new tracking response service
	 */
	@Autowired
    public void setTrackingResponseService(TrackingResponseService trackingResponseService) {
		this.trackingResponseService = trackingResponseService;
	}
	
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
	}	
	
	/**
	 * Sets the google analytics service.
	 *
	 * @param googleAnalyticsService the new google analytics service
	 */
	@Autowired
	public void setGoogleAnalyticsService(GoogleAnalyticsService googleAnalyticsService) {
		this.googleAnalyticsService = googleAnalyticsService;
	}

	/**
	 * Gets the image.
	 *
	 * @param request the request
	 * @param response the response
	 * @param trackingId the tracking id
	 * @return the image
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException the file not found exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws TransformerException the transformer exception
	 * @throws ServletException the servlet exception
	 * @throws FileUploadException the file upload exception
	 */
	@RequestMapping(value="/openTrack", method = RequestMethod.GET)        
	public void getImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("trackingId") String trackingId) throws IOException, FileNotFoundException, ParserConfigurationException, TransformerException, ServletException, FileUploadException {
		BufferedImage pixel;
		Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
		EmailList emailList = emailListService.getEmailListById(trackingId);
		TrackingResponse trackingResponse = new TrackingResponse();
		trackingResponse.setBroadcast_id(emailList.getBroadcast_id());
		trackingResponse.setEmail(emailList.getEmail());
		trackingResponse.setResponse_type("Open");
		trackingResponse.setUnique_id(emailList.getId());
		trackingResponse.setResponse_source("Internal Tracking");
		trackingResponse.setResponse_time(curTimestamp);
		trackingResponse.setProcessed_dttm(curTimestamp);
		trackingResponseService.SaveOrUpdate(trackingResponse);
		
		System.out.println(trackingId);
		// dodati logiku za ubacivanje response-a.
		
		pixel = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	    pixel.setRGB(0, 0, (0xFF));
	    
	    response.setContentType("image/png");
	    OutputStream os = response.getOutputStream();
	    ImageIO.write(pixel, "png", os);
	    System.out.println("Neko je pristupio!!!");
	}
	
	/**
	 * Gets the tracking link.
	 *
	 * @param request the request
	 * @param response the response
	 * @param id the id
	 * @return the tracking link
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value="/tracking", method = RequestMethod.GET)
	public void getTrackingLink(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) throws IOException {
		Base64 base64 = new Base64(true);
		String decriptedText = new String(base64.decode(id.getBytes()));
        String unique_id = decriptedText.substring(decriptedText.indexOf("trackingId=")+11);
		String url = decriptedText.substring(0, decriptedText.indexOf("trackingId=")-1);
		System.out.println(url);
		response.sendRedirect(url);
		
		Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
		EmailList emailList = emailListService.getEmailListById(unique_id);
		TrackingResponse trackingResponse = new TrackingResponse();
		trackingResponse.setBroadcast_id(emailList.getBroadcast_id());
		trackingResponse.setEmail(emailList.getEmail());
		trackingResponse.setResponse_type("Click");
		trackingResponse.setUnique_id(emailList.getId());
		trackingResponse.setResponse_source("Internal Tracking");
		trackingResponse.setResponse_url(url);
		trackingResponse.setResponse_time(curTimestamp);
		trackingResponse.setProcessed_dttm(curTimestamp);
		trackingResponseService.SaveOrUpdate(trackingResponse);
	}
	
	/**
	 * Gets the ga responses.
	 *
	 * @return the ga responses
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws GeneralSecurityException 
	 * @throws KeyStoreException 
	 */
	@RequestMapping("/garesponse")
	public void getGaResponses() throws IOException, KeyStoreException, GeneralSecurityException {
		googleAnalyticsService.getGaClickResponses();
		googleAnalyticsService.getGaOpenResponses();
	}
}
