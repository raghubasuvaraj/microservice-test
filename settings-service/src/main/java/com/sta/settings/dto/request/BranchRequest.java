package com.sta.settings.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchRequest {

    private String instituteId;
    private String branchName;
    private String branchId;
    private  String shortCode;
    private  String branchType;
    private  String affliatonNumber;
    private  String contactName;
    private  String mailId;
    private  String phoneNumber;
    private  int noOfStudents;
    private  String location;
    private  String branchLogo;
    private  String instituteName;
}
