package controller;

import View.FirstPanel;
import model.Course;
import model.GradingScale;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    public void updateModel() {
        try {
            course.setCredits(Float.parseFloat(firstPanel.getCoursePoints().getText()));
            course.setName(firstPanel.getCourseName().getText());
            course.setCode(firstPanel.getCourseCode().getText());
            course.setDistance(firstPanel.getIsDistanceCheckBox().isSelected());
            course.setThesis(firstPanel.getThesisCheckBox().isSelected());

            HashMap<String, Integer> gradingScaleMap = firstPanel.getGradingScaleMap();
            JComboBox<String> gradingScaleComboBox = firstPanel.getGradingScaleComboBox();

            GradingScale gradingScale = new GradingScale();
            String selectedItem = (String)gradingScaleComboBox.getSelectedItem();
            ArrayList<String> gradingScaleList = gradingScale.getGradingScale(gradingScaleMap.get(selectedItem));

            course.setGradingScale(gradingScaleList);
        } catch(RuntimeException e) {
            throw new RuntimeException("Fel i inmatning! Vänligen kontrollera att inmatning är korrekt.");
        }

    }

}
