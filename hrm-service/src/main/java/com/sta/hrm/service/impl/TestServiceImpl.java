package com.sta.hrm.service.impl;

import com.sta.hrm.dto.TestDTO;
import com.sta.hrm.entities.TestEntity;
import com.sta.hrm.exception.TestServiceException;
import com.sta.hrm.mappers.TestMapper;
import com.sta.hrm.repository.TestRepository;
import com.sta.hrm.service.ITestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements ITestService {
    @Autowired
    TestRepository feeRepository;


    @Override
    public TestDTO addFeeDetails(TestDTO feeDTO) {
        try{

            TestEntity result = feeRepository.save(TestMapper.toEntity(feeDTO));

            return TestMapper.toFeeDto(result);
        }catch (Exception exception){
            throw new TestServiceException(exception.getMessage());
        }

    }
}