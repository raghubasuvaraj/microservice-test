package com.sta.settings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author r@ghu | Thur 28th July 2022
 *
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DepartmentsResponse {

	private String id;

	private String department;

	private String shortCode;

	private String branchId;

	private String branch;

	private String instituteId;

	private String institute;

	private boolean active;

	public DepartmentsResponse(String id, String department, String shortCode, boolean active) {
		this.id = id;
		this.department = department;
		this.shortCode = shortCode;
		this.active = active;
	}
}
