package com.workintech.spring.SpringProject2.model;

public class SeniorDeveloper extends Developer{
    public SeniorDeveloper(double id, String name, double salary) {
        super(id, name, salary, Experience.SENIOR);
    }
}
