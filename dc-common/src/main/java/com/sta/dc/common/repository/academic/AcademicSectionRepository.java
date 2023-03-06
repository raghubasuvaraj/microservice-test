package com.sta.dc.common.repository.academic;

import com.sta.dc.common.entity.academic.AcademicClassEntity;
import com.sta.dc.common.entity.academic.AcademicSectionEntity;
import com.sta.dc.common.enums.CommonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AcademicSectionRepository extends JpaRepository<AcademicSectionEntity, String> {

    Optional<AcademicSectionEntity> findByIdAndActive(String sectionId, boolean b);

    boolean existsByNameAndAcademicClassIdAndActive(String name, String classId, boolean b);

    @Query("select se.id from #{#entityName} se where se.academicClass.id=?1 and se.active=?2")
    Set<String> findActiveIdByAcademicClassId(@Param("classId") String classId, @Param("b") boolean b);

    @Transactional
    @Modifying
    @Query("update #{#entityName} se set se.active =:active,se.deletedAt=:currentTime, se.status=:status, se.lastModifiedAt=:currentTime where se.id in(:ids)")
    int setActiveStatus(Set<String> ids, CommonStatus status, boolean active, LocalDateTime currentTime);

    @Query("From AcademicSectionEntity ac where ac.academicClass.classesEntity.masterClassesEntity.id=?1 and ac.active=?2")
    List<AcademicSectionEntity> getAllByAcademicClassIdAndActive(@Param("classId")String classId, @Param("b") boolean b);

    List<AcademicSectionEntity> findAllByAcademicClassIdAndActive(String id, boolean b);
}
