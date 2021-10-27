package com.example.hr.department.service.repository;

import com.example.hr.department.service.domain.DevelopersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevelopersRepository extends JpaRepository<DevelopersEntity, String> {
}
