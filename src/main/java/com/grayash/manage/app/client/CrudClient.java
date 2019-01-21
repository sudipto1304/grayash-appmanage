package com.grayash.manage.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grayash.manage.app.config.FeignClientConfiguration;
import com.grayash.manage.app.model.request.InvalidOTPAttemptRequest;
import com.grayash.manage.app.model.request.OTPRequest;
import com.grayash.manage.app.model.request.OTPStatusChangeRequest;
import com.grayash.manage.app.model.response.ErrorMessageResponse;
import com.grayash.manage.app.model.response.OTPResponse;
import com.grayash.manage.app.model.response.Status;
import com.grayash.manage.app.model.response.ValidateOTPResponse;


@FeignClient(name = "CrudService", configuration=FeignClientConfiguration.class)
public interface CrudClient {
	
	@RequestMapping(method = RequestMethod.POST, path = "/app/otp/generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	OTPResponse generateOTP(@RequestBody OTPRequest request);
	
	@RequestMapping(method = RequestMethod.POST, path = "/app/otp/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ValidateOTPResponse getOTP(@RequestBody OTPRequest request);
	
	@RequestMapping(method = RequestMethod.GET, path = "/app/message/{msgId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ErrorMessageResponse getErrorMsg(@PathVariable(value = "msgId") String msgId);
	
	@RequestMapping(method = RequestMethod.POST, path = "/app/OTP/invalid", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Status invalidOTP(@RequestBody InvalidOTPAttemptRequest request);
	
	@Async
	@RequestMapping(method = RequestMethod.POST, path = "/app/OTP/change/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	void changeOTPStatus(@RequestBody OTPStatusChangeRequest request);
	
	

}
