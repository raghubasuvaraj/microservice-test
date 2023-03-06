package com.sta.settings.mappers;


import com.sta.settings.dto.InstitutesSystemInfoDto;
import com.sta.settings.entities.InstituteSystemInfoEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InstituteSystemInfoMapperUtil implements InstitutesSystemInfoListMapper{

    public static InstituteSystemInfoEntity toInstituteSystemInfoEntity(InstitutesSystemInfoDto institutesSystemInfoDto){
        return InstituteSystemInfoEntity.builder()
                .instituteId(institutesSystemInfoDto.getInstituteId())
                .country(institutesSystemInfoDto.getCountry())
                .timeZone(institutesSystemInfoDto.getTimeZone())
                .defaultLanguage(institutesSystemInfoDto.getDefaultLanguage())
                .dateFormat(institutesSystemInfoDto.getDateFormat())
                .timeFormat(institutesSystemInfoDto.getTimeFormat())
                .floatPrecision(institutesSystemInfoDto.getFloatPrecision())
                .numberFormat(institutesSystemInfoDto.getNumberFormat())
                .firstWeekDay(institutesSystemInfoDto.getFirstWeekDay())
                .currencyPrecision(institutesSystemInfoDto.getCurrencyPrecision())
                .sessionExpiry(institutesSystemInfoDto.getSessionExpiry())
                .build();
    }

    public static InstitutesSystemInfoDto toInstituteSystemInfoDto(InstituteSystemInfoEntity instituteSystemInfoEntityResp) {
        InstitutesSystemInfoDto institutesSystemInfoDto = InstitutesSystemInfoDto.builder()
                .instituteId(instituteSystemInfoEntityResp.getInstituteId())
                .country(instituteSystemInfoEntityResp.getCountry())
                .timeZone(instituteSystemInfoEntityResp.getTimeZone())
                .defaultLanguage(instituteSystemInfoEntityResp.getDefaultLanguage())
                .dateFormat(instituteSystemInfoEntityResp.getDateFormat())
                .timeFormat(instituteSystemInfoEntityResp.getTimeFormat())
                .floatPrecision(instituteSystemInfoEntityResp.getFloatPrecision())
                .numberFormat(instituteSystemInfoEntityResp.getNumberFormat())
                .firstWeekDay(instituteSystemInfoEntityResp.getFirstWeekDay())
                .currencyPrecision(instituteSystemInfoEntityResp.getCurrencyPrecision())
                .sessionExpiry(instituteSystemInfoEntityResp.getSessionExpiry())
                .build();
        institutesSystemInfoDto.setId(instituteSystemInfoEntityResp.getId());
        return institutesSystemInfoDto;
    }

    public static InstituteSystemInfoEntity toUpdateInstituteSystemInfoEntity(InstitutesSystemInfoDto existingInstituteSystemInfoEntity, String id) {
        InstituteSystemInfoEntity instituteSystemInfoEntity =  InstituteSystemInfoEntity.builder()
                .instituteId(existingInstituteSystemInfoEntity.getInstituteId())
                .country(existingInstituteSystemInfoEntity.getCountry())
                .timeZone(existingInstituteSystemInfoEntity.getTimeZone())
                .defaultLanguage(existingInstituteSystemInfoEntity.getDefaultLanguage())
                .dateFormat(existingInstituteSystemInfoEntity.getDateFormat())
                .timeFormat(existingInstituteSystemInfoEntity.getTimeFormat())
                .floatPrecision(existingInstituteSystemInfoEntity.getFloatPrecision())
                .numberFormat(existingInstituteSystemInfoEntity.getNumberFormat())
                .firstWeekDay(existingInstituteSystemInfoEntity.getFirstWeekDay())
                .currencyPrecision(existingInstituteSystemInfoEntity.getCurrencyPrecision())
                .sessionExpiry(existingInstituteSystemInfoEntity.getSessionExpiry())
                .build();
        instituteSystemInfoEntity.setId(id);
        return instituteSystemInfoEntity;
    }



}
