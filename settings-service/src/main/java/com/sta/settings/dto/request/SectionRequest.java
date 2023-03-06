package com.sta.settings.dto.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

/** 
 * @author r@ghu
 *
 */
@Data
public class SectionRequest {

	@NotEmpty(message = SettingsConstants.MANDATORY)
	private Set<SectionAlternativeRequest> sections;
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	private String classId;
}
