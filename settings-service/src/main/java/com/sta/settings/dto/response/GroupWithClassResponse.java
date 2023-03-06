package com.sta.settings.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GroupWithClassResponse {

    private String groupId;

    private String groupName;
    private List<ClassesResponse> classesResponse = new ArrayList<>();
}
