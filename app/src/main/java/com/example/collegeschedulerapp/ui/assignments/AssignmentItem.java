package com.example.collegeschedulerapp.ui.assignments;

import java.time.LocalDate;

public class AssignmentItem {
    private String title;
    private LocalDate date;
    private String className;
    public AssignmentItem(String title, LocalDate date, String className) {
        this.title = title;
        this.date = date;
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
