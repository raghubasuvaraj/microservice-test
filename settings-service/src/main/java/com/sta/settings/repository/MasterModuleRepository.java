package com.sta.settings.repository;

import com.sta.settings.entities.MasterModules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterModuleRepository extends JpaRepository<MasterModules, String> , JpaSpecificationExecutor<MasterModules> {

}
