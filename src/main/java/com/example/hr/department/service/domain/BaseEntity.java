package com.example.hr.department.service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class BaseEntity {

    @Id
    private String id;
}
