package com.sta.dc.common.utility;

import org.springframework.http.HttpStatus;

public class SettingsConstants {

	public static final String MANDATORY = "is a mandatory filed.";

	public static final String FUTURE_DATE = "expecting upcoming date here.";

	public static final String GIVE_VALID_VALUE = "please enter a valid data.";

	public static final String BASIC_EMAIL_REGEX = ".*@.*\\..*";

	/* will accept these type of name Fran�ois, O'Brian etc */
	public static final String UNICOD_NAME_REGEX = "^[\\p{L} .'-]+$";

	public static final String EMAIL_REGEX = "^[\\p{L}\\d\\+-]+(?:\\.[\\w\\+-]+)*@[\\p{L}\\d]+\\.([\\p{L}\\d]{2,4}\\.)?[\\p{L}]{2,4}$";

	/* Only accept Indian mobile number format */
	public static final String PHONE_REGEX = "^[6-9][\\d]{9}$";

	public static final String ALLOWED_SPL_CHARACTERS = "!@#$%^&*()_+";

	public static final String IS_EMPTY = "must not be left blank";

	public static final String PHONE_NUMBER_LENGTH = "must be 10 Digits";

	/**
	 * http://www.sample.com => true https://www.sample.com/ => true
	 * https://www.sample.com# => true http://www.sample.com/xyz => true
	 * http://www.sample.com/#xyz => true www.sample.com => true
	 * www.sample.com/xyz/#/xyz => true sample.com => true sample.com?name=foo =>
	 * true http://www.sample.com#xyz => true http://www.sample.c => false
	 */
	public static final String WEBSITE_REGEX = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$";

	public static final String SPLIT_CHAR = ";";

	/** Error Code Strings */
	public static final String BAD_REQUEST = HttpStatus.BAD_REQUEST.toString();
	public static final String NOT_FOUND = HttpStatus.INTERNAL_SERVER_ERROR.toString();
	public static final String INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.toString();

	/** Academic Year exception messages */
	public static final String ACADEMIC_YEAR_ALREADY_EXISTS = "Same Academic year already exist for this year name";
	public static final String ACADEMIC_YEAR_CREATE_FAILED = "Failed to create academic year";
	public static final String ACADEMIC_YEAR_UPDATE_FAILED = "Failed to update academic year";
	public static final String ACADEMIC_YEAR_NOT_FOUND = "Academic Year info not found with the given information : ";
	public static final String ACADEMIC_YEAR_DELETE_FAILED = "Failed to delete academic year";
	public static final String ACADEMIC_YEAR_DELETED = "Academic Year deleted with this id:";
	public static final String ACADEMIC_YEAR_NOT_DELETED = "Academic Year not deleted with this id:";
	public static final String ACADEMIC_YEAR_LIST_ALL_FAILED = "Failed to list all academic years";

	/** InstitutesEntity System Info exception messages */
	public static final String INSTITUTE_INFO_ALREADY_EXISTS = "Institute system already exist by this institute";
	public static final String INSTITUTE_INFO_CREATE_FAILED = "Failed to create institute system information";
	public static final String INSTITUTE_INFO_UPDATE_FAILED = "Failed to update institute system information";
	public static final String INSTITUTE_INFO_NOT_FOUND = "Institutes info not found with this institute id:";
	public static final String INSTITUTE_INFO_DELETE_FAILED = "Failed to delete institute system information";
	public static final String INSTITUTE_INFO_DELETED = "Institutes info deleted with this institute id:";
	public static final String INSTITUTE_INFO_NOT_DELETED = "Institutes info not deleted with this institute id:";
	public static final String INSTITUTE_INFO_LIST_ALL_FAILED = "Failed to list all institutes system information";

	public static final String MASTER_FIELDS_SAVED = "Master Fields Saved";
	public static final String DATE_FORMAT_DDMMYYYY = "dd-MM-yyyy";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	
	/** Bank Detail exception messages */
	public static final String BANK_DETAIL_ALREADY_EXISTS = "Same Bank Account already exist for this branch";
	public static final String BANK_DETAIL_CREATE_FAILED = "Failed to create Bank detail";
	public static final String BANK_DETAIL_UPDATE_FAILED = "Failed to update Bank detail";
	public static final String BANK_DETAIL_NOT_FOUND = "Bank detail not found with this branch id or bank id :";
	public static final String BANK_DETAIL_DELETE_FAILED = "Failed to delete Bank detail";
	public static final String BANK_DETAIL_DELETED = "Bank detail deleted with this bank id:";
	public static final String BANK_DETAIL_NOT_DELETED = "Bank detail not deleted with this bank id:";
	public static final String BANK_DETAIL_LIST_ALL_FAILED = "Failed to list all Bank details";
	
	/** Subject exception messages */
	public static final String SUBJECT_ALREADY_EXISTS = "Same Subject already exist for this curriculum";
	public static final String SUBJECT_CREATE_FAILED = "Failed to create Subject";
	public static final String SUBJECT_UPDATE_FAILED = "Failed to update Subject";
	public static final String SUBJECT_NOT_FOUND = "Subject not found with this id :";
	public static final String SUBJECT_DELETE_FAILED = "Failed to delete Subject";
	public static final String SUBJECT_DELETED = "Subject deleted with this id:";
	public static final String SUBJECT_NOT_DELETED = "Subject not deleted with this id:";
	public static final String SUBJECT_LIST_ALL_FAILED = "Failed to list all Subjects";

	public static final String GET_CURRENT_ACADEMIC_YEAR_FAILED = "Failed to get current academic year";


}
