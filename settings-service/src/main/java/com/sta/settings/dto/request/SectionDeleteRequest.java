package com.sta.settings.dto.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

@Data
public class SectionDeleteRequest {
	
	@NotEmpty(message = SettingsConstants.MANDATORY)
	private Set<String> sections;
}
