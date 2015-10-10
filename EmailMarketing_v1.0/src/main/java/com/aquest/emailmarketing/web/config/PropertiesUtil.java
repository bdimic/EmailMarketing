/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	private static Map properties = new HashMap();
	
	@Override
	protected void loadProperties(final Properties props) throws IOException {
		super.loadProperties(props);
		for (final Object key : props.keySet()) {
			properties.put((String) key, props.getProperty((String) key));
		}
	}
	
	/**
	* Return a property loaded by the place holder.
	* @param name the property name.
	* @return the property value.
	*/
	public static Object getProperty(final String name) {
		return properties.get(name);
	}
}