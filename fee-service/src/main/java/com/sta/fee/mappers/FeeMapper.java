package com.sta.fee.mappers;

import com.sta.fee.dto.FeeDTO;
import com.sta.fee.entities.FeeEntity;


public class FeeMapper {
    /**
     *
     * @param feeDetails
     * @return
     */
       public static FeeEntity toFeeEntity(FeeDTO feeDetails){
           return FeeEntity.builder().feeMonth(feeDetails.getFeeMonth())
                   .totalFee(feeDetails.getTotalFee())
                   .amountToBePaid(feeDetails.getAmountToBePaid())
                   .createdDate(feeDetails.getCreatedDate())
                   .id(feeDetails.getId())
                   .build();
       }

    /**
     *
     * @param result
     * @return
     */
    public static FeeDTO toFeeDto(FeeEntity result) {
        return FeeDTO.builder().feeMonth(result.getFeeMonth())
                .totalFee(result.getTotalFee())
                .amountToBePaid(result.getAmountToBePaid())
                .createdDate(result.getCreatedDate())
                .id(result.getId())
                .build();
    }
}

