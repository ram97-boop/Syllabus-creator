package controller;

import View.TeachingPanel;
import model.Course;

import javax.swing.*;

public class TeachingController implements CourseController {
    private Course course;
    private TeachingPanel teachingPanel;

    public TeachingController(Course course, TeachingPanel teachingPanel) {
        this.course = course;
        this.teachingPanel = teachingPanel;
    }

    public Course getCourse() {
        return course;
    }

    public TeachingPanel getPanel() {
        return teachingPanel;
    }


    public void updateModel() {
        JCheckBox otherThanSwedishCheckBox = teachingPanel.getOtherThanSwedishCheckBox();
        JRadioButton radioButtonCourseInEnglish = teachingPanel.getRadioButtonCourseInEnglish();

        if (otherThanSwedishCheckBox.isSelected() && radioButtonCourseInEnglish.isSelected()) {
            course.setLanguage(Language.ENGLISH.getLanguage());
        } else if (otherThanSwedishCheckBox.isSelected() && !radioButtonCourseInEnglish.isSelected()) {
            course.setLanguage(Language.NOT_SPECIFIED.getLanguage());
        } else {
            course.setLanguage(Language.SWEDISH.getLanguage());
        }

    }

}