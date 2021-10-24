package com.example.hr.department.service.repository;


import com.example.hr.department.service.domain.BaseEntity;

import java.util.List;

public interface RepositoryFacade<T extends BaseEntity> {

    T save(T entity);

    T updateById(T entity);

    T findById(String id);

    List<T> findAll();

    void deleteById(String id);
}
