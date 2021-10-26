package com.example.hr.department.service.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEVELOPERS")
public class DevelopersEntity extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "TEAM_ID")
    private TeamsEntity team;
}
