package com.sta.settings.utility;

public class MessageConstants {

	//misc
	public static final String INVALID_STATUS = "Invalid status please check the request.";
	public static final String FAILED_TO_CHECK_REQUEST = "Failed to check request";

	//InstitutesEntity
	public static final String INSTITUTE_CREATED = "Institute created.";
	public static final String INSTITUTE_CREATE_FAILED = "Failed to create institute.";

	public static final String INSTITUTE_UPDATED = "Institute updated.";
	public static final String INSTITUTE_UPDATE_FAILED = "Failed to update institute.";

	public static final String INSTITUTE_GET_BY_ID = "Institute detailes get by id.";
	public static final String INSTITUTE_GET_BY_ID_FAILED = "Failed to get the institute by id.";

	public static final String INSTITUTE_GET_ALL = "All Institute detailes fetched.";
	public static final String INSTITUTE_GET_ALL_FAILED = "Failed to get all the institutes";

	public static final String INSTITUTE_STATUS = "Institute status updated.";
	public static final String INSTITUTE_STATUS_FAILED = "Failed to update the institute status";

	public static final String INSTITUTE_DELETED = "Institute deleted.";
	public static final String INSTITUTE_DELETE_FAILED = "Failed to delete the institute.";

	public static final String INSTITUTE_ALREADY_EXIST = "Institute already exist by this Institute name or code";
	public static final String INSTITUTE_NOT_FOUND = "Institute not exist";

	//RolesEntity
	public static final String ROLES_CREATED = "Role created successfully.";
	public static final String ROLES_CREATE_FAILED = "Failed to create roles.";

	public static final String ROLE_UPDATED = "Role updated successfully.";
	public static final String ROLE_UPDATE_FAILED = "Failed to update Role.";

	public static final String ROLE_GET_BY_ID = "Role detailes get by id.";
	public static final String ROLE_GET_BY_ID_FAILED = "Failed to get the role by id.";

	public static final String ROLE_GET_ALL = "All role detailes fetched.";
	public static final String ROLE_GET_ALL_FAILED = "Failed to get all the role";

	public static final String ROLE_STATUS = "Role status updated.";
	public static final String ROLE_STATUS_FAILED = "Failed to update the role status";

	public static final String ROLE_DELETED = "Role deleted.";
	public static final String ROLE_DELETE_FAILED = "Failed to delete the role.";

	public static final String ROLE_ALREADY_EXIST = "Role already exist.";
	public static final String ROLE_NOT_FOUND = "Role not exist";

	public static final String ROLE_DUPLICATE = "Roles duplicated, please check the request.";
	public static final String ROLE_EXIST_CHECKING = "Roles existance checking failed";
	public static final String ROLE_DUPLICATE_CHEKING = "Roles duplication checking failed";

	//Departments
	public static final String DEPARTMENTS_CREATED = "Departments created.";
	public static final String DEPARTMENTS_CREATE_FAILED = "Failed to create departments.";

	public static final String DEPARTMENT_UPDATED = "Department updated.";
	public static final String DEPARTMENT_UPDATE_FAILED = "Failed to update department.";

	public static final String DEPARTMENT_GET_BY_ID = "Department detailes get by id.";
	public static final String DEPARTMENT_GET_BY_ID_FAILED = "Failed to get the department by id.";

	public static final String DEPARTMENT_GET_ALL = "All department detailes fetched.";
	public static final String DEPARTMENT_GET_ALL_FAILED = "Failed to get all the department";

	public static final String DEPARTMENT_STATUS = "Department status updated.";
	public static final String DEPARTMENT_STATUS_FAILED = "Failed to update the department status";

	public static final String DEPARTMENT_DELETED = "Department deleted.";
	public static final String DEPARTMENT_DELETE_FAILED = "Failed to delete the department.";

	public static final String DEPARTMENT_ALREADY_EXIST = "Department already exist.";
	public static final String DEPARTMENT_NOT_FOUND = "Department not exist";
	public static final String DEPARTMENT_ADD = "Please add at least one department";
	public static final String DEPARTMENT_NO_DUPLICATE = "Duplicate data is not allowed, please check the request.";
	public static final String DEPARTMENT_UPDATE_NOT_ALLOWED = "Update cannot allowed here.";
	public static final String DEPARTMENT_CHECKING_FAILED = "Failed to check departments";
	public static final String DEPARTMENT_ID_MANDATORY = "Department id is manatory.";

	//branch
	public static final String BRANCH_STATUS = "Branch status updated.";
	public static final String BRANCH_STATUS_FAILED = "Failed to update the branch status";
	public static final String BRANCH_NOT_FOUND = "Branch not found";
	public static final String BRANCH_INSTITUTE_NO_RELATION = "The given branch and institute has no relation";

	//master
	public static final String MODULES_GET_ALL_FAILED ="Failed to list all master modules";
	public static final String COUNTRIES_GET_ALL_FAILED = "Failed to list all countries";
	public static final String LANGUAGES_GET_ALL_FAILED ="Failed to list all languages" ;
	public static final String BRANCH_TYPES_GET_ALL_FAILED = "Failed to list all branch types";
	public static final String CLASS_GROUPS_GET_ALL_FAILED = "Failed to list all class groups";
	public static final String CLASS_GROUPS_CREATE_FAILED = "Failed to create  class groups";
	public static final String CLASS_GROUPS_NOT_FOUND = "class group not exist";

	public static final String STANDARDS_ADDING_TO_CLASS_GROUPS_FAILED = "Failed to add standards to class group";

	public static final String REMOVING_STANDARDS_FROM_CLASS_GROUP_FAILED = "Failed to remove standards from class group";



	public static final String CURRICULUM_ALREADY_EXIST = "Curriculum already exist";
	public static final String CURRICULUM_NOT_FOUND = "Curriculum not exist";

	public static final String INVALID_CURRICULUM = "Curriculum is not associated with the given institute.";


	public static final String CURRICULUM_CREATE_FAILED = "Failed to create Curriculums";
	public static final String CURRICULUM_GET_ALL_FAILED = "Failed to list curriculums";
	public static final String CURRICULUM_UPDATE_FAILED = "Failed to update curriculum";
	public static final String CURRICULUM_DELETE_FAILED = "Failed to delete curriculum";
	public static final String TIMEZONE_GET_ALL_FAILED = "Failed to list all timezones";
	public static final String TERMS_GET_ALL_FAILED = "Failed to list all terms";
	public static final String TERM_NOT_FOUND = "Term not exist";
	public static final String TERM_NOT_ASSOCIATED ="Term not associated with given institute" ;
	public static final String TERMS_GET_BY_ID_FAILED = "Failed to get term by id";
	public static final String TERM_TYPE_NOT_FOUND = "Term type not exist";
	public static final String TERMS_CREATE_FAILED ="Failed to create terms" ;
	public static final String TERM_ALREADY_EXIST = "Term name or code already exist";

	public static final String INVALID_TERM = "Term is not associated with the given institute.";
	public static final String TERM_UPDATE_FAILED = "Failed to update term";
	public static final String TERM_DELETE_FAILED = "Failed to delete term";
	public static final String INVALID_UPDATE_FOR_MAIN_BRANCH ="Main branch can be one for a institute" ;
	public static final String MAIN_BRANCH_UPDATED_FAILED ="Failed to update branch as a main branch" ;
	public static final String INVALID_TERM_BRANCH = "Term is not associated with the given branch";
	public static final String INVALID_DATE = "start date must be before end date";

	public static final String PUT_OPERATION_NOT_ALLOWED = "Update operation cannot be full filled by Create operation, please check the request.";

	// AssignFormToBranchEntity
	public static final String ASSIGNE_FORM_CREATED = "Master form assigned to branch.";
	public static final String ASSIGNE_FORM_CREATE_FAILED = "Failed to assign master form to branch.";
	public static final String ASSIGNE_FORM_UPDATED = "Updated assigned master form to branch.";
	public static final String ASSIGNE_FORM_UPDATE_FAILED = "Failed to update assigned master form to branch.";
	public static final String ASSIGNE_FORM_GET_BY_BARANCH_ID = "Assigned form detailes get by branch id.";
	public static final String ASSIGNE_FORM_GET_BY_BARANCH_ID_FAILED = "Failed to get Assigned form detailes by branch id.";
	public static final String ASSIGNE_FORM_GET_ALL = "All assigned master form detailes fetched.";
	public static final String ASSIGNE_FORM_GET_ALL_FAILED = "Failed to get all the assigned master forms";
	public static final String ASSIGNE_FORM_DELETED = "Master-form unassigned from branch.";
	public static final String ASSIGNE_FORM_DELETE_FAILED = "Failed to unassign master form from branch.";
	public static final String ASSIGNE_FORM_ALREADY_EXIST = "This master-form already assigned to branch.";
	public static final String ASSIGNE_FORM_NOT_FOUND = "Master-form is not assigned to this branch";

	// MasterForms
	public static final String MASTER_FORM_CREATED = "Master form created.";
	public static final String MASTER_FORM_CREATE_FAILED = "Failed to create master form.";
	public static final String MASTER_FORM_UPDATED = "Updated master form.";
	public static final String MASTER_FORM_UPDATE_FAILED = "Failed to update master form";
	public static final String MASTER_FORM_GET_BY_CARRICULUM_ID = "Master forms listed by carruculum.";
	public static final String MASTER_FORM_GET_BY_CARRICULUM_ID_FAILED = "Failed to list master form detailes by carriculum.";
	public static final String MASTER_FORM_GET_ALL = "All master form detailes fetched.";
	public static final String MASTER_FORM_GET_ALL_FAILED = "Failed to get all the master forms";
	public static final String MASTER_FORM_DELETED = "Master-form unassigned from branch.";
	public static final String MASTER_FORM_DELETE_FAILED = "Failed to unassign master form from branch.";
	public static final String MASTER_FORM_ALREADY_EXIST = "This master-form already exist.";
	public static final String MASTER_FORM_NOT_FOUND = "Master-form is not found";

	// Institute group
	public static final String INSTITUTE_GROUP_CREATED = "Institute group created.";
	public static final String INSTITUTE_GROUP_CREATE_FAILED = "Failed to create institute group.";
	public static final String INSTITUTE_GROUP_UPDATED = "Updated institute group.";
	public static final String INSTITUTE_GROUP_UPDATE_FAILED = "Failed to update institute group";
	public static final String INSTITUTE_GROUP_GET_BY_ID = "Institute group details fetched.";
	public static final String INSTITUTE_GROUP_GET_BY_ID_FAILED = "Failed to fetch institute group.";
	public static final String INSTITUTE_GROUP_GET_ALL = "All institute group detailes fetched.";
	public static final String INSTITUTE_GROUP_GET_ALL_FAILED = "Failed to get all the institute group";
	public static final String INSTITUTE_GROUP_DELETED = "institute group deleted.";
	public static final String INSTITUTE_GROUP_DELETE_FAILED = "Failed to delete institute group.";
	public static final String INSTITUTE_GROUP_ALREADY_EXIST = "This institute group already exist.";
	public static final String INSTITUTE_GROUP_NOT_FOUND = "Institute group is not found";


	// SectionEntity
	public static final String SECTION_CREATED = "Section created.";
	public static final String SECTION_CREATE_FAILED = "Failed to create section.";

	public static final String SECTION_UPDATED = "Section updated.";
	public static final String SECTION_UPDATE_FAILED = "Failed to update section.";

	public static final String SECTION_GET_BY_ID = "Section detailes get by id.";
	public static final String SECTION_GET_BY_ID_FAILED = "Failed to get the section by id.";

	public static final String SECTION_GET_ALL = "All section detailes fetched.";
	public static final String SECTION_GET_ALL_FAILED = "Failed to get all the section";

	public static final String SECTION_DELETED = "Section deleted.";
	public static final String SECTION_DELETE_FAILED = "Failed to delete the section.";

	public static final String SECTION_ALREADY_EXIST = "Section already exist.";
	public static final String SECTION_NOT_FOUND = "Section not exist";

	public static final String SECTION_GET_BY_CLASS = "Sections detailes get by class/curriculum.";
	public static final String SECTION_GET_BY_CLASS_FAILED = "Failed to get the sections by class/curriculum.";

	public static final String SECTION_MANDATORY = "Please add at least one section";
	public static final String SECTION_ONLY_CREATE = "Sorry only section create supports here.";
	public static final String SECTION_REQUEST = "Section cannot be duplicated or empty, please check your request.";

	// ClassEntity
	public static final String CLASS_NOT_FOUND = "Class not found";


	public static final String MASTER_DATA_GET_ALL_FAILED = "Failed to get all the master field data";

	public static final String AREA_NOT_FOUND = "Area not exist";

	public static final String MASTER_CURRICULUM_NOT_FOUND = "master curriculum not found";

	public static final String MASTER_DEPARTMENT_NOT_FOUND = "Master Department not exist";

}
