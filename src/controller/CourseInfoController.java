package controller;

import View.CourseInfoPanel;
import model.Course;

/**
 * CourseInfoController
 * Implements CourseController
 *
 * Update course with content entered by user in all fields in the mainPanel in CourseInfoPanel.
 *
 * @author Sofia Ayata Karbin
 */

public class CourseInfoController implements CourseController {
    private Course course;
    private CourseInfoPanel courseInfoPanel;

    public CourseInfoController(Course course, CourseInfoPanel courseInfoPanel) {
        this.course = course;
        this.courseInfoPanel = courseInfoPanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseInfoPanel getPanel() {
        return courseInfoPanel;
    }

    public void updateModel() {
        try {
            course.setCredits(Float.parseFloat(courseInfoPanel.getCoursePoints().getText()));
            course.setName(courseInfoPanel.getCourseName().getText());
            course.setCode(courseInfoPanel.getCourseCode().getText());
            course.setDistance(courseInfoPanel.getIsDistanceCheckBox().isSelected());
            course.setThesis(courseInfoPanel.getThesisCheckBox().isSelected());
        } catch(RuntimeException e) {
            throw new RuntimeException("Fel i inmatning! Vänligen kontrollera att inmatning är korrekt.");
        }

    }

}
