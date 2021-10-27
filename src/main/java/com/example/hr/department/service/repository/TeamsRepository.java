package com.example.hr.department.service.repository;

import com.example.hr.department.service.domain.TeamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<TeamsEntity, String> {

    TeamsEntity findByName(String name);
}
