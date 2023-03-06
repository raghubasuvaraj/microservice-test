package com.sta.settings.service;

import com.sta.settings.dto.InstitutesSystemInfoDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IInstitutesSystemInfoService {
    InstitutesSystemInfoDto saveInstituteSystemInfo(InstitutesSystemInfoDto institutesRequestDto);
    InstitutesSystemInfoDto updateInstituteSystemInfo(InstitutesSystemInfoDto institutesSystemInfoDto, HttpServletRequest request);
    List<InstitutesSystemInfoDto> getAllInstituteSystemInfo();
    InstitutesSystemInfoDto getInstituteSystemInfoByInstituteId(String instituteId);
    ResponseEntity<String> deleteInstituteSystemInfoByInstituteId(String instituteId);
}
