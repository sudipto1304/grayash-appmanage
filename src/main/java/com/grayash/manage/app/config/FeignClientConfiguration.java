package com.grayash.manage.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignClientConfiguration {
	
	private static final Logger Log = LoggerFactory.getLogger(FeignClientConfiguration.class);
	
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {

	    return new RequestInterceptor() {
	        @Override
	        public void apply(RequestTemplate requestTemplate) {
	        	try {
	        		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		            requestTemplate.header("Authorization", authentication.getCredentials().toString());   
				} catch (Exception e) {
					if(Log.isErrorEnabled())
						Log.error("FeignClientConfiguration error::"+e.getMessage());
				}
	        	                
	        }
	    };
	}

}
