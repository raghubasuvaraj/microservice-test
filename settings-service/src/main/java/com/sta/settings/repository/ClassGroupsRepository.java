package com.sta.settings.repository;

import com.sta.settings.entities.ClassGroupsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassGroupsRepository extends JpaRepository<ClassGroupsEntity, String> {

}
