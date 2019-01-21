package com.grayash.manage.app.model.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorMessageResponse {
	
	public ErrorMessageResponse(String msgCode, String msgText) {
		this.msgCode = msgCode;
		this.msgText = msgText;
	}
	
	public ErrorMessageResponse() {}
	
	private String msgCode;
	private String msgText;

}
