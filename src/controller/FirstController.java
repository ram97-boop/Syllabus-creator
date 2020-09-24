package controller;

import View.FirstPanel;
import model.Course;

public class FirstController implements CourseController {
    private Course course;
    private FirstPanel firstPanel;

    FirstController(Course course, FirstPanel firstPanel) {
        this.course = course;
        this.firstPanel = firstPanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public FirstPanel getFirstPanel() {
        return firstPanel;
    }

    public void setFirstPanel(FirstPanel firstPanel) {
        this.firstPanel = firstPanel;
    }

    public void updateModel() {}

}