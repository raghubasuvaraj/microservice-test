package com.sta.settings.service.impl;

import com.sta.dc.common.entity.academic.MasterAcademicClassesEntity;
import com.sta.settings.dto.request.CurriculumRequest;
import com.sta.settings.dto.request.CurriculumRequestList;
import com.sta.settings.entities.*;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.*;
import com.sta.settings.service.ISettingsService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SettingServiceImpl implements ISettingsService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private MasterBranchTypeRepository masterBranchTypeRepository;

    @Autowired
    private ClassGroupsRepository classGroupsRepository;

    @Autowired
    private InstitutesRepository institutesRepository;

    @Autowired
    private MasterCurriculumRespository masterCurriculumRespository;

    @Autowired
    private InstituteCurriculumRepository instituteCurriculumRepository;

    @Autowired
    MasterModuleRepository masterModuleRepository;

    @Autowired
    MasterFieldsDataRepository masterFieldsDataRepository;
    @Autowired
    MasterAcademicYearRepository masterAcademicYearRepository;
    @Autowired
    TermTypeRepository termTypeRepository;
    @Autowired
    MasterDepartmentsRepository masterDepartmentsRepository;
    @Autowired
    MasterAcademicClassesRepository masterAcademicClassesRepository;
    @Autowired
    MasterRoleRepository masterRoleRepository;
    @Autowired
    MasterAcademicCurriculumRepository masterAcademicCurriculumRepository;
    @Autowired
    MasterTermRepository masterTermRepository;

    @Autowired
    private MasterFormsRepository masterFormsRepository;

    @Autowired
    private BranchInfoRepository branchRepository;

    @Autowired
    private BranchCurriculumRepository branchCurriculumRepository;

    @Override
    public List<MasterFieldsDataEntity> getAllMasterFields(String categoryName, String name) {
        try {
            return masterFieldsDataRepository.getAllMasterFields(categoryName, name);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.MASTER_DATA_GET_ALL_FAILED + ": " + categoryName, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<LanguageEntity> getAllLanguages(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<LanguageEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return languageRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<MasterBranchTypeEntity> getAllBranchTypes(String branchTypeName) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterBranchTypeEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (branchTypeName != null && !branchTypeName.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + branchTypeName.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterBranchTypeRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.BRANCH_TYPES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<ClassGroupsEntity> getAllClassGroups() {
        try {
            return classGroupsRepository.findAll();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CLASS_GROUPS_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<InstituteCurriculumEntity> createCurriculums(CurriculumRequestList request, String instituteId) throws SettingsException {
        try {
            InstitutesEntity institute = institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<MasterCurriculumEntity> masterCurriculums = masterCurriculumRespository.findAll();

            List<InstituteCurriculumEntity> instituteCurriculumListToBeSaved = new ArrayList<>();
            List<MasterCurriculumEntity> masterCurriculumListToBeSaved = new ArrayList<>();

            for (CurriculumRequest curriculumReq : request.getCurriculums()) {
                if (curriculumReq.getMasterCurriculumId() != null && !curriculumReq.getMasterCurriculumId().isEmpty()) {
                    Optional<MasterCurriculumEntity> optionalMasterCurriculum = masterCurriculums.stream().
                            filter(masterCurriculum -> curriculumReq.getMasterCurriculumId().equalsIgnoreCase(masterCurriculum.getId())).
                            findAny();
                    if (optionalMasterCurriculum.isPresent()) {
                        MasterCurriculumEntity masterCurriculum = optionalMasterCurriculum.get();
                        InstituteCurriculumEntity instituteCurriculum = new InstituteCurriculumEntity();
                        instituteCurriculum.setInstitute(institute);
                        instituteCurriculum.setMasterCurriculum(masterCurriculum);
                        instituteCurriculumListToBeSaved.add(instituteCurriculum);
                    } else {
                        throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.MASTER_CURRICULUM_NOT_FOUND,
                                HttpStatus.NOT_FOUND, null);
                    }
                } else {

                    if ((curriculumReq.getName().length() > 50 || curriculumReq.getName().length() < 2)
                            || !(curriculumReq.getName().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
                                "Curriculum name should be alphanumeric with one (-) allowed, and of 2-50 characters." + ": " + curriculumReq.getName(),
                                HttpStatus.BAD_REQUEST, null);
                    }
                    if ((curriculumReq.getCode().length() > 10 || curriculumReq.getCode().length() < 2)
                            || !(curriculumReq.getCode().matches("^[a-z A-Z0-9]+-?[a-z A-Z0-9]+$"))) {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST,
                                "Curriculum code should be alphanumeric with one (-) allowed, and of 2-10 characters." + ": " + curriculumReq.getCode(),
                                HttpStatus.BAD_REQUEST, null);
                    }
                    MasterCurriculumEntity masterCurriculum = new MasterCurriculumEntity();
                    masterCurriculum.setName(curriculumReq.getName().toUpperCase());
                    masterCurriculum.setCode(curriculumReq.getCode().toUpperCase());

                    InstituteCurriculumEntity instituteCurriculum = new InstituteCurriculumEntity();
                    instituteCurriculum.setInstitute(institute);
                    instituteCurriculum.setMasterCurriculum(masterCurriculum);

                    instituteCurriculumListToBeSaved.add(instituteCurriculum);
                    masterCurriculumListToBeSaved.add(masterCurriculum);
                }
            }
            try {
                masterCurriculumRespository.saveAll(masterCurriculumListToBeSaved);
            } catch (DataIntegrityViolationException e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
                    String columnName = ex.getConstraintName();
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Duplicate data for " + "curriculum " + columnName.substring(columnName.lastIndexOf('.') + 1),
                            HttpStatus.BAD_REQUEST, null);
                }
            }
            try {
                instituteCurriculumRepository.saveAll(instituteCurriculumListToBeSaved);
            } catch (DataIntegrityViolationException e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Curriculum is already available for the given institute.",
                            HttpStatus.BAD_REQUEST, null);
                }
            }
            return instituteCurriculumListToBeSaved;
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }


    @Override
    public List<InstituteCurriculumEntity> getAllCurriculums(String instituteId, String curriculumName) throws SettingsException {
        try {
            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<Predicate> predicates = new ArrayList<>();

            Specification<InstituteCurriculumEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));

                predicates.add(criteriaBuilder.equal(root.get("institute").get("id"), instituteId));

                if (curriculumName != null && !curriculumName.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("masterCurriculum").get("name")),
                            "%" + curriculumName.toLowerCase() + "%"));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return instituteCurriculumRepository.findAll(spec);
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_GET_ALL_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public InstituteCurriculumEntity updateCurriculum(String curriculumId, String instituteId, CurriculumRequest request) {

        try {
            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            InstituteCurriculumEntity instituteCurriculum = instituteCurriculumRepository.findById(curriculumId)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, "Institute Curriculum not found.",
                            HttpStatus.NOT_FOUND, null));

            if (!instituteCurriculum.getInstitute().getId().equalsIgnoreCase(instituteId)) {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Curriculum is not associated with the given institute.",
                        HttpStatus.BAD_REQUEST, null);
            }

            MasterCurriculumEntity masterCurriculum = instituteCurriculum.getMasterCurriculum();
            // .....to be continued.

            return null;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public void deleteCurriculum(String instituteCurriculumId, String instituteId) {
        try {
            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            InstituteCurriculumEntity instituteCurriculum = instituteCurriculumRepository.findByIdAndActive(instituteCurriculumId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, "Institute Curriculum not found.",
                            HttpStatus.NOT_FOUND, null));

            if (!instituteCurriculum.getInstitute().getId().equalsIgnoreCase(instituteId)) {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Curriculum is not associated with the given institute.",
                        HttpStatus.BAD_REQUEST, null);
            }
            instituteCurriculum.setActive(false);
            instituteCurriculum.setStatus(CommonStatus.DELETE);
            instituteCurriculum.setDeletedAt(LocalDateTime.now());

            instituteCurriculumRepository.save(instituteCurriculum);

        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<MasterModules> getAllMasterModules(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterModules> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterModuleRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.MODULES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<MasterFormsEntity> getAllMasterForms(String formName) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterFormsEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (formName != null && !formName.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + formName.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterFormsRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.BRANCH_TYPES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public List<MasterAcademicYearEntity> getAllMasterAcademicYear(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterAcademicYearEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterAcademicYearRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<BranchCurriculumEntity> createBranchCurriculums(List<String> curriculumIds, String branchId) {
        try {
            BranchInfoEntity branchInfoEntity = branchRepository.findByIdAndActive(branchId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<InstituteCurriculumEntity> instituteCurriculumList = instituteCurriculumRepository.
                    findByInstituteIdAndActive(branchInfoEntity.getInstituteId(), true);

            List<BranchCurriculumEntity> branchCurriculumListToBeSaved = new ArrayList<>();

            if (!instituteCurriculumList.isEmpty()) {
                curriculumIds.forEach(requestCurriculumId -> {
                    if (requestCurriculumId.equalsIgnoreCase("") || requestCurriculumId.equalsIgnoreCase(" ")) {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Institute curriculum id should not be blank/empty.",
                                HttpStatus.BAD_REQUEST, null);
                    }
                    Optional<InstituteCurriculumEntity> optionalInstituteCurriculum = instituteCurriculumList.
                            stream().
                            filter(instituteCurriculum -> instituteCurriculum.getId().equalsIgnoreCase(requestCurriculumId)).
                            findAny();
                    if (optionalInstituteCurriculum.isPresent()) {
                        BranchCurriculumEntity branchCurriculum = new BranchCurriculumEntity();
                        branchCurriculum.setInstituteCurriculum(optionalInstituteCurriculum.get());
                        branchCurriculum.setBranchInfoEntity(branchInfoEntity);
                        branchCurriculumListToBeSaved.add(branchCurriculum);
                    } else {
                        throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Provided Institute curriculum not found.",
                                HttpStatus.BAD_REQUEST, null);
                    }

                });
            } else {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Institute curriculums not found.",
                        HttpStatus.BAD_REQUEST, null);
            }
            try {
                branchCurriculumRepository.saveAll(branchCurriculumListToBeSaved);
            } catch (DataIntegrityViolationException e) {
                if (e.getCause() instanceof ConstraintViolationException) {
                    ConstraintViolationException ex = (ConstraintViolationException) e.getCause();
                    throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Curriculum is already exists the given branch.",
                            HttpStatus.BAD_REQUEST, null);
                }
            }
            return branchCurriculumListToBeSaved;
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_CREATE_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<BranchCurriculumEntity> getBranchCurriculums(String branchId, String curriculumName) {
        try {
            branchRepository.findByIdAndActive(branchId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND,
                            MessageConstants.BRANCH_NOT_FOUND, HttpStatus.NOT_FOUND, null));

            List<Predicate> predicates = new ArrayList<>();

            Specification<BranchCurriculumEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));

                predicates.add(criteriaBuilder.equal(root.get("branchInfoEntity").get("id"), branchId));

                if (curriculumName != null && !curriculumName.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("instituteCurriculum").
                            get("masterCurriculum").get("name")), "%" + curriculumName.toLowerCase() + "%"));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return branchCurriculumRepository.findAll(spec);
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_GET_ALL_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public List<MasterTermTypeEntity> getAllMasterTermTypes(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterTermTypeEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return termTypeRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public void deleteBranchCurriculum(String branchCurriculumId, String branchId) {
        try {
            branchRepository.findByIdAndActive(branchId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND,
                            MessageConstants.BRANCH_NOT_FOUND, HttpStatus.NOT_FOUND, null));

            BranchCurriculumEntity branchCurriculumEntity = branchCurriculumRepository.findByIdAndActive(branchCurriculumId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, "Branch Curriculum not found.",
                            HttpStatus.NOT_FOUND, null));

            if (!branchCurriculumEntity.getBranchInfoEntity().getId().equalsIgnoreCase(branchId)) {
                throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, "Curriculum is not associated with the given branch.",
                        HttpStatus.BAD_REQUEST, null);
            }
            branchCurriculumEntity.setActive(false);
            branchCurriculumEntity.setStatus(CommonStatus.DELETE);
            branchCurriculumEntity.setDeletedAt(LocalDateTime.now());

            branchCurriculumRepository.save(branchCurriculumEntity);

        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CURRICULUM_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    public List<MasterDepartmentsEntity> getAllMasterDepartments(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterDepartmentsEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterDepartmentsRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<MasterAcademicClassesEntity> getAllMasterAcademicClasses(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterAcademicClassesEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterAcademicClassesRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<MasterRoleEntity> getAllMasterRoles(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterRoleEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterRoleRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<MasterAcademicCurriculumEntity> getAllMasterAcademicCurriculum(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterAcademicCurriculumEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterAcademicCurriculumRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<MasterTermEntity> getAllMasterTerm(String name) {
        try {
            List<Predicate> predicates = new ArrayList<>();
            Specification<MasterTermEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.equal(root.get("active"), true));
                if (name != null && !name.isEmpty())
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            return masterTermRepository.findAll(spec);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.LANGUAGES_GET_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}
