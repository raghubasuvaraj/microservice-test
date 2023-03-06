package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class DepartmentRequestList {

    @Valid
    private List<DepartmentsRequest> departments;
}
