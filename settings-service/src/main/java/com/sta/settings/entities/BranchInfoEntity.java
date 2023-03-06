package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mse_branches")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchInfoEntity  extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3879025180544377789L;

//    @Column(name = "branch_id")
//    private String branchId;
    @Column(name = "institute_id")
    private String instituteId;
    @Column(name = "branch_Name")
    private String branchName;
    @Column(name = "short_Code")
    private String shortCode;
    @Column(name = "branch_Type")
    private String branchType;
    @Column(name = "affliaton_Number")
    private String affliatonNumber;
    @Column(name = "contact_Name")
    private String contactName;
    @Column(name = "mail_Id")
    private String mailId;
    @Column(name = "phone_Number")
    private String phoneNumber;
    @Column(name = "no_of_Students")
    private int noOfStudents;
    @Column(name = "location")
    private String location;
    @Column(name = "branch_Logo")
    private String branchLogo;
    @Column(name = "institute_Name")
    private String instituteName;
    @Column(name="is_main_branch")
    private Boolean isMainBranch;
	@Column(name="active")
	private boolean active= true;
    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
    @Column(name = "comments", columnDefinition = "text")
    private String comments;

}
