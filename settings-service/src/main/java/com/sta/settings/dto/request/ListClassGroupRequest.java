package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ListClassGroupRequest {
    @Valid
    List<@NotNull(message = "class group is required ") @NotEmpty(message = "class group name  is required ") String> classGroups;
}
