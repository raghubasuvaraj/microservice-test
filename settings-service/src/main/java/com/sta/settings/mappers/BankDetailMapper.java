package com.sta.settings.mappers;

import com.sta.settings.dto.BankDetailDto;
import com.sta.settings.entities.BankDetailEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankDetailMapper {

    public static BankDetailEntity requestToEntity(BankDetailDto request) {

        return BankDetailEntity.builder()
                .branchId(request.getBranchId())
                .bankAccountNumber(request.getBankAccountNumber())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .bankAccountName(request.getBankAccountName())
                .isPrimary(request.isPrimary())
                .bankAccountBranch(request.getBankAccountBranch())
                .build();
    }

    public static BankDetailEntity updateRequestToEntity(BankDetailDto request, String id) {

        BankDetailEntity bankDetailEntity = BankDetailEntity.builder()
                .branchId(request.getBranchId())
                .bankAccountNumber(request.getBankAccountNumber())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .isPrimary(request.isPrimary())
                .bankAccountName(request.getBankAccountName())
                .bankAccountBranch(request.getBankAccountBranch())
                .build();
        bankDetailEntity.setId(id);
        return bankDetailEntity;
    }

    public static BankDetailDto entityToResponse(BankDetailEntity bankDetailEntity) {
        return BankDetailDto.builder()
                .id(bankDetailEntity.getId()).branchId(bankDetailEntity.getBranchId())
                .bankAccountNumber(bankDetailEntity.getBankAccountNumber())
                .bankName(bankDetailEntity.getBankName())
                .ifscCode(bankDetailEntity.getIfscCode())
                .isPrimary(bankDetailEntity.isPrimary())
                .bankAccountName(bankDetailEntity.getBankAccountName())
                .bankAccountBranch(bankDetailEntity.getBankAccountBranch())
                .build();
    }

    public static List<BankDetailDto> mapAllEntityToResponse(List<BankDetailEntity> bankDetailEntities) {
        return bankDetailEntities.parallelStream()
                .map(BankDetailMapper::entityToResponse)
                .toList();
    }
}
