package com.sta.settings.dto.request;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.sta.dc.common.utility.SettingsConstants;

import lombok.Data;

/**
 * Request data transfer object(DTO) for institute entity.
 * <p>
 * Except institute name, code, affiliation number, email and phone number other
 * fields leaving as optional.
 * 
 * @author r@ghu
 *
 */
@Data
public class InstitutesRequest {
	
	//Insert it if the institute is update
	private String id;
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	private String instituteGroupId;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String instituteName;

	@NotNull(message = SettingsConstants.MANDATORY)
	private Integer instituteCode;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String afflicationNo;

	private String trustName;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String institutionEmail;

	@NotBlank(message = SettingsConstants.MANDATORY)
	private String institutionPhoneNo;

	private String address;

	private String city;

	private String area;

	private String pinCode;

	private String contactPersonName;

	private String contactPersonPhoneNo;

	private String contactPersonEmail;

	private String licenceType;

	@Future(message = SettingsConstants.FUTURE_DATE)
	private Date expiryDate;

	@Pattern(regexp = SettingsConstants.WEBSITE_REGEX, message = SettingsConstants.GIVE_VALID_VALUE)
	private String websiteUrl;
}

