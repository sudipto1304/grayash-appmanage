package com.grayash.manage.app.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.github.grayash.exception.InvalidOTPException;
import com.github.grayash.exception.OTPMaxAttemptsException;
import com.github.grayash.exception.PCRuntimeException;
import com.grayash.manage.app.client.CrudClient;
import com.grayash.manage.app.model.request.FlowType;
import com.grayash.manage.app.model.request.InvalidOTPAttemptRequest;
import com.grayash.manage.app.model.request.OTPRequest;
import com.grayash.manage.app.model.request.OTPStatusChangeRequest;
import com.grayash.manage.app.model.response.OTPResponse;
import com.grayash.manage.app.model.response.OTPStatus;
import com.grayash.manage.app.model.response.Status;
import com.grayash.manage.app.model.response.ValidateOTPResponse;
import com.grayash.manage.app.util.CodeConstant;

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
			throw new PCRuntimeException();
		}else {
			if(request.getFlowId().equals(response.getFlowId()) && request.getPhoneNumber().equals(response.getPhoneNumber())) {
				if(response.getAttemptCount()==3) { //account already locked of reached max attempts
					if(response.getFlowId()==FlowType.REGISTRATION) {
						throw new OTPMaxAttemptsException();
					}else {
						throw new InvalidOTPException(3);
					}
				}else {
					if(response.getOtp().equals(request.getOtp())) { //OTP Valid
						OTPStatusChangeRequest statusChangeRequest = new OTPStatusChangeRequest();
						statusChangeRequest.setFlowType(response.getFlowId());
						statusChangeRequest.setPhoneNumber(response.getPhoneNumber());
						statusChangeRequest.setStatus(OTPStatus.VALIDATED);
						crudClient.changeOTPStatus(statusChangeRequest);
						return new Status(CodeConstant.HTTP_OK_STATUS, "SERVER_OK", HttpStatus.OK); 
					}else { //provided invalid OTP
						if(response.getFlowId()==FlowType.REGISTRATION) {
							InvalidOTPAttemptRequest invalidRequest = new InvalidOTPAttemptRequest();
							invalidRequest.setFlowType(response.getFlowId());
							invalidRequest.setPhoneNumber(request.getPhoneNumber());
							crudClient.invalidOTP(invalidRequest);
							if(response.getAttemptCount()+1==3) {
								throw new OTPMaxAttemptsException();
							}else {
								throw new InvalidOTPException(response.getAttemptCount()+1);
							}
						}else {
							//increase count and lock account
							throw new InvalidOTPException(response.getAttemptCount()+1);
						}
						
					}
				}
				
			}else {
				throw new PCRuntimeException();
			}
		}
	}


}
