package com.workintech.spring.SpringProject2.model;

public class MidDeveloper extends Developer{
    public MidDeveloper(double id, String name, double salary) {
        super(id, name, salary, Experience.MID);
    }
}
