package controller;

import View.LiteraturePanel;
import model.Course;

public class CourseLiteratureController implements CourseController {
    private Course course;
    private LiteraturePanel courseLiteraturePanel;

    CourseLiteratureController(Course course, LiteraturePanel courseLiteraturePanel) {
        this.course = course;
        this.courseLiteraturePanel = courseLiteraturePanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public LiteraturePanel getCourseLiteraturePanel() {
        return courseLiteraturePanel;
    }

    public void setCourseLiteraturePanel(LiteraturePanel courseLiteraturePanel) {
        this.courseLiteraturePanel = courseLiteraturePanel;
    }

    public void updateModel() {}
}
