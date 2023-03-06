package com.sta.dc.academic.service;

import java.util.List;

public interface ISectionService {

    // AC-20101
    Void addStudentsToSection(String sectionId, List<String> studentIds);
    
}
