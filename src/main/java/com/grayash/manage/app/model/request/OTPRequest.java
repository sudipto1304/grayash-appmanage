package com.grayash.manage.app.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class OTPRequest extends CommonRequest implements Serializable{

	private String phoneNumber;
	private FlowType flowId;
	private String countryCode;
	private String otp;
}
