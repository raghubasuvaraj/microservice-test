package com.sta.settings.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response data transfer object(DTO) for institute entity.
 *  
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class InstitutesResponse {

	private String id;

	private String instituteName;

	private int instituteCode;

	private String afflicationNo;

	private String trustName;

	private String institutionEmail;

	private String institutionPhoneNo;

	private String address;

	private String city;

	private String area;

	private String pinCode;

	private String contactPersonName;

	private String contactPersonPhoneNo;

	private String contactPersonEmail;

	private String licenceType;

	private Date expiryDate;

	private String websiteUrl;

	private boolean active;
}
