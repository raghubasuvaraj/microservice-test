package com.sta.dc.common.repository.academic;

import com.sta.dc.common.entity.academic.AcademicClassEntity;
import com.sta.dc.common.entity.academic.AcademicSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicClassRepository extends JpaRepository<AcademicClassEntity, String> {

    AcademicClassEntity getReferenceByClassesEntityId(String classId);

    List<AcademicClassEntity> findAllByActive(boolean b);

    List<AcademicClassEntity> findByIdIn(List<String> classIds);

    List<AcademicClassEntity> findAllByClassGroupsIdAndActive(String id, boolean b);
}
