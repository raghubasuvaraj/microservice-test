package com.sta.fee.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FeeDTO {

    private Long id;

    private Long totalFee;

    private Long amountToBePaid;

    private String feeMonth;

    private Date createdDate;
}
