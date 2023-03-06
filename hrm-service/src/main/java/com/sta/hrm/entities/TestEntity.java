package com.sta.hrm.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fee")
@Data
@Builder
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "totalFee")
    public Long totalFee;

    @Column(name = "amountToBePaid")
    public Long amountToBePaid;

    @Column(name = "feeMonth")
    public String feeMonth;

    @Column(name = "createdDate")
    public Date createdDate;
}
