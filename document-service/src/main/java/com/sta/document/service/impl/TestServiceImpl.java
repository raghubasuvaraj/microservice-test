package com.sta.document.service.impl;

import com.sta.document.dto.TestDTO;
import com.sta.document.entities.TestEntity;
import com.sta.document.exception.TestServiceException;
import com.sta.document.mappers.TestMapper;
import com.sta.document.repository.TestRepository;
import com.sta.document.service.ITestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements ITestService {
    @Autowired
    TestRepository testRepository;

    @Override
    public TestDTO addFeeDetails(TestDTO testDTO) {
        try{

            TestEntity result = testRepository.save(TestMapper.toEntity(testDTO));

            return TestMapper.toDto(result);
        }catch (Exception exception){
            throw new TestServiceException(exception.getMessage());
        }

    }
}