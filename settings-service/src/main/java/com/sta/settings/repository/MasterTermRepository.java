package com.sta.settings.repository;

import com.sta.settings.entities.MasterTermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTermRepository extends JpaRepository<MasterTermEntity, String>, JpaSpecificationExecutor {

}
