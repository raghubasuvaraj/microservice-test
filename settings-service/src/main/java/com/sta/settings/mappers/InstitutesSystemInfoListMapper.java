package com.sta.settings.mappers;

import com.sta.settings.dto.InstitutesSystemInfoDto;
import com.sta.settings.entities.InstituteSystemInfoEntity;

public interface InstitutesSystemInfoListMapper {
     static InstitutesSystemInfoDto apply(InstituteSystemInfoEntity instituteSystemInfoEntity) {
          InstitutesSystemInfoDto institutesSystemInfoDto = InstitutesSystemInfoDto.builder()
                  .instituteId(instituteSystemInfoEntity.getInstituteId())
                  .country(instituteSystemInfoEntity.getCountry())
                  .timeZone(instituteSystemInfoEntity.getTimeZone())
                  .defaultLanguage(instituteSystemInfoEntity.getDefaultLanguage())
                  .dateFormat(instituteSystemInfoEntity.getDateFormat())
                  .timeFormat(instituteSystemInfoEntity.getTimeFormat())
                  .floatPrecision(instituteSystemInfoEntity.getFloatPrecision())
                  .numberFormat(instituteSystemInfoEntity.getNumberFormat())
                  .firstWeekDay(instituteSystemInfoEntity.getFirstWeekDay())
                  .currencyPrecision(instituteSystemInfoEntity.getCurrencyPrecision())
                  .sessionExpiry(instituteSystemInfoEntity.getSessionExpiry())
                  .build();
          institutesSystemInfoDto.setId(instituteSystemInfoEntity.getId());
          return institutesSystemInfoDto;
     }
}
