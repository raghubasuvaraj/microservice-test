package com.sta.settings.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sta.dc.common.utility.SettingsConstants;
import com.sta.settings.enums.ApprovedStatus;
import com.sta.settings.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Persistence layer for the institutesEntity
 * 
 * {@code city}, {@code area} and {@code pinCode} expecting the primary key of
 * those tables (if there is any master table exist for country, state, city, 
 * area and pincode).
 * <p>
 * Will confirm the primary key after the confirmation
 * 
 * <p>
 * Just for now only institute name, code, affiliation number, email and the
 * phone number making as mandatory.
 * <p>
 * Leaving the rest of the fields as optional will implement the validation
 * after the confirmation.
 * 
 * @author r@ghu
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "mse_institutes")
public class InstitutesEntity extends BaseEntity {

	@ManyToOne
	@NotNull(message = SettingsConstants.MANDATORY)
	@JoinColumn(name = "mse_master_institute_group_id")
	private MasterInstituteGroups instituteGroups;

	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "institute_name", unique = true, length = 50)
	private String instituteName;

	@NotNull(message = SettingsConstants.MANDATORY)
	@Column(name = "institute_code", unique = true)
	private Integer instituteCode;

	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "affliation_no", length = 50)
	private String afflicationNo;

	@Column(name = "trust_name", length = 200)
	private String trustName;

	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "emailid", length = 50)
	private String emailid;

	/*
	 * Mobile number/ land line number will implement the checking after the
	 * confirmation
	 */
	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "phoneno", length = 50)
	private String phoneno;

	@Column(name = "address", columnDefinition = "text")
	private String address;

	/*
	 * Expecting city table PK here, after the confirmation relationship will enable
	 */
	@Column(name = "country", length = 50)
	private String country;

	/*
	 * Expecting city table PK here, after the confirmation relationship will enable
	 */
	@Column(name = "state", length = 50)
	private String state;

	/*
	 * Expecting city table PK here, after the confirmation relationship will
	 * enable. <p> This column is not existing in the database architecture but in
	 * UX design
	 */
	@Column(name = "city")
	private String city;

	/*
	 * Expecting area table PK here, after the confirmation relationship will enable
	 */
	@Column(name = "area")
	private String area;

	/*
	 * Expecting pin code table PK here, after the confirmation relationship will
	 * enable. <p> This column is not existing in the database architecture but in
	 * UX design
	 */
	@Column(name = "pincode", length = 50)
	private String pincode;

	@Column(name = "contact_person", length = 50)
	private String contactPerson;

	/*
	 * Mobile number/ land line number will implement the checking after the
	 * confirmation
	 */
	@Column(name = "contact_person_no", length = 50)
	private String contactPersonNo;

	@Column(name = "contact_person_emailid", length = 50)
	private String contactPersonEmailid;

	@Column(name = "licence_type", length = 30)
	private String licenceType;

	@Future(message = SettingsConstants.FUTURE_DATE)
	@Column(name = "license_expiry_date", columnDefinition = "datetime(3)")
	private Date licenseExpiryDate;

	@Pattern(regexp = SettingsConstants.WEBSITE_REGEX, message = SettingsConstants.GIVE_VALID_VALUE)
	@Column(name = "website", length = 50)
	private String website;

	/*
	 * below three columns are interrelated
	 * 
	 * 1. approved_status is APPROVED then
	 * status will be 1, means institute is active. 
	 * 2. By default it'll be PENDING
	 * 3. column comments is optional.
	 */
	@Enumerated(EnumType.ORDINAL)
	private CommonStatus status;

	@Column(name = "comments", columnDefinition = "text")
	private String comments;
}
