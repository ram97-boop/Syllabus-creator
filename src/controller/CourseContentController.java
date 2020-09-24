package controller;

import View.CourseContentPanel;
import model.Course;

public class CourseContentController implements CourseController {
    private Course course;
    private CourseContentPanel courseContentPanel;

    public CourseContentController(Course course, CourseContentPanel courseContentPanel) {
        this.course = course;
        this.courseContentPanel = courseContentPanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseContentPanel getPanel() {
        return courseContentPanel;
    }

    public void updateModel() {}
}
