package com.example.hr.department.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class TeamsEntity extends BaseEntity {


    private String name;
    private String description;
    //TODO change to list of Developer
    //private List<String> developers;
}
