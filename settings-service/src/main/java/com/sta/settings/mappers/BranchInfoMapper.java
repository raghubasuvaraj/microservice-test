package com.sta.settings.mappers;

import com.sta.settings.dto.request.BranchRequest;
import com.sta.settings.dto.response.BranchResponse;
import com.sta.settings.entities.BranchInfoEntity;
import com.sta.settings.enums.CommonStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BranchInfoMapper {

    public static BranchInfoEntity convertBranchDTOtoEntity(BranchRequest branchDto){
        return BranchInfoEntity.builder()
                .instituteId(branchDto.getInstituteId())
                .instituteName((branchDto.getInstituteName()))
                .branchName(branchDto.getBranchName())
                .shortCode(branchDto.getShortCode())
                .branchType(branchDto.getBranchType())
                .affliatonNumber(branchDto.getAffliatonNumber())
                .contactName(branchDto.getContactName())
                .mailId(branchDto.getMailId())
                .phoneNumber(branchDto.getPhoneNumber())
                .noOfStudents(branchDto.getNoOfStudents())
                .location(branchDto.getLocation())
                .branchLogo(branchDto.getBranchLogo())
                .isMainBranch(false)
                .status(CommonStatus.PENDING)
                .build();
    }

    public static BranchResponse convertBranchEntityTOResponseDTO(BranchInfoEntity branchInfoEntity){
        return BranchResponse.builder()
                .instituteId(branchInfoEntity.getInstituteId())
                .instituteName((branchInfoEntity.getInstituteName()))
                .branchName(branchInfoEntity.getBranchName())
                .branchId(branchInfoEntity.getId())
                .shortCode(branchInfoEntity.getShortCode())
                .branchType(branchInfoEntity.getBranchType())
                .affliatonNumber(branchInfoEntity.getAffliatonNumber())
                .contactName(branchInfoEntity.getContactName())
                .mailId(branchInfoEntity.getMailId())
                .phoneNumber(branchInfoEntity.getPhoneNumber())
                .noOfStudents(branchInfoEntity.getNoOfStudents())
                .location(branchInfoEntity.getLocation())
                .branchLogo(branchInfoEntity.getBranchLogo())
                .active(branchInfoEntity.isActive())
                .build();
    }
}
