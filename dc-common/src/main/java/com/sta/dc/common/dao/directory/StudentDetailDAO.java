package com.sta.dc.common.dao.directory;

import com.sta.dc.common.entity.directory.StudentDetailEntity;

import java.util.List;

public interface StudentDetailDAO {

    // DI-30101
    public List<StudentDetailEntity> getStudentDetailsByIds(List<String> studentIds);
}
