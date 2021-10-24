package com.example.hr.department.service.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresJPA<T> extends JpaRepository<T, String> {
}
