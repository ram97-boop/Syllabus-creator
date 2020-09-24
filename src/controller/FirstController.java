package controller;

import View.FirstPanel;
import model.Course;

public class FirstController implements CourseController {
    private Course course;
    private FirstPanel firstPanel;

    public FirstController(Course course, FirstPanel firstPanel) {
        this.course = course;
        this.firstPanel = firstPanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public FirstPanel getPanel() {
        return firstPanel;
    }


    public void updateModel() {}

}
