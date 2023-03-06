package com.sta.settings.service;

import com.sta.settings.dto.request.BranchApproveRequest;
import com.sta.settings.dto.request.BranchRequest;
import com.sta.settings.dto.request.RequestForMainBranch;
import com.sta.settings.dto.response.BranchResponse;
import com.sta.settings.entities.BranchInfoEntity;

import java.util.List;

public interface IBranchInfoService {
    BranchResponse getBranchById(String branchId);
    BranchResponse saveBranch(BranchRequest branchDto);

    List<BranchResponse> getAllBranches(String instId);

    BranchResponse updateBranchDetails(BranchRequest branchRequest);

    boolean deleteBranch(String branchId);

    BranchInfoEntity updateBranchToMainBranch(String instituteId, String branchId, RequestForMainBranch request);

    BranchResponse approveOrRejectBranch(BranchApproveRequest request);
}
