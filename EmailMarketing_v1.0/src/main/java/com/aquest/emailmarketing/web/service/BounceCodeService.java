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

/**
 *
 * @author bdimic
 */
@Service("bounceCodeService")
public class BounceCodeService {
	
	private BounceCodeDao bounceCodeDao;

	@Autowired
	public void setBounceCodeDao(BounceCodeDao bounceCodeDao) {
		this.bounceCodeDao = bounceCodeDao;
	}
	
	public List<BounceCode> getAllBounceCodes() {
		return bounceCodeDao.getAllBounceCodes();
	}
}
