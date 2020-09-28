package View;

import model.Course;

import javax.swing.*;
import java.awt.*;

public class ExaminationPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel noPartsExaminationPanel;
    private JPanel partsExaminationPanel;
    private JCheckBox homeExamCheckBox;
    private JPanel homeExamPanel;
    private JCheckBox examinationOnEnglishCheckBox;

    private JPanel notDistanceAttendancePanel;
    private JPanel distanceAttendancePanel;
    private JCheckBox hasAttendanceCheckBox;
    private JPanel attendancePanel;

    private JPanel englishExaminationPanel;
    private JPanel gradingPanel;
    private JPanel partsGradingScalePanel;
    private JComboBox comboBox1;

    // Constructors
    private ExaminationPanel() {
        homeExamCheckBox.addActionListener(e -> updateHomeExamPanel());
        examinationOnEnglishCheckBox.addActionListener(e -> updateEnglishExaminationPanel());
        hasAttendanceCheckBox.addActionListener(e -> updateAttendancePanel());
    }
    private static final ExaminationPanel INSTANCE = new ExaminationPanel();
    public static ExaminationPanel getInstance() {return INSTANCE;}

    // Interface methods
    public JPanel getPanel() {
        return mainPanel;
    }
    public JButton getNextPanelButton() {
        return nextPanelButton;
    }
    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }
    public void updateView(Course course) {
        for (Component component : homeExamPanel.getComponents()) {
            component.setEnabled(homeExamCheckBox.isSelected());
        }
        for (Component component : englishExaminationPanel.getComponents()) {
            component.setEnabled(examinationOnEnglishCheckBox.isSelected());
        }

        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
        distanceAttendancePanel.setVisible(course.isDistance());
        notDistanceAttendancePanel.setVisible(!course.isDistance());

        partsExaminationPanel.setVisible(course.getCourseParts() != null);
        noPartsExaminationPanel.setVisible(course.getCourseParts() == null);
    }

    // Action listeners methods
    private void updateHomeExamPanel() {
        for (Component component : homeExamPanel.getComponents()) {
            component.setEnabled(homeExamCheckBox.isSelected());
        }
    }

    private void updateEnglishExaminationPanel() {
        for (Component component : englishExaminationPanel.getComponents()) {
            component.setEnabled(examinationOnEnglishCheckBox.isSelected());
        }
    }

    private void updateAttendancePanel() {
        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
    }


    public void printOut(Course course) {

    }
}
