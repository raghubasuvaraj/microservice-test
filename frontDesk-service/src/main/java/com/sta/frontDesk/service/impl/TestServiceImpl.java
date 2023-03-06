package com.sta.frontDesk.service.impl;


import com.sta.frontDesk.dto.TestDTO;
import com.sta.frontDesk.entities.TestEntity;
import com.sta.frontDesk.exception.TestServiceException;
import com.sta.frontDesk.mappers.TestMapper;
import com.sta.frontDesk.repository.TestRepository;
import com.sta.frontDesk.service.ITestService;
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