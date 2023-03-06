package com.sta.examination.service.impl;




import com.sta.academics.service.ITestService;
import com.sta.examination.dto.TestDTO;
import com.sta.examination.entities.TestEntity;
import com.sta.examination.exception.TestServiceException;
import com.sta.examination.mappers.TestMapper;
import com.sta.examination.repository.TestRepository;
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