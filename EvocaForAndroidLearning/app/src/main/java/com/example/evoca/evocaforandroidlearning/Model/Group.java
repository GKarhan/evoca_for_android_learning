package com.example.evoca.evocaforandroidlearning.Model;

/**
 * Created by Evoca-PC on 7/2/2016.
 */

import java.util.ArrayList;

public class Group {

    private String main_title;
    private ArrayList<Child> courses;

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public ArrayList<Child> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Child> courses) {
        this.courses = courses;
    }
}