package com.sta.directory.service.impl;


import com.sta.directory.dto.TestDTO;
import com.sta.directory.entities.TestEntity;
import com.sta.directory.exception.TestServiceException;
import com.sta.directory.mappers.TestMapper;
import com.sta.directory.repository.TestRepository;
import com.sta.directory.service.ITestService;
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