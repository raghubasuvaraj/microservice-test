package com.sta.settings.repository;

import com.sta.settings.entities.BankDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankDetailRepository extends JpaRepository<BankDetailEntity, String> {

    boolean existsByBranchIdAndBankAccountNumber(String branchId, String bankAccountNumber);

    @Query(value = "select b from BankDetailEntity b where (b.branchId = ?1 or b.id = ?1) and b.active = 1")
    List<BankDetailEntity> findByBranchIdOrBankDetailId(String branchIdOrBankDetailId);
}
