package com.sta.settings.dto.request;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class CurriculumRequestList {

    @Valid
    private List<CurriculumRequest> curriculums;
}
