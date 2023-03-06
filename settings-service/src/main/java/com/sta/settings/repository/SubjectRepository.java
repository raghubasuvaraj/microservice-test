package com.sta.settings.repository;

import com.sta.settings.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, String> {

    boolean existsByCurriculumIdAndSubjectName(String curriculum_id, String subjectName);

    @Query(value = "select s from SubjectEntity s where s.curriculumId = ?1")
    List<SubjectEntity> findByCurriculumId(String curriculum_id);
}
