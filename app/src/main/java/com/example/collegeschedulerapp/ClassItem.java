package com.example.collegeschedulerapp;

import java.time.LocalTime;

public class ClassItem {
    String name;
    String instructor;
    String location;
    LocalTime time;
    String desc;
    public ClassItem(String name, String instructor, String location, LocalTime time, String desc) {
        this.name = name;
        this.instructor = instructor;
        this.location = location;
        this.time = time;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }
}
