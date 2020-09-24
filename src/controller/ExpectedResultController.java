package controller;

import View.ExpectedResultPanel;
import model.Course;

public class ExpectedResultController implements CourseController {
    private Course course;
    private ExpectedResultPanel expectedResultPanel;

    public ExpectedResultController(Course course, ExpectedResultPanel expectedResultPanel) {
        this.course = course;
        this.expectedResultPanel = expectedResultPanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ExpectedResultPanel getPanel() {
        return expectedResultPanel;
    }


    public void updateModel() {}
}
