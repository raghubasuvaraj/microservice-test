package com.sta.settings.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.sta.dc.common.entity.academic.AcademicClassEntity;
import com.sta.dc.common.entity.academic.AcademicSectionEntity;
import com.sta.dc.common.entity.academic.MasterAcademicClassesEntity;
import com.sta.dc.common.enums.CommonStatus;
import com.sta.dc.common.repository.academic.AcademicClassRepository;
import com.sta.dc.common.repository.academic.AcademicSectionRepository;
import com.sta.settings.repository.MasterAcademicClassesRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sta.settings.dto.request.SectionAlternativeRequest;
import com.sta.settings.dto.request.SectionDeleteRequest;
import com.sta.settings.dto.request.SectionRequest;
import com.sta.settings.dto.response.SectionWithClassResponse;
import com.sta.settings.dto.response.SectionsResponse;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.BranchInfoRepository;
import com.sta.settings.repository.SectionsRepository;
import com.sta.settings.service.SectionService;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("sectionService")
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionsRepository sectionsRepository;

	@Autowired
	private BranchInfoRepository branchRepository;

	@Autowired
	private AcademicSectionRepository academicSectionRepository;

	@Autowired
	private AcademicClassRepository academicClassRepository;

	@Autowired
	private MasterAcademicClassesRepository masterAcademicClassesRepository;

	/**
	 * Create a section either under a Class or Curriculum. Branch took as mandatory
	 * (according to UI design, will change it, if it is no necessary).
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public SectionWithClassResponse createSections(SectionRequest request) {
		checkTheCreateSectionRequest(request.getSections());

		try {
			SectionWithClassResponse response = null;
			List<SectionsResponse> sectionsResponse = new ArrayList<>();

			AcademicClassEntity academicClass = academicClassRepository.getReferenceByClassesEntityId(request.getClassId());

			for (SectionAlternativeRequest sec : request.getSections()) {
				if (!academicSectionRepository.existsByNameAndAcademicClassIdAndActive(sec.getName(),
						request.getClassId(), true)) {
					log.info("New section is going to create");
					AcademicSectionEntity academicSectionEntity = new AcademicSectionEntity();
					academicSectionEntity.setName(sec.getName());
					academicSectionEntity.setShortCode(sec.getShortCode());
					academicSectionEntity.setAcademicClass(academicClass);
					academicSectionEntity = academicSectionRepository.save(academicSectionEntity);
					if (!StringUtils.isBlank(academicSectionEntity.getId())) {
						log.info("Section: " + sec.getName() + " created successfully!");
						sectionsResponse.add(new SectionsResponse(academicSectionEntity.getId(), academicSectionEntity.getName(),
								academicSectionEntity.isActive()));
					}
				}
			}
			if (!CollectionUtils.isEmpty(sectionsResponse)) {
				log.info("Sections saved and response generated.");
				response = new SectionWithClassResponse(academicClass.getId(), academicClass.getName(),
						sectionsResponse);
			}
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.SECTION_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * Check during create a new section.
	 *
	 * @param request
	 */
	private void checkTheCreateSectionRequest(Set<SectionAlternativeRequest> request) {
		if (!CollectionUtils.isEmpty(request)) {
			int requestCount = request.size();

			Set<String> idSet = request.stream().filter(item -> !StringUtils.isBlank(item.getId()))
					.map(SectionAlternativeRequest::getId).collect(Collectors.toSet());
			if (!CollectionUtils.isEmpty(idSet)) {
				log.error("Id found during the section Post API");
				throw new SettingsException(ErrorCodesConstants.METHOD_NOT_ALLOWED,
						MessageConstants.SECTION_ONLY_CREATE, HttpStatus.METHOD_NOT_ALLOWED, null);
			}

			Set<String> sectionName = request.stream().map(SectionAlternativeRequest::getName)
					.collect(Collectors.toSet());
			if (requestCount != sectionName.size()) {
				log.error("Duplicated sections");
				throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.SECTION_REQUEST,
						HttpStatus.BAD_REQUEST, null);
			}
		} else {
			log.error("Sections is mandatory.");
			throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.SECTION_MANDATORY,
					HttpStatus.BAD_REQUEST, null);
		}
	}

	/**
	 * Update the section if it has id or else will check it is already exist with
	 * the branch id, class id and active=true. If not exist will create a new
	 * section.
	 *
	 * @param request
	 * @return
	 */
	@Override
	public SectionWithClassResponse updateSections(SectionRequest request) {

		try {
			Long currentTime = new Date().getTime();
			SectionWithClassResponse response = null;
			List<SectionsResponse> sectionsResponse = new ArrayList<>();

			AcademicClassEntity academicClass = academicClassRepository.getReferenceById(request.getClassId());

			// delete the section removed from request.
			findTheRemovedSectionAndDeleteFromSystem(request.getSections(),
					request.getClassId());

			for (SectionAlternativeRequest sec : request.getSections()) {
				if (!StringUtils.isBlank(sec.getId())) {
					log.info("Updating the sections");
					AcademicSectionEntity academicSectionEntity= academicSectionRepository.getReferenceById(sec.getId());
					academicSectionEntity.setName(sec.getName());
					academicSectionEntity.setAcademicClass(academicClass);
					academicSectionEntity.setShortCode(sec.getShortCode());

					academicSectionEntity = academicSectionRepository.save(academicSectionEntity);
					log.info("Section : " + sec.getName() + " updated successfully!");
					sectionsResponse.add(new SectionsResponse(academicSectionEntity.getId(), academicSectionEntity.getName(),
							academicSectionEntity.isActive()));
				} else {
					if (!academicSectionRepository.existsByNameAndAcademicClassIdAndActive(
							sec.getName(), request.getClassId(), true)) {
						log.info("New section is going to create, in put api");
						AcademicSectionEntity academicSectionEntity = new AcademicSectionEntity();
						academicSectionEntity.setName(sec.getName());
						academicSectionEntity.setShortCode(sec.getShortCode());
						academicSectionEntity.setAcademicClass(academicClass);
						academicSectionEntity = academicSectionRepository.save(academicSectionEntity);
						if (!StringUtils.isBlank(academicSectionEntity.getId())) {
							log.info("Section : " + sec.getName() + " created successfully!");
							sectionsResponse.add(new SectionsResponse(academicSectionEntity.getId(), academicSectionEntity.getName(),
									academicSectionEntity.isActive()));
						}
					}
				}
			}
			if (!CollectionUtils.isEmpty(sectionsResponse)) {
				log.info("Sections saved and response generated.");
				response = new SectionWithClassResponse(academicClass.getId(), academicClass.getName()
						, sectionsResponse);
			}
			return response;
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.SECTION_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}
	/**
	 * 1. list down the existing Id coming from the request.
	 * <p>
	 * 2. List down the existing Id from the system using branch id, classId and
	 * active=true.
	 * <p>
	 * 3. Compare first and second list, will retrieve which Id is not exist, that
	 * particular section will be set to active=false
	 *
	 * @param request
	 * @param classId
	 */
	private void findTheRemovedSectionAndDeleteFromSystem(Set<SectionAlternativeRequest> request,
														  String classId) {
		if (!CollectionUtils.isEmpty(request)) {
			log.info("Listing the ids came from request.");
			Set<String> requestIdSet = request.stream().map(SectionAlternativeRequest::getId)
					.collect(Collectors.toSet());
			log.info("Listing the existing id.");
			Set<String> existingIds = academicSectionRepository.findActiveIdByAcademicClassId(classId, true);
			log.info("Listing the removed sections from request.");
			Set<String> removedIds = existingIds.stream().filter(eIds -> !requestIdSet.contains(eIds))
					.collect(Collectors.toSet());
			if (!CollectionUtils.isEmpty(removedIds)) {
				LocalDateTime currentTime = LocalDateTime.now();
				int setStatus = academicSectionRepository.setActiveStatus(removedIds, CommonStatus.DELETE,false, currentTime);
				log.info("Set active status into: ", setStatus);
			}
		}
	}

	/**
	 * With the help of a specific class id bring the sections
	 *
	 * @param classId
	 * @return
	 */
	@Override
	public SectionWithClassResponse getAllSectionBySpecificClass(String classId) {
		try {
			SectionWithClassResponse response = null;
			AcademicClassEntity academicClass = academicClassRepository.getReferenceByClassesEntityId(classId);
			List<AcademicSectionEntity> sectionEntities = academicSectionRepository.getAllByAcademicClassIdAndActive(classId,
					true);
			if (!CollectionUtils.isEmpty(sectionEntities)) {
				log.info("All the section listed by class id.");
				List<SectionsResponse> sectionsResponse = sectionEntities.stream()
						.map(sEntity -> new SectionsResponse(sEntity.getId(), sEntity.getName(), sEntity.isActive()))
						.toList();
				Optional<MasterAcademicClassesEntity> optClass = masterAcademicClassesRepository.findById(classId);

				response = new SectionWithClassResponse(optClass.get().getId(), optClass.get().getName(),
						sectionsResponse);
			} else {
				log.error("Section not found");
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.SECTION_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);
			}
			return response;
		} catch (SettingsException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(e.getErrorCode(), e.getMessage(), e.getStatusCode(), e);
		}
	}
	/**
	 * Get all the classes and assigned sections to each particular class.
	 *
	 * @return
	 */
	@Override
	public List<SectionWithClassResponse> getAllClassWithSections() {
		try {
			List<SectionWithClassResponse> response = new ArrayList<>();
			log.info("Get all the academicClasses where active-true");
			List<AcademicClassEntity> academicClasses = academicClassRepository.findAllByActive(true);
			if (!CollectionUtils.isEmpty(academicClasses)) {
				log.info("All the academicClasses listed");
				for (AcademicClassEntity acEntity : academicClasses) {
					log.info("Find the sections assigned to each class");
					List<AcademicSectionEntity> sectionEntities = academicSectionRepository
							.findAllByAcademicClassIdAndActive(acEntity.getId(), true);
					if (!CollectionUtils.isEmpty(sectionEntities)) {
						log.info("All the section listed by class id.");
						List<SectionsResponse> sectionsResponse = sectionEntities.stream().map(
										sEntity -> new SectionsResponse(sEntity.getId(), sEntity.getName(), sEntity.isActive()))
								.toList();
						log.info("Setting the response...");
						response.add(new SectionWithClassResponse(acEntity.getId(), acEntity.getName(),
								 sectionsResponse));
					}
				}
			} else {
				log.error("Classes can not found");
				throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.CLASS_NOT_FOUND,
						HttpStatus.NOT_FOUND, null);
			}
			return response;
		} catch (SettingsException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(e.getErrorCode(), e.getMessage(), e.getStatusCode(), e);
		}
	}

	/**
	 * Set the active=false, active column consider as delete status
	 *
	 * @param sections
	 * @return
	 */
	@Override
	public Boolean deleteSections(SectionDeleteRequest request) {
		try {
			boolean response = false;
			if (!CollectionUtils.isEmpty(request.getSections())) {
				log.info("Converting section into deleted...");
				LocalDateTime currentTime = LocalDateTime.now();
				int setStatus = academicSectionRepository.setActiveStatus(request.getSections(), CommonStatus.DELETE,false, currentTime);
				log.info("Section active status changed into: ", setStatus > 0);
				response = setStatus > 0;
			} else {
				log.error("No data available from the request.");
				throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.SECTION_MANDATORY,
						HttpStatus.BAD_GATEWAY, null);
			}
			return response;
		} catch (SettingsException se) {
			throw new SettingsException(se.getErrorCode(), se.getMessage(), se.getStatusCode(), se.getCause());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
					MessageConstants.SECTION_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

}
