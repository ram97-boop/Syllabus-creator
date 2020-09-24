package controller;

import View.ExaminationPanel;
import model.Course;

public class ExaminationController implements CourseController {
    private Course course;
    private ExaminationPanel examinationPanel;

    public ExaminationController(Course course, ExaminationPanel examinationPanel) {
        this.course = course;
        this.examinationPanel = examinationPanel;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ExaminationPanel getExaminationPanel() {
        return examinationPanel;
    }

    public void setExaminationPanel(ExaminationPanel examinationPanel) {
        this.examinationPanel = examinationPanel;
    }

    public void updateModel() {}
    
}
