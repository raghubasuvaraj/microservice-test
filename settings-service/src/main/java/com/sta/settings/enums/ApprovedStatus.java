package com.sta.settings.enums;

/**
 * Approval status, this is a generic one can be used to apply anywhere.
 * 
 * @author r@ghu
 *
 */
public enum ApprovedStatus {

	PENDING("Pending", "PENDING"), INPROGRESS("Inprogress", "INPROGRESS"), APPROVED("Approved", "APPROVED"),
	REJECTED("Rejected", "REJECTED"), HOLD("Hold", "HOLD"), DELETED("Deleted", "DELETED");

	private String name;

	private String code;

	ApprovedStatus(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

}
