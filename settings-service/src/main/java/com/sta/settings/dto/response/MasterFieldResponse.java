package com.sta.settings.dto.response;

import com.sta.settings.enums.CommonStatus;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MasterFieldResponse {

    private String id;
    private String instituteId;
    private String configName;
    private String  configValue;
    private CommonStatus status;
    private String modifiedBy;
    private Long lastModifiedAt;
    private Boolean active;
}
