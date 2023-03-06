package com.sta.settings.service;

import com.sta.settings.dto.request.MasterFieldRequest;
import com.sta.settings.dto.response.MasterFieldResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IMasterFieldService {
    MasterFieldResponse saveOrUpdateMasterField(List<MasterFieldRequest> masterFieldDto);
    MasterFieldResponse getMasterField(String fieldId);
    List<MasterFieldResponse> getAllMasterField();
    MasterFieldResponse deleteMasterField(String fieldId);
}
