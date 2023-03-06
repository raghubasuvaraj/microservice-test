package com.sta.settings.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequest {
    private String id;
    private String branchId;
    private String areaName;
    private String pinCode;
}
