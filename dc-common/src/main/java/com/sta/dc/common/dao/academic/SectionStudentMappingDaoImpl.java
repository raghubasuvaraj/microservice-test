package com.sta.dc.common.dao.academic;

import com.sta.dc.common.entity.academic.SectionStudentMappingEntity;
import com.sta.dc.common.exception.academic.AcademicException;
import com.sta.dc.common.repository.academic.SectionStudentMappingRepository;
import com.sta.dc.common.constants.MessageConstants.academic.AcademicMessageConstants;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SectionStudentMappingDaoImpl implements SectionStudentMappingDAO {

    @Autowired
    private SectionStudentMappingRepository sectionStudentMappingRepository;

    // AC-30201
    public List<SectionStudentMappingEntity> saveSectionStudentMappings(List<SectionStudentMappingEntity> sectionStudentMappingsToBeSaved) {
        try {
            return sectionStudentMappingRepository.saveAll(sectionStudentMappingsToBeSaved);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new AcademicException(HttpStatus.BAD_REQUEST.toString(),
                        AcademicMessageConstants.DUPLICATE_STUDENT_EXCEPTION,
                        HttpStatus.BAD_REQUEST, null);
            }
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
