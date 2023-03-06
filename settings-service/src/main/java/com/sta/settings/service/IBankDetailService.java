package com.sta.settings.service;

import com.sta.settings.dto.BankDetailDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
/**
 * A Service interface to cater CRUD operations for Bank detail of a branch.
 *
 * @author Shane
 */
public interface IBankDetailService {
    BankDetailDto saveBankDetail(BankDetailDto bankDetailDto);
    BankDetailDto updateBankDetail(BankDetailDto bankDetailDto);
    List<BankDetailDto> getBankDetail(String branchIdOrBankDetailId);
    List<BankDetailDto> getAllBankDetails();
    ResponseEntity<String> deleteBankDetail(String id);
}
