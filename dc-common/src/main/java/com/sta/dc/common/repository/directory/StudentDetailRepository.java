package com.sta.dc.common.repository.directory;

import com.sta.dc.common.entity.directory.StudentDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDetailRepository extends JpaRepository<StudentDetailEntity, String>{

    List<StudentDetailEntity> findByActiveAndStudentIdIn(boolean b, List<String> studentIds);
}
