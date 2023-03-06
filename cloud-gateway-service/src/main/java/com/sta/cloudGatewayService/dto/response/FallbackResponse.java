package com.sta.cloudGatewayService.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class FallbackResponse {
	
	private int httpCode;
	
	private String message;
}
