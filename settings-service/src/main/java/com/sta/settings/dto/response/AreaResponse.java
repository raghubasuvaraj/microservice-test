package com.sta.settings.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AreaResponse {
    private String id;
    private String branchId;
    private String areaName;
    private String pinCode;
    private boolean active;
}
