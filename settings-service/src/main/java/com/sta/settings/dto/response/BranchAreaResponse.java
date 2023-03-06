package com.sta.settings.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchAreaResponse {
    private String id;
    private String branchId;
    private String name;
    private String pinCode;
    private boolean active;

}
