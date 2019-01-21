package com.grayash.manage.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.github.grayash.exception.InvalidOTPException;
import com.github.grayash.exception.OTPMaxAttemptsException;
import com.github.grayash.exception.PCRuntimeException;
import com.grayash.auditactivity.utils.CommonUtils;
import com.grayash.manage.app.client.CrudClient;
import com.grayash.manage.app.model.response.Status;
import com.grayash.manage.app.util.CodeConstant;

@SuppressWarnings("Duplicates")
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler implements CodeConstant {

	private static final Logger Log = LoggerFactory.getLogger(AppExceptionHandler.class);
	
	@Autowired
	private CrudClient client;

	@ExceptionHandler(InvalidOTPException.class)
	protected ResponseEntity<Object> handleGlobalException(InvalidOTPException ex, WebRequest request) {
		int count = ex.getOtpErrorCount();
		Status status = new Status();
		int leftCount = 3 - count;
		if (leftCount > 0) {
			status.setResponseCode(MSG_00006);
			status.setResponseMsg(MSG_00006);
			status.setResponseMsg(client.getErrorMsg(MSG_00006).getMsgText().replace("$#$", String.valueOf(leftCount)));
			status.setHttpCode(HttpStatus.NOT_ACCEPTABLE);
		} else if (leftCount == 0) {
			status.setResponseCode(MSG_00007);
			status.setResponseMsg(client.getErrorMsg(MSG_00007).getMsgText());
			status.setHttpCode(HttpStatus.NOT_ACCEPTABLE);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status), headers,
				HttpStatus.NOT_ACCEPTABLE, request);
	}
	
	@ExceptionHandler(OTPMaxAttemptsException.class)
	protected ResponseEntity<Object> handleGlobalException(OTPMaxAttemptsException ex, WebRequest request) {
		if(Log.isErrorEnabled())
            Log.error("Exception::", ex);
        Status status  = new Status();
        status.setResponseCode(MSG_00008);
        status.setResponseMsg(client.getErrorMsg(MSG_00008).getMsgText());
        status.setHttpCode(HttpStatus.NOT_ACCEPTABLE);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	
	@ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        if(Log.isErrorEnabled())
            Log.error("Exception::", ex);
        Status status  = new Status();
        status.setResponseCode(MSG_99999);
        status.setResponseMsg(client.getErrorMsg(MSG_99999).getMsgText());
        status.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
	
	@ExceptionHandler({PCRuntimeException.class})
    protected ResponseEntity<Object> handleGlobalException(PCRuntimeException ex, WebRequest request) {
        if(Log.isErrorEnabled())
            Log.error("Exception::", ex);
        Status status  = new Status();
        status.setResponseCode(MSG_99999);
        status.setResponseMsg(client.getErrorMsg(MSG_99999).getMsgText());
        status.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, CommonUtils.constructJsonResponse(status),
                headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
