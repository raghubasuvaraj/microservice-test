package com.sta.settings.service;

import com.sta.settings.dto.request.BranchAreaRequest;
import com.sta.settings.dto.response.BranchAreaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBranchAreaService {

    public List<BranchAreaResponse> saveOrUpdateBranchArea(List<BranchAreaRequest> branchAreaRequestList);

    void deleteBranchArea(String fieldId);

    public List<BranchAreaResponse> getAllBranchAreas(String branchId);

    public BranchAreaResponse getBranchAreasById(String id);

}
