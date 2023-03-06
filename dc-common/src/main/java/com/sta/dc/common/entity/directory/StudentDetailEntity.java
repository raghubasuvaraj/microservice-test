package com.sta.dc.common.entity.directory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_admission_student_details")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailEntity extends BaseEntity implements Serializable {

    @Column(name = "student_id")
    public String studentId;

    @Column(name = "reg_id")
    public Integer regId;

    @Column(name = "branch_id")
    public String branchId;

    @Column(name = "student_uid_no")
    public String studentUidNo;

    @Column(name = "caste_category_id")
    public Integer casteCategoryId;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "roll_no")
    public String rollNo;

    @Column(name = "student_name")
    public String studentName;

    @Column(name = "form_id")
    public String formId;

    @Column(name = "admission_no")
    public String admissionNo;

    @Column(name = "date_of_birth")
    public LocalDate dateOfBirth;

    @Column(name = "place_of_birth")
    public String placeOfBirth;

    @Column(name = "gender")
    public String gender;

    @Column(name = "nationality")
    public String nationality;

    @Column(name = "religion")
    public String religion;

    @Column(name = "mother_tongue")
    public String motherTongue;

    @Column(name = "house_id")
    public Integer houseId;

    @Column(name = "admission_category_id")
    public Integer admissionCategoryId;

    @Column(name = "date_of_admission")
    public LocalDate dateOfAdmission;

    @Column(name = "allergic_to")
    public String allergicTo;

    @Column(name = "sms_priority_no")
    public String smsPriorityNo;

    @Column(name = "blood_group_id")
    public String bloodGroupId;

    @Column(name = "passport_no")
    public String passportNo;

    @Column(name = "identification_marks")
    public String identificationMarks;

    @Column(name = "fee_category_type_id")
    public Integer feeCategoryTypeId;

    @Column(name = "details")
    public String details;

    @Column(name = "email_id")
    public String emailId;

    @Column(name = "health_status")
    public String healthStatus;

    @Column(name = "student_interest")
    public String studentInterest;

    @Column(name = "ra_houseno_or_plotno")
    public String raHousenoOrPlotno;

    @Column(name = "ra_locationor_landmark")
    public String raLocationorLandmark;

    @Column(name = "ra_city")
    public String raCity;

    @Column(name = "ra_state")
    public String raState;

    @Column(name = "ra_pincode")
    public String raPincode;

    @Column(name = "ra_country")
    public String raCountry;

    @Column(name = "is_pa_same_as_ca")
    public Boolean isPaSameAsCa;

    @Column(name = "pa_houseno_or_plotno")
    public String paHousenoOrPlotno;

    @Column(name = "pa_location_or_landmark")
    public String paLocationOrLandmark;

    @Column(name = "pa_city")
    public String paCity;

    @Column(name = "pa_state")
    public String paState;

    @Column(name = "pa_pincode")
    public String paPincode;

    @Column(name = "pa_country")
    public String paCountry;

    @Column(name = "address")
    public String address;

    @Column(name = "pa_address")
    public String paAddress;

    @Column(name = "landline_no1")
    public String landlineNo1;

    @Column(name = "landline_no2")
    public String landlineNo2;

    @Column(name = "transport_type_ids")
    public String transportTypeIds;

    @Column(name = "transport")
    public String transport;

    @Column(name = "hostel")
    public String hostel;

    @Column(name = "mess")
    public String mess;

    @Column(name = "student_photo")
    public String studentPhoto;

    @Column(name = "status")
    public String status;

    @Column(name = "action")
    public String action;

    @Column(name = "school_id")
    public Integer schoolId;

    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "user_name")
    public String userName;

    @Column(name = "user_date")
    public LocalDate userDate;

    @Column(name = "rs")
    public String rs;

    @Column(name = "user_school_id")
    public Integer userSchoolId;

    @Column(name = "user_branch_id")
    public Integer userBranchId;

    @Column(name = "neworexisting_students")
    public String neworexistingStudents;

    @Column(name = "employee_id")
    public String employeeId;

    @Column(name = "applied_tc")
    public String appliedTc;

    @Column(name = "admission_withdraw")
    public String admissionWithdraw;

    @Column(name = "not_reporting_students")
    public String notReportingStudents;

    @Column(name = "has_siblings")
    public String hasSiblings;

    @Column(name = "is_special_child")
    public String isSpecialChild;

    @Column(name = "doj")
    public String doj;

    @Column(name = "doj1")
    public String doj1;

    @Column(name = "identification_marks1")
    public String identificationMarks1;

    @Column(name = "groups_id")
    public Integer groupsId;

    @Column(name = "shifted_campus")
    public String shiftedCampus;

    @Column(name = "shifted_campus_id")
    public Integer shiftedCampusId;

    @Column(name = "shifted_to_sid")
    public Integer shiftedToSid;

    @Column(name = "shifted_from_campus_id")
    public Integer shiftedFromCampusId;

    @Column(name = "student_email_id")
    public String studentEmailId;

    @Column(name = "deactivate_reason")
    public String deactivateReason;

    @Column(name = "tc_request")
    public String tcRequest;

    @Column(name = "tc_issue")
    public String tcIssue;

    @Column(name = "campus_transfer")
    public String campusTransfer;

    @Column(name = "previous_student_id")
    public String previousStudentId;

    @Column(name = "from_campus")
    public String fromCampus;

    @Column(name = "eligible_to_promote")
    public String eligibleToPromote;

    @Column(name = "admission_type")
    public String admissionType;

    @Column(name = "cbse_roll_no")
    public String cbseRollNo;

    @Column(name = "aadhar_enrollment_no")
    public String aadharEnrollmentNo;

    @Column(name = "student_uid_type")
    public String studentUidType;

    @Column(name = "placeof_school_studied")
    public String placeofSchoolStudied;

    @Column(name = "reason_for_seaking_admission")
    public String reasonForSeakingAdmission;

    @Column(name = "optional_subjects")
    public String optionalSubjects;

    @Column(name = "last_school_studied")
    public String lastSchoolStudied;

    @Column(name = "last_curriculum_studied")
    public String lastCurriculumStudied;

    @Column(name = "last_class_studied")
    public String lastClassStudied;

    @Column(name = "caste")
    public String caste;

    @Column(name = "emergency_contact_no")
    public String emergencyContactNo;

    @Column(name = "last_attended_date")
    public LocalDate lastAttendedDate;

    @Column(name = "is_single_parent")
    public Boolean isSingleParent;

    @Column(name = "single_parent_type")
    public String singleParentType;

    @Column(name = "tse_upload_data_id")
    public String tseUploadDataId;
}
