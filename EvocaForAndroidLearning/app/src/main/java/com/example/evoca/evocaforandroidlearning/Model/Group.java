package com.example.evoca.evocaforandroidlearning.Model;

/**
 * Created by Evoca-PC on 7/2/2016.
 */
import com.example.evoca.evocaforandroidlearning.Model.Child;

import java.util.ArrayList;

public class Group {

    public String main_title;
    //public String title;

    // childrens-i tex@ courses greci vor list@ vercni
    //public ArrayList<Child> childrens;

    public ArrayList<Child> courses;

    /*public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/

    /*public Group(ArrayList<Child> childrens) {
        this.childrens = childrens;
    }*/

    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    /*public ArrayList<Child> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<Child> childrens) {
        this.childrens = childrens;
    }*/

    public ArrayList<Child> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Child> courses) {
        this.courses = courses;
    }
}