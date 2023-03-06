package com.sta.document.mappers;

import com.sta.document.dto.TestDTO;
import com.sta.document.entities.TestEntity;

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

