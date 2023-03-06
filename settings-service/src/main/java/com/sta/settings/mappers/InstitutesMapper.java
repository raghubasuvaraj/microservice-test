package com.sta.settings.mappers;

import java.util.List;

import com.sta.settings.dto.request.InstitutesRequest;
import com.sta.settings.dto.response.InstitutesResponse;
import com.sta.settings.entities.InstitutesEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstitutesMapper {

	public static InstitutesEntity requestToEntity(InstitutesRequest request) {

		return InstitutesEntity.builder().instituteName(request.getInstituteName())
				.instituteCode(request.getInstituteCode()).afflicationNo(request.getAfflicationNo())
				.trustName(request.getTrustName()).emailid(request.getInstitutionEmail())
				.phoneno(request.getInstitutionPhoneNo()).address(request.getAddress()).city(request.getCity())
				.pincode(request.getPinCode()).area(request.getArea()).contactPerson(request.getContactPersonName())
				.contactPersonNo(request.getContactPersonPhoneNo())
				.contactPersonEmailid(request.getContactPersonEmail()).licenceType(request.getLicenceType())
				.licenseExpiryDate(request.getExpiryDate()).website(request.getWebsiteUrl()).build();
	}

	public static InstitutesEntity updateRequestToEntity(InstitutesRequest request, String id) {

		InstitutesEntity institutesEntity = InstitutesEntity.builder().instituteName(request.getInstituteName())
				.instituteCode(request.getInstituteCode()).afflicationNo(request.getAfflicationNo())
				.trustName(request.getTrustName()).emailid(request.getInstitutionEmail())
				.phoneno(request.getInstitutionPhoneNo()).address(request.getAddress()).city(request.getCity())
				.area(request.getArea()).pincode(request.getPinCode()).contactPerson(request.getContactPersonName())
				.contactPersonNo(request.getContactPersonPhoneNo())
				.contactPersonEmailid(request.getContactPersonEmail()).licenceType(request.getLicenceType())
				.licenseExpiryDate(request.getExpiryDate()).website(request.getWebsiteUrl()).build();
		institutesEntity.setId(id);
		return institutesEntity;
	}

	public static InstitutesResponse entityToResponse(InstitutesEntity institutesEntity) {

		return InstitutesResponse.builder().id(institutesEntity.getId())
				.instituteName(institutesEntity.getInstituteName()).instituteCode(institutesEntity.getInstituteCode())
				.afflicationNo(institutesEntity.getAfflicationNo()).trustName(institutesEntity.getTrustName())
				.institutionEmail(institutesEntity.getEmailid()).institutionPhoneNo(institutesEntity.getPhoneno())
				.address(institutesEntity.getAddress()).area(institutesEntity.getArea())
				.pinCode(institutesEntity.getPincode()).contactPersonName(institutesEntity.getContactPerson())
				.contactPersonEmail(institutesEntity.getContactPersonEmailid())
				.contactPersonPhoneNo(institutesEntity.getContactPersonNo())
				.licenceType(institutesEntity.getLicenceType()).expiryDate(institutesEntity.getLicenseExpiryDate())
				.websiteUrl(institutesEntity.getWebsite()).active(institutesEntity.isActive()).build();
	}

	public static List<InstitutesResponse> mapAllEntityToResponse(List<InstitutesEntity> institutes) {
		return institutes.parallelStream().map(InstitutesMapper::entityToResponse).toList();
	}
}
