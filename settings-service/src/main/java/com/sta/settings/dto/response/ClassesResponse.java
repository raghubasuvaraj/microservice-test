package com.sta.settings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClassesResponse {
    private String classId;

    private String className;

    private boolean active;
}
