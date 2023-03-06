package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AcademicClassRequest {
    @Valid
    List<@NotNull(message = "Academic class id is required ") @NotEmpty(message = "Academic class id  is required ") String> classIds;

}
