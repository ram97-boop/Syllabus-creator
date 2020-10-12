package controller;

import View.ExaminationPanel;
import model.Course;
import model.CoursePart;
import model.GradingScale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        setIfExaminationInEnglish();
        setGradingScaleForCourse();
        setAttendanceRequired();
        setTotalGradeImpact();
        setOtherActivitiesAffectGrade();

        if (course.getCourseParts().isEmpty()) {
            JTextPane examination = examinationPanel.getExaminationPane();
            course.setExamination(examination.getText());
        } else {
            setGradingScaleAndExaminationFieldForCourseParts();
        }

    }

    private void setOtherActivitiesAffectGrade() {
        JCheckBox otherActivitiesCheckBox = examinationPanel.getOtherActivitiesCheckBox();
        course.setOtherActivitiesAffectGrade(otherActivitiesCheckBox.isSelected());

        if (otherActivitiesCheckBox.isSelected()) {
            JTextPane otherActivitiesPane = examinationPanel.getOtherActivitiesPane();
            course.setOtherActivitiesThatAffectGrade(otherActivitiesPane.getText());
        }
    }

    private void setTotalGradeImpact() {
        JRadioButton totalGradeRadio1 = examinationPanel.getTotalGradeRadio1();
        JRadioButton totalGradeRadio2 = examinationPanel.getTotalGradeRadio2();

        course.setTotalGradeFromAllParts(totalGradeRadio1.isSelected());
        course.setTotalGradeFromSomeParts((totalGradeRadio2.isSelected() && totalGradeRadio2.isVisible()));

        if (course.isTotalGradeFromSomeParts()) {
            ArrayList<CoursePart> courseParts = course.getCourseParts();
            JRadioButton[] gradeCertainPartsRadios = examinationPanel.getGradeCertainPartsRadios();

            Arrays.stream(gradeCertainPartsRadios).filter(Component::isVisible).forEach(jRadioButton -> {
                String text = jRadioButton.getText();
                List<CoursePart> collect = courseParts.stream().filter(coursePart -> coursePart.getName().toLowerCase().equals(text.toLowerCase())).collect(Collectors.toList());
                if (collect.size() == 1) {
                    collect.get(0).setDecidesTotalGrade(jRadioButton.isSelected());
                }
            });
        } else if (!course.isTotalGradeFromAllParts() && !course.isTotalGradeFromSomeParts()) {
            JTextPane totalGradeAlt3TextPane = examinationPanel.getTotalGradeAlt3TextPane();
            course.setTotalGradeAlt3Text(totalGradeAlt3TextPane.getText());
        }
    }

    private void setAttendanceRequired() {
        JCheckBox hasAttendanceCheckBox = examinationPanel.getHasAttendanceCheckBox();

        course.setAttendanceRequired(hasAttendanceCheckBox.isSelected());

        if (hasAttendanceCheckBox.isSelected() && course.isDistance()) {
            JTextPane distanceAttendancePane = examinationPanel.getDistanceAttendancePane();
            course.setDistanceAttendanceText(distanceAttendancePane.getText());
        } else if (hasAttendanceCheckBox.isSelected() && !course.isDistance()) {
            JTextPane notDistanceAttendancePane = examinationPanel.getNotDistanceAttendancePane();
            course.setNotDistanceAttendanceText(notDistanceAttendancePane.getText());
        }
    }

    private void setIfExaminationInEnglish() {
        JCheckBox examinationOnEnglishCheckBox = examinationPanel.getExaminationOnEnglishCheckBox();
        JRadioButton englishRadio1 = examinationPanel.getEnglishRadio1();

        course.setExaminationPartiallyInEnglish(examinationOnEnglishCheckBox.isSelected());

        if (examinationOnEnglishCheckBox.isSelected()) {
            course.setExaminationInEnglish(englishRadio1.isSelected());
        }
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
