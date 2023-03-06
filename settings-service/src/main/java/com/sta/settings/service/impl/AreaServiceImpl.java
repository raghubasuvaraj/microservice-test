package com.sta.settings.service.impl;

import com.sta.settings.dto.request.AreaRequest;
import com.sta.settings.dto.response.AreaResponse;
import com.sta.settings.entities.AreaEntity;
import com.sta.settings.mappers.AreaMapper;
import com.sta.settings.repository.AreaRepository;
import com.sta.settings.service.IAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AreaServiceImpl implements IAreaService {

    @Autowired
    AreaRepository areaRepository;
    @Override
    public List<AreaResponse> saveAreaForBranch(List<AreaRequest> branchAreaRequestList) {
        List<AreaEntity> list = areaRepository.saveAll(branchAreaRequestList.stream().map
                       (entity -> AreaMapper.convertAreaRequestToEntity(entity)).collect(Collectors.toList()));

        return list.stream().map(response -> AreaMapper.convertAreaEntityToResponse(response)).collect(Collectors.toList());
    }
}
