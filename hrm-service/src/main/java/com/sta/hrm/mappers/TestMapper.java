package com.sta.hrm.mappers;


import com.sta.hrm.dto.TestDTO;
import com.sta.hrm.entities.TestEntity;

public class TestMapper {
    /**
     *
     * @param
     * @return
     */
       public static TestEntity toEntity(TestDTO testDTO){
           return TestEntity.builder().feeMonth(testDTO.getFeeMonth())
                   .totalFee(testDTO.getTotalFee())
                   .amountToBePaid(testDTO.getAmountToBePaid())
                   .createdDate(testDTO.getCreatedDate())
                   .id(testDTO.getId())
                   .build();
       }

    /**
     *
     * @param result
     * @return
     */
    public static TestDTO toFeeDto(TestEntity result) {
        return TestDTO.builder().feeMonth(result.getFeeMonth())
                .totalFee(result.getTotalFee())
                .amountToBePaid(result.getAmountToBePaid())
                .createdDate(result.getCreatedDate())
                .id(result.getId())
                .build();
    }
}

