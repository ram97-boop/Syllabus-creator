package controller;

import View.ExaminationPanel;
import model.Course;
import model.CoursePart;
import model.GradingScale;

import javax.swing.*;
import java.util.ArrayList;

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

    public ExaminationPanel getPanel() {
        return examinationPanel;
    }


    public void updateModel() {
        ArrayList<JComboBox<String>> gradingScales = examinationPanel.getGradingScales();
        JTextField[] examinationFields = examinationPanel.getExaminationFields();

        GradingScale gradingScale = new GradingScale();

        for (int i=0; i<course.getCourseParts().size(); i++) {
            JTextField examinationField = examinationFields[i];
            String selectedItem = (String) gradingScales.get(i).getSelectedItem();
            ArrayList<String> scale = gradingScale.userGetGradingScale(selectedItem);
            CoursePart coursePart = course.getCourseParts().get(i);
            coursePart.setGradingScale(scale);
            coursePart.setExamination(examinationField.getText());
        }
    }
    
}
