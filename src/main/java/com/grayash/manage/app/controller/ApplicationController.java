package com.grayash.manage.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/app")
public class ApplicationController {
	
	@RequestMapping(value="/statuscheck", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStatus(HttpServletRequest servletRequest) {
        
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

}
