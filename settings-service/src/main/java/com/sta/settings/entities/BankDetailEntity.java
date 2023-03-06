package com.sta.settings.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_branch_accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7248257232501931600L;

    @Column(name = "branch_id")
    private String branchId;
    @Column(name = "bank_account_no")
    private String bankAccountNumber;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_ifsc_code")
    private String ifscCode;
    @Column(name = "bank_branch")
    private String bankAccountBranch;
    @Column(name = "bank_account_name")
    private String bankAccountName;
    @Column(name = "is_primary")
    private boolean isPrimary;
    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}
