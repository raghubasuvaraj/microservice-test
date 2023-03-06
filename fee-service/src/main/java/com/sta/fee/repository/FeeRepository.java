package com.sta.fee.repository;

import com.sta.fee.entities.FeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<FeeEntity,Long> {
}
