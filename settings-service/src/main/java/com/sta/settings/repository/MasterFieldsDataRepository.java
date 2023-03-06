package com.sta.settings.repository;

import com.sta.settings.entities.MasterFieldsDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterFieldsDataRepository extends JpaRepository<MasterFieldsDataEntity, String> {
   @Query("FROM  MasterFieldsDataEntity mf INNER JOIN MasterCategoryEntity  mc ON " +
           "mf.categoryId=mc.id WHERE mc.name=:categoryName  " +
           "AND mf.active = true AND (:name is null or :name is '' or mf.name1 like :name) ")
    List<MasterFieldsDataEntity> getAllMasterFields(@Param("categoryName") String categoryName,@Param("name") String name);
}
