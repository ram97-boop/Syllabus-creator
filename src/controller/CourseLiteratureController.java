package controller;

import View.LiteraturePanel;
import model.Course;

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


    public void updateModel() {}
}
