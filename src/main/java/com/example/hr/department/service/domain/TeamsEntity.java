package com.example.hr.department.service.domain;

import java.util.List;

public class TeamsEntity extends BaseEntity{

    private String name;
    private String description;
    //TODO change to list of Developer
    private List<String> developers;
}
