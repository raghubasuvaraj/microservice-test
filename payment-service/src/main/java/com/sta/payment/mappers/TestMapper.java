package com.sta.payment.mappers;

import com.sta.payment.dto.TestDTO;
import com.sta.payment.entities.TestEntity;

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
    public static TestDTO toDto(TestEntity result) {
        return TestDTO.builder().feeMonth(result.getFeeMonth())
                .totalFee(result.getTotalFee())
                .amountToBePaid(result.getAmountToBePaid())
                .createdDate(result.getCreatedDate())
                .id(result.getId())
                .build();
    }
}

