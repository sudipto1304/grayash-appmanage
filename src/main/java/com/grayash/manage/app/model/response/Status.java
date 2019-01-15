package com.grayash.manage.app.model.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

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
