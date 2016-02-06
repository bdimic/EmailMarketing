/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.BounceCode;
import com.aquest.emailmarketing.web.dao.BounceCodeDao;

// TODO: Auto-generated Javadoc
/**
 * The Class BounceCodeService.
 */
@Service("bounceCodeService")
public class BounceCodeService {
	
	/** The bounce code dao. */
	private BounceCodeDao bounceCodeDao;

	/**
	 * Sets the bounce code dao.
	 *
	 * @param bounceCodeDao the new bounce code dao
	 */
	@Autowired
	public void setBounceCodeDao(BounceCodeDao bounceCodeDao) {
		this.bounceCodeDao = bounceCodeDao;
	}
	
	/**
	 * Gets the all bounce codes.
	 *
	 * @return the all bounce codes
	 */
	public List<BounceCode> getAllBounceCodes() {
		return bounceCodeDao.getAllBounceCodes();
	}
}
