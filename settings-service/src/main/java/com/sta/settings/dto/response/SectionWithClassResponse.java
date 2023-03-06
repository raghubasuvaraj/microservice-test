package com.sta.settings.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @JsonInclude(value = Include.NON_NULL) is used to hide the field if it has no
 *                    data.
 * 
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class SectionWithClassResponse {

	@JsonInclude(value = Include.NON_NULL)
	private String classId;

	@JsonInclude(value = Include.NON_NULL)
	private String className;

	private List<SectionsResponse> sectionsResponse = new ArrayList<>();
}
