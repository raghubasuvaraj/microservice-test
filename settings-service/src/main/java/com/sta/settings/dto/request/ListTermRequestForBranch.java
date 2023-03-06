package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ListTermRequestForBranch {

    private String academicYearId;

    private String termTypeId;

    @Valid
    List<TermRequestForBranch> academicTerms;
}
