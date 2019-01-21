package com.grayash.manage.app.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InvalidOTPAttemptRequest implements Serializable{
	
	private String phoneNumber;
	private FlowType flowType;
	private int count;

}
