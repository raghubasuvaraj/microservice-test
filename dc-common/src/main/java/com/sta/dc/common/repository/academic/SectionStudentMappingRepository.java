package com.sta.dc.common.repository.academic;

import com.sta.dc.common.entity.academic.SectionStudentMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionStudentMappingRepository extends JpaRepository<SectionStudentMappingEntity, String> {

}
