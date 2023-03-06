package com.sta.settings.service;

import com.sta.settings.dto.request.AreaRequest;
import com.sta.settings.dto.response.AreaResponse;

import java.util.List;

public interface IAreaService {

    List<AreaResponse> saveAreaForBranch( List<AreaRequest> branchAreaRequestList);
}
