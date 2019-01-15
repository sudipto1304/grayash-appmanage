package com.grayash.manage.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grayash.manage.app.model.request.OTPRequest;
import com.grayash.manage.app.model.response.OTPResponse;
import com.grayash.manage.app.model.response.Status;
import com.grayash.manage.app.service.AppProcessService;
import com.grayash.manage.app.util.CodeConstant;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping("/app")
public class ApplicationController implements CodeConstant{
	
	private static final Logger Log = LoggerFactory.getLogger(ApplicationController.class);
	
	
	@Autowired
	private AppProcessService service;
	
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 426, message = "Upgrade Required"),
            @ApiResponse(code = 406, message = "Not Acceptable"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/statuscheck", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> getStatus(HttpServletRequest servletRequest) {
        Status status = new Status("200_OK", "SERVER_OK", HttpStatus.OK);
		
        if(Log.isDebugEnabled())
            Log.debug("returning response "+status);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
	
	
	@ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted", response = Status.class),
            @ApiResponse(code = 412, message = "Precondition Failed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @RequestMapping(value="/otp/generate", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> generateOtp(@RequestBody OTPRequest request, HttpServletRequest servletRequest) {
        if(Log.isDebugEnabled())
            Log.debug("Request to generate OTP for the customerId::"+request);
        Status response = service.generateOTP(request);
        if(Log.isDebugEnabled())
            Log.debug("returning response "+response);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
