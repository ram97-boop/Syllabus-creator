package controller;

import View.TeachingPanel;
import model.Course;

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

    public void setCourse(Course course) {
        this.course = course;
    }

    public TeachingPanel getPanel() {
        return teachingPanel;
    }


    public void updateModel() {}

}
