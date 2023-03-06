package com.sta.settings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassGroupResponse {
    private String classGroup;
    private String className;
    private String shortCode;
    private Long instituteId;
    private int branchId;
    private Long academicYear;
    private Long termId;
    private boolean status;
}