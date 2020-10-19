package controller;

import View.TeachingPanel;
import model.Course;

import javax.swing.*;

/**
 * TeachingController
 * Implements CourseController
 *
 * Update course with content entered by user in all fields in the mainPanel in TeachingPanel.
 *
 * @author Sofia Ayata Karbin
 */

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

        setCourseLanguage();

        if (!course.isDistance()) {
            course.setTeaching(teachingPanel.getTeachingPane().getText());
        }

        if (course.hasThesis()) {
            course.setThesisSupervisedHours(teachingPanel.getThesisSupervisedHoursField().getText());
            course.setCanChangeSupervisor(teachingPanel.getCanChangeSupervisorCheckBox().isSelected());
        }

    }

    private void setCourseLanguage() {
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
