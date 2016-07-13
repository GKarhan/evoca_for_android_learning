package com.example.evoca.evocaforandroidlearning.Model;

import java.util.ArrayList;

/**
 * Created by Evoca-PC on 7/2/2016.
 */
public class Child {

    private String cat_id;
    private String title;
    private String text;
    private String has_exam;
    private ArrayList<Exercise> exam_questions;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHas_exam() {
        return has_exam;
    }

    public void setHas_exam(String has_exam) {
        this.has_exam = has_exam;
    }

    public ArrayList<Exercise> getExam_questions() {
        return exam_questions;
    }

    public void setExam_questions(ArrayList<Exercise> exam_questions) {
        this.exam_questions = exam_questions;
    }
}
