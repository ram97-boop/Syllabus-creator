package controller;

import View.LiteraturePanel;
import model.Course;

import javax.swing.*;

public class CourseLiteratureController implements CourseController {
    private Course course;
    private LiteraturePanel courseLiteraturePanel;

    public CourseLiteratureController(Course course, LiteraturePanel courseLiteraturePanel) {
        this.course = course;
        this.courseLiteraturePanel = courseLiteraturePanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public LiteraturePanel getPanel() {
        return courseLiteraturePanel;
    }


    public void updateModel() {
        JTextField institutionField = courseLiteraturePanel.getInstitutionField();
        course.setInstitution(institutionField.getText());

        JCheckBox multipleInstitutionsCheckBox = courseLiteraturePanel.getMultipleInstitutionsCheckBox();
        course.setMultipleInstitutions(multipleInstitutionsCheckBox.isSelected());

        JRadioButton radio1 = courseLiteraturePanel.getRadio1();
        course.setPrintMultipleInstitutionsAlt1((radio1.isVisible() && radio1.isSelected()));
    }
}
