package com.sta.fee.service.impl;

import com.sta.fee.dto.FeeDTO;
import com.sta.fee.entities.FeeEntity;
import com.sta.fee.exception.FeeServiceException;
import com.sta.fee.mappers.FeeMapper;
import com.sta.fee.repository.FeeRepository;
import com.sta.fee.service.IFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements IFeeService {
    @Autowired
    FeeRepository feeRepository;

    @Override
    public FeeDTO addFeeDetails(FeeDTO feeDTO) {
        try{

            FeeEntity result = feeRepository.save(FeeMapper.toFeeEntity(feeDTO));

            return FeeMapper.toFeeDto(result);
        }catch (Exception exception){
            throw new FeeServiceException(exception.getMessage());
        }

    }
}
