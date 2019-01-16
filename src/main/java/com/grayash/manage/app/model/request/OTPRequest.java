package com.grayash.manage.app.model.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class OTPRequest extends CommonRequest implements Serializable{

	private String phoneNumber;
	private String flowId;
	private String countryCode;
}
