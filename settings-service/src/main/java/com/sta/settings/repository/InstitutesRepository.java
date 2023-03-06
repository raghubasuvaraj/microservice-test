package com.sta.settings.repository;

import java.util.List;
import java.util.Optional;

import com.sta.settings.entities.InstitutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sta.settings.dto.response.InstitutesResponse;

@Repository
public interface InstitutesRepository extends JpaRepository<InstitutesEntity, String> {

	boolean existsByInstituteName(String institute);
	
	boolean existsByIdAndActive(String institute, boolean active);

	boolean existsByInstituteNameOrInstituteCode(String institute, int code);

	boolean existsByInstituteNameAndIdNot(String institute, String id);

	boolean existsByInstituteCodeAndIdNot(int instituteCode, String id);
	
	@Query("select new com.sta.settings.dto.response.InstitutesResponse(i.id, "
			+ "i.instituteName, i.instituteCode, i.afflicationNo, i.trustName, "
			+ "i.emailid, i.phoneno, i.address, i.city, "
			+ "i.area, i.pincode, i.contactPerson, i.contactPersonNo, "
			+ "i.contactPersonEmailid, i.licenceType, i.licenseExpiryDate, i.website, i.active) "
			+ "from #{#entityName} i where i.active = true and i.id=:id")
	InstitutesResponse findInstituteById(String id);
	
	@Query("select new com.sta.settings.dto.response.InstitutesResponse(i.id, "
			+ "i.instituteName, i.instituteCode, i.afflicationNo, i.trustName, "
			+ "i.emailid, i.phoneno, i.address, i.city, "
			+ "i.area, i.pincode, i.contactPerson, i.contactPersonNo, "
			+ "i.contactPersonEmailid, i.licenceType, i.licenseExpiryDate, i.website, i.active) "
			+ "from #{#entityName} i where i.active = true")
	List<InstitutesResponse> findAllInstitutes();
	
    Optional<InstitutesEntity> findByIdAndActive(String instituteId, boolean b);
}
