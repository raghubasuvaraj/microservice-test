package com.sta.settings.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchAreaRequest {
    private String id;
    private String branchId;
    private String areaName;
    private String pinCode;
}
