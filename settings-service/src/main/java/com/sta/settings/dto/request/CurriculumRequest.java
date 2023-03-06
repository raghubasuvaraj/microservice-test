package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CurriculumRequest {

    private String masterCurriculumId;

    private String name;

    private String code;

}
