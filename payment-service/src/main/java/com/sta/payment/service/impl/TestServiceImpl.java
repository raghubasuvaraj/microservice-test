package com.sta.payment.service.impl;

import com.sta.payment.dto.TestDTO;
import com.sta.payment.entities.TestEntity;
import com.sta.payment.exception.TestServiceException;
import com.sta.payment.mappers.TestMapper;
import com.sta.payment.repository.TestRepository;
import com.sta.payment.service.ITestService;
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