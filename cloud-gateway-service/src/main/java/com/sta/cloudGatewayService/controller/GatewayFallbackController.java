package com.sta.cloudGatewayService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sta.cloudGatewayService.dto.response.FallbackResponse;
import com.sta.cloudGatewayService.utilities.Constants;

@RestController
@RequestMapping(value = "api/v1/fallback")
public class GatewayFallbackController {
	
	@GetMapping("/api-gateway")
    public FallbackResponse apiGatewayFallBack() {
        return new FallbackResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), Constants.GATEWAY_FALL_BACK_MSG);
    }
	
	@GetMapping("/auth-service")
    public FallbackResponse authServiceFallBack() {
        return new FallbackResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), "Auth "+Constants.FALL_BACK_MSG);
    }
	
	@GetMapping("/institute-service")
    public FallbackResponse instituteServiceFallBack() {
        return new FallbackResponse(HttpStatus.SERVICE_UNAVAILABLE.value(), "Institute "+Constants.FALL_BACK_MSG);
    }
}
