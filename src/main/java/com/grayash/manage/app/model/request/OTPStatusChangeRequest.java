package com.grayash.manage.app.model.request;

import java.io.Serializable;

import com.grayash.manage.app.model.response.OTPStatus;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class OTPStatusChangeRequest implements Serializable{

	private String phoneNumber;
	private FlowType flowType;
	private OTPStatus status;
}
