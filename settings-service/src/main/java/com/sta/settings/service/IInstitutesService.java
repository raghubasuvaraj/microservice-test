package com.sta.settings.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sta.settings.dto.request.ActiveStatusToggleRequest;
import com.sta.settings.dto.request.InstitutesApproveRequest;
import com.sta.settings.dto.request.InstitutesRequest;
import com.sta.settings.dto.response.InstitutesResponse;

@Service
public interface IInstitutesService {

	InstitutesResponse createInstitute(InstitutesRequest request);

	InstitutesResponse updateInstitute(InstitutesRequest request);

	InstitutesResponse getInstituteById(String id);

	List<InstitutesResponse> getAllInstitute();

	Boolean toggleActiveStatus(ActiveStatusToggleRequest request);

	Boolean deleteInstitute(String id);

	InstitutesResponse approveOrRejectInstitute(InstitutesApproveRequest request);
}