package com.sta.settings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SectionsResponse {
	
	private String sectionId;
	
	private String sectionName;
	
	private boolean active;
}
