package com.example.hr.department.service.repository.strategy;

import com.example.hr.department.service.domain.BaseEntity;
import com.example.hr.department.service.repository.RepositoryFacade;
import com.example.hr.department.service.repository.jpa.PostgresJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostgresStrategy<T extends BaseEntity> implements RepositoryFacade<T> {

    private PostgresJPA<T> postgresJPA;

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public T updateById(T entity) {
        return null;
    }

    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
