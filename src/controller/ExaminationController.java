package controller;

import View.ExaminationPanel;
import model.Course;
import model.CoursePart;
import model.GradingScale;

import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

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

        setIfHomeExam();
        setGradingScaleForCourse();
        setGradingScaleAndExaminationFieldForCourseParts();

    }

    private void setIfHomeExam() {
        JCheckBox homeExamCheckBox = examinationPanel.getHomeExamCheckBox();
        JRadioButton homeExamRadio1 = examinationPanel.getHomeExamRadio1();
        course.setHomeExam(homeExamCheckBox.isSelected());
        
        if (homeExamCheckBox.isSelected()) {
            course.setLateHomeExamNotExamined(homeExamRadio1.isSelected());
        }

    }

    private void setGradingScaleForCourse() {
        GradingScale gradingScale = new GradingScale();

        String selectedItem = (String) examinationPanel.getCourseGradingScaleComboBox().getSelectedItem();
        ArrayList<String> gradingScaleList = gradingScale.userGetGradingScale(selectedItem);

        course.setGradingScale(gradingScaleList);
    }


    private void setGradingScaleAndExaminationFieldForCourseParts() {
        ArrayList<JComboBox<String>> gradingScales = examinationPanel.getGradingScales();
        JTextField[] examinationFields = examinationPanel.getExaminationFields();

        GradingScale gradingScale = new GradingScale();

        IntStream.range(0, course.getCourseParts().size()).forEach(i -> {
            CoursePart coursePart = course.getCourseParts().get(i);

            JTextField examinationField = examinationFields[i];
            coursePart.setExamination(examinationField.getText());

            String selectedGradingScale = (String) gradingScales.get(i).getSelectedItem();
            ArrayList<String> scale = gradingScale.userGetGradingScale(selectedGradingScale);
            coursePart.setGradingScale(scale);

        });
    }

}
