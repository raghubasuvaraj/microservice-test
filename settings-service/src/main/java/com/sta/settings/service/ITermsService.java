package com.sta.settings.service;

import com.sta.settings.dto.request.ListTermRequestForBranch;
import com.sta.settings.dto.request.TermListRequest;
import com.sta.settings.dto.request.TermRequest;
import com.sta.settings.dto.request.TermRequestForBranch;
import com.sta.settings.entities.BranchTermEntity;
import com.sta.settings.entities.InstituteTermEntity;
import com.sta.settings.entities.LanguageEntity;

import java.util.List;

public interface ITermsService {
    List<InstituteTermEntity> getAllInstituteTerms(String instituteId, String termName);

    InstituteTermEntity getInstituteTerm(String termId, String instituteId);

   List<InstituteTermEntity> saveInstituteTerm(TermListRequest request, String instituteId);

    InstituteTermEntity updateInstituteTerm(String termId, String instituteId, TermRequest request);

    void deleteInstituteTerm(String id, String instituteId);

    List<BranchTermEntity> saveBranchTerm(ListTermRequestForBranch request, String branchId);

    List<BranchTermEntity> getAllBranchTerms(String branchId, String termName);

    void deleteBranchTerm(String id, String branchId);

    BranchTermEntity updateBranchTerm(String termId, String branchId, TermRequestForBranch request);
}
