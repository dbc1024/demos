package com.its.sims.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseConfig {

	public static String SIMS_NAME;

	@Value("#{simsConfig['sims.name']}")
	public void setSIMS_NAME(String sIMS_NAME) {
		SIMS_NAME = sIMS_NAME;
	}
	
	
}
