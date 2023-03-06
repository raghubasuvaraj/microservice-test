package com.sta.dc.common.dao.directory;

import com.sta.dc.common.entity.directory.StudentDetailEntity;
import com.sta.dc.common.repository.directory.StudentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDetailDaoImpl implements StudentDetailDAO {

    @Autowired
    private StudentDetailRepository studentDetailRepository;

    // DI-30101
    public List<StudentDetailEntity> getStudentDetailsByIds(List<String> studentIds) {
        try {
            return studentDetailRepository.findByActiveAndStudentIdIn(true, studentIds);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
