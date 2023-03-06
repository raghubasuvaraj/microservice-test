package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class TermListRequest {

    private String termTypeId;

    @Valid
    List<TermRequest> terms;
}
