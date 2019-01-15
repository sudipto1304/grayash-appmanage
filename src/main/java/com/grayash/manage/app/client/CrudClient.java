package com.grayash.manage.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grayash.manage.app.config.FeignClientConfiguration;
import com.grayash.manage.app.model.request.OTPRequest;
import com.grayash.manage.app.model.response.OTPResponse;
import com.grayash.manage.app.model.response.Status;


@FeignClient(name = "CrudService", configuration=FeignClientConfiguration.class)
public interface CrudClient {
	
	@RequestMapping(method = RequestMethod.POST, path = "/app/otp/generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	OTPResponse generateOTP(@RequestBody OTPRequest request);
	
	

}
