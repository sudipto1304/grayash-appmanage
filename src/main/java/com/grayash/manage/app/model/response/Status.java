package com.grayash.manage.app.model.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Status implements Serializable {

    private String responseCode;
    private String responseMsg;
    private HttpStatus httpCode;


    public Status(){

    }


    public Status(String responseCode, String responseMsg, HttpStatus httpCode){
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.httpCode = httpCode;
    }

}
