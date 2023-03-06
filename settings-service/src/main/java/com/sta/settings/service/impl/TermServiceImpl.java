package com.sta.settings.service.impl;

import com.sta.dc.common.utility.SettingsConstants;
import com.sta.settings.dto.request.*;
import com.sta.settings.entities.*;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.*;
import com.sta.settings.service.ITermsService;
import com.sta.settings.utility.DateUtils;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TermServiceImpl implements ITermsService {
    @Autowired
    private InstituteTermRepository instituteTermRepository;

    @Autowired
    private InstitutesRepository institutesRepository;

    @Autowired
    private TermTypeRepository termTypeRepository;
    @Autowired
    private BranchInfoRepository branchInfoRepository;
    @Autowired
    private BranchTermRepository branchTermRepository;
    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private MasterTermRepository masterTermRepository;

    @Override
    public List<InstituteTermEntity> getAllInstituteTerms(String instituteId, String termName) {
        try {
            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<Predicate> predicates = new ArrayList<>();
            Specification<InstituteTermEntity> spec = (root, query, criteriaBuilder) -> {

                predicates.add(criteriaBuilder.equal(root.get("active"), true));

                predicates.add(criteriaBuilder.equal(root.get("institute").get("id"), instituteId));

                if (termName != null && !termName.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("masterTermEntity").get("name")),
                            "%" + termName.toLowerCase() + "%"));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return instituteTermRepository.findAll(spec);
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERMS_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public InstituteTermEntity getInstituteTerm(String termId, String instituteId) {
        try {
            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));
            InstituteTermEntity instituteTermObj = instituteTermRepository.findById(termId)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            if (!instituteTermObj.getInstitute().getId().equalsIgnoreCase(instituteId)) {
                throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_NOT_ASSOCIATED,
                        HttpStatus.NOT_FOUND, null);
            }
            return instituteTermObj;
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERMS_GET_BY_ID_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<InstituteTermEntity> saveInstituteTerm(TermListRequest request, String instituteId) {
        try {
            if(request.getTermTypeId()==null ||request.getTermTypeId().isEmpty()) {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
                        "Master Term Type is mandatory.", HttpStatus.BAD_REQUEST, null);
            }

            InstitutesEntity institute = institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<MasterTermEntity> masterTermEntities = masterTermRepository.findAll();

            List<InstituteTermEntity> instituteTermsListToBeSaved = new ArrayList<>();
            List<MasterTermEntity> masterTermListToBeSaved = new ArrayList<>();

            MasterTermTypeEntity masterTermTypeEntity = termTypeRepository.findById(request.getTermTypeId())
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_TYPE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            for (TermRequest termRequest : request.getTerms()) {
                if (termRequest.getMasterTermId() != null && !termRequest.getMasterTermId().isEmpty()) {
                    Optional<MasterTermEntity> optionalMasterTermEntity = masterTermEntities.stream().
                            filter(masterTerm -> masterTerm.getId().equalsIgnoreCase(termRequest.getMasterTermId())).
                            findAny();
                    if (optionalMasterTermEntity.isPresent()) {
                        MasterTermEntity masterTermEntity = optionalMasterTermEntity.get();
                        InstituteTermEntity instituteTerm = new InstituteTermEntity();
                        instituteTerm.setInstitute(institute);
                        instituteTerm.setMasterTermEntity(masterTermEntity);
                        instituteTerm.setTermType(masterTermTypeEntity);
                        instituteTermsListToBeSaved.add(instituteTerm);
                    } else {
                        throw new SettingsException(ErrorCodesConstants.NOT_FOUND, "Master term not found.",
                                HttpStatus.NOT_FOUND, null);
                    }
                } else {
                    if ((termRequest.getName().length() > 50 || termRequest.getName().length() < 2)
                            || !(termRequest.getName().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
                                "Term name should be alphanumeric with one (-) allowed, and of 2-50 characters." + ": " + termRequest.getName(),
                                HttpStatus.BAD_REQUEST, null);
                    }
                    if ((termRequest.getCode().length() > 10 || termRequest.getCode().length() < 2)
                            || !(termRequest.getCode().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
                                "Term code should be alphanumeric with one (-) allowed, and of 2-10 characters." + ": " + termRequest.getCode(),
                                HttpStatus.BAD_REQUEST, null);
                    }
                    MasterTermEntity masterTerm = new MasterTermEntity();
                    masterTerm.setName(termRequest.getName());
                    masterTerm.setShortCode(termRequest.getCode());
                    masterTerm.setPosition(masterTermEntities.stream().max(Comparator.comparing(MasterTermEntity::getPosition)).get().getPosition() + 1);

                    InstituteTermEntity instituteTerm = new InstituteTermEntity();
                    instituteTerm.setInstitute(institute);
                    instituteTerm.setMasterTermEntity(masterTerm);
                    instituteTerm.setTermType(masterTermTypeEntity);

                    masterTermListToBeSaved.add(masterTerm);
                    instituteTermsListToBeSaved.add(instituteTerm);

                }
            }
            try {
                masterTermRepository.saveAll(masterTermListToBeSaved);
            } catch (DataIntegrityViolationException e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
                    String columnName = ex.getConstraintName();
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Master Term already exists with " + "term " + columnName.substring(columnName.lastIndexOf('.') + 1),
                            HttpStatus.BAD_REQUEST, null);
                }
            }
            try {
                instituteTermRepository.saveAll(instituteTermsListToBeSaved);
            } catch (DataIntegrityViolationException e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Term is already available with name and type for the given institute.",
                            HttpStatus.BAD_REQUEST, null);
                }
            }
            return instituteTermsListToBeSaved;
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public InstituteTermEntity updateInstituteTerm(String termId, String instituteId, TermRequest request) {
        try {

            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            InstituteTermEntity instituteTermObj = instituteTermRepository.findById(termId)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            if (instituteTermObj.getInstitute().getId().equalsIgnoreCase(instituteId)) {
                //to be continued
                return instituteTermRepository.save(instituteTermObj);
            } else {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_TERM,
                        HttpStatus.BAD_GATEWAY, null);
            }
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERM_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public void deleteInstituteTerm(String id, String instituteId) {
        try {
            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            InstituteTermEntity instituteTermObj = instituteTermRepository.findById(id)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            if (instituteTermObj.getInstitute().getId().equalsIgnoreCase(instituteId)) {
                instituteTermObj.setStatus(CommonStatus.DELETE);
                instituteTermObj.setDeletedAt(LocalDateTime.now());
                instituteTermRepository.save(instituteTermObj);
            } else {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_TERM,
                        HttpStatus.BAD_GATEWAY, null);
            }
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERM_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<BranchTermEntity> saveBranchTerm(ListTermRequestForBranch request, String branchId) {
        try {

            if (request.getAcademicYearId() == null || request.getAcademicYearId().isEmpty()) {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
                        "Institute Academic Year is mandatory.", HttpStatus.BAD_REQUEST, null);
            }

            BranchInfoEntity branch = branchInfoRepository.findByIdAndActive(branchId, true).
                    orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND,
                            MessageConstants.BRANCH_NOT_FOUND, HttpStatus.NOT_FOUND, null));

            InstituteAcademicYearEntity academicYearEntity = academicYearRepository.findByIdAndActive(request.getAcademicYearId(), true).
                    orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND,
                            "Academic Year Not Found.", HttpStatus.NOT_FOUND, null));

            termTypeRepository.findById(request.getTermTypeId())
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_TYPE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<InstituteTermEntity> dbInstituteTermEntities = instituteTermRepository.
                    findByInstituteIdAndTermTypeIdAndActive(branch.getInstituteId(), request.getTermTypeId(), true);

            List<BranchTermEntity> branchTermListToBeSaved = new ArrayList<>();

            for (TermRequestForBranch termReq : request.getAcademicTerms()) {
                LocalDate startDate = DateUtils.convertStringToJustLocalDate(termReq.getStartDate(), SettingsConstants.DATE_FORMAT_YYYYMMDD);
                LocalDate endDate = DateUtils.convertStringToJustLocalDate(termReq.getEndDate(), SettingsConstants.DATE_FORMAT_YYYYMMDD);

                if (startDate.isAfter(endDate)) {
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_DATE,
                            HttpStatus.BAD_GATEWAY, null);
                }
                if(!dbInstituteTermEntities.isEmpty()) {
                    Optional<InstituteTermEntity> optionalInstituteTerm = dbInstituteTermEntities.
                            stream().
                            filter(dbInstituteTerm -> dbInstituteTerm.getId().equalsIgnoreCase(termReq.getInstituteTermId())).
                            findAny();
                    if (optionalInstituteTerm.isPresent()) {
                        BranchTermEntity branchTerm = new BranchTermEntity();
                        branchTerm.setBranch(branch);
                        branchTerm.setStartDate(startDate);
                        branchTerm.setEndDate(endDate);
                        branchTerm.setAcademicYear(academicYearEntity);
                        branchTerm.setInstituteTerm(optionalInstituteTerm.get());

                        branchTermListToBeSaved.add(branchTerm);
                    } else {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Institute Term not found.",
                                HttpStatus.BAD_REQUEST, null);
                    }
                }else{
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Institute Term not found.",
                            HttpStatus.BAD_REQUEST, null);
                }
            }

            try {
                branchTermRepository.saveAll(branchTermListToBeSaved);
            } catch (DataIntegrityViolationException e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Term already exists for the given branch.",
                            HttpStatus.BAD_REQUEST, null);
                }
            }
            return branchTermListToBeSaved;
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERMS_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<BranchTermEntity> getAllBranchTerms(String branchId, String termName) {
        try {
            branchInfoRepository.findByIdAndActive(branchId, true).
                    orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND,
                            MessageConstants.BRANCH_NOT_FOUND, HttpStatus.NOT_FOUND, null));

            List<Predicate> predicates = new ArrayList<>();

            Specification<BranchTermEntity> spec = (root, query, criteriaBuilder) -> {

                predicates.add(criteriaBuilder.equal(root.get("active"), true));

                predicates.add(criteriaBuilder.equal(root.get("branch").get("id"), branchId));

                if (termName != null && !termName.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("instituteTerm").get("masterTermEntity").get("name")),
                            "%" + termName.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return branchTermRepository.findAll(spec);
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERMS_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public void deleteBranchTerm(String id, String branchId) {
        try {
            branchInfoRepository.findByIdAndActive(branchId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            BranchTermEntity branchTermObj = branchTermRepository.findById(id)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            if (branchTermObj.getBranch().getId().equalsIgnoreCase(branchId)) {
                branchTermObj.setStatus(CommonStatus.DELETE);
                branchTermObj.setDeletedAt(LocalDateTime.now());
                branchTermRepository.save(branchTermObj);
            } else {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_TERM_BRANCH,
                        HttpStatus.BAD_GATEWAY, null);
            }
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERM_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public BranchTermEntity updateBranchTerm(String termId, String branchId, TermRequestForBranch request) {
        try {
            BranchInfoEntity branchInfoEntity = branchInfoRepository.findByIdAndActive(branchId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            BranchTermEntity branchTermObj = branchTermRepository.findById(termId)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.TERM_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<InstituteTermEntity> dbInstituteTermEntities = instituteTermRepository.findByInstituteIdAndActive(branchInfoEntity.getInstituteId(), true);

            if (branchTermObj.getBranch().getId().equalsIgnoreCase(branchId)) {
                LocalDate d1 = DateUtils.convertStringToJustLocalDate(request.getStartDate(), SettingsConstants.DATE_FORMAT_YYYYMMDD);
                LocalDate d2 = DateUtils.convertStringToJustLocalDate(request.getEndDate(), SettingsConstants.DATE_FORMAT_YYYYMMDD);
                if (d1.isAfter(d2)) {
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_DATE,
                            HttpStatus.BAD_GATEWAY, null);
                }
                Optional<InstituteTermEntity> optionalInstituteTerm = dbInstituteTermEntities.
                        stream().
                        filter(dbInstituteTerm -> dbInstituteTerm.getId().equalsIgnoreCase(request.getInstituteTermId())).
                        findAny();
                branchTermObj.setStartDate(d1);
                branchTermObj.setEndDate(d2);
                branchTermObj.setInstituteTerm(optionalInstituteTerm.get());
                try {
                    branchTermRepository.save(branchTermObj);
                } catch (DataIntegrityViolationException e) {
                    if (e.getCause() instanceof ConstraintViolationException) {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Term already exists for the given branch.",
                                HttpStatus.BAD_REQUEST, null);
                    }
                }
            } else {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Term is not associated with the given branch.",
                        HttpStatus.BAD_GATEWAY, null);
            }
            return branchTermObj;
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.TERM_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}
