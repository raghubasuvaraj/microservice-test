package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class TermRequest {

    private String masterTermId;

    private String name;

    private String code;

}
