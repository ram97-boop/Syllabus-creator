package controller;

import View.TeachingPanel;
import model.Course;

public class TeachingController {
    private Course course;
    private TeachingPanel teachingPanel;

    TeachingController(Course course, TeachingPanel teachingPanel) {
        this.course = course;
        this.teachingPanel = teachingPanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TeachingPanel getTeachingPanel() {
        return teachingPanel;
    }

    public void setTeachingPanel(TeachingPanel teachingPanel) {
        this.teachingPanel = teachingPanel;
    }

    public void updateModel() {}

}
