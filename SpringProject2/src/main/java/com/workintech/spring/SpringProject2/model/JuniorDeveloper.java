package com.workintech.spring.SpringProject2.model;

public class JuniorDeveloper extends Developer{
    public JuniorDeveloper(double id, String name, double salary) {
        super(id, name, salary, Experience.JUNIOR);
    }

}
