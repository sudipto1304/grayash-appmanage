package com.grayash.manage.app.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grayash.manage.app.client.CrudClient;
import com.grayash.manage.app.model.request.OTPRequest;
import com.grayash.manage.app.model.response.OTPResponse;
import com.grayash.manage.app.model.response.Status;
import com.grayash.manage.app.model.response.ValidateOTPResponse;

@Service
public class AppProcessService {
	
	@Autowired
	private CrudClient crudClient;
	
	
	public Status generateOTP(OTPRequest request) {
		OTPResponse response =  crudClient.generateOTP(request);
		return response.getStatus();
	}
	
	
	public Status validateOTP(OTPRequest request) {
		ValidateOTPResponse response =  crudClient.getOTP(request);
		
		if(response==null || StringUtils.isEmpty(response.getOtp())) {
			
		}
	}


}
