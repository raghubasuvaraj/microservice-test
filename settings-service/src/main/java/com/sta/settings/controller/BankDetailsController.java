package com.sta.settings.controller;

import com.sta.settings.dto.BankDetailDto;
import com.sta.settings.service.IBankDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * A REST handler to cater CRUD operations for Bank detail of a branch.
 *
 * @author Shane
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/bank")
public class BankDetailsController {

    @Autowired
    private IBankDetailService bankDetailService;

    @PostMapping()
    public ResponseEntity<BankDetailDto> saveBankDetail(@RequestBody BankDetailDto bankDetailDto) {
        log.info("Saving Bank Detail for branchId - {} ::", bankDetailDto.getBranchId());
        return new ResponseEntity<>(bankDetailService.saveBankDetail(bankDetailDto), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<BankDetailDto> updateBankDetail(@Valid @RequestBody BankDetailDto bankDetailDto) {
        log.info("Updating Bank Detail for bankId - {} ::", bankDetailDto.getId());
        return new ResponseEntity<>(bankDetailService.updateBankDetail(bankDetailDto), HttpStatus.CREATED);
    }
    @GetMapping("/{branch_id_or_bank_id}")
    public ResponseEntity<List<BankDetailDto>> getBankDetail(@PathVariable("branch_id_or_bank_id") String branchIdOrBankId) {
        log.info("Getting All the Bank Details for a branch_id_or_bank_id - {} ::", branchIdOrBankId);
        return new ResponseEntity<>(bankDetailService.getBankDetail(branchIdOrBankId), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<BankDetailDto>> getAllBankDetails() {
        log.info("Getting All the Bank Details.");
        return new ResponseEntity<>(bankDetailService.getAllBankDetails(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankDetail(@PathVariable("id") String id) {
        log.info("Deleting a Bank Details with id {} ::", id);
        return bankDetailService.deleteBankDetail(id);
    }
}
