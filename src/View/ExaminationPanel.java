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
    private JCheckBox hasAttendanceDistanceCheckBox;

    private JPanel englishExaminationPanel;
    private JPanel distanceHasAttendancePanel;

    // Constructors
    private ExaminationPanel() {
        for (Component component : homeExamPanel.getComponents()) {
            component.setEnabled(false);
        }
        for (Component component : englishExaminationPanel.getComponents()) {
            component.setEnabled(false);
        }
        for (Component component : attendancePanel.getComponents()) {
            component.setEnabled(false);
        }
        for (Component component : distanceAttendancePanel.getComponents()) {
            component.setEnabled(false);
        }
        homeExamCheckBox.addActionListener(e -> updateHomeExamPanel());
        examinationOnEnglishCheckBox.addActionListener(e -> updateEnglishExaminationPanel());
        hasAttendanceCheckBox.addActionListener(e -> updateAttendancePanel());
        hasAttendanceDistanceCheckBox.addActionListener(e -> updateDistanceHasAttendancePanel());
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

        distanceAttendancePanel.setVisible(false);
        notDistanceAttendancePanel.setVisible(true);

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
        for (Component component : attendancePanel.getComponents()) {
            component.setEnabled(hasAttendanceCheckBox.isSelected());
        }
    }

    private void updateDistanceHasAttendancePanel() {
        for (Component component : distanceAttendancePanel.getComponents()) {
            component.setEnabled(hasAttendanceCheckBox.isSelected());
        }
    }

    public void printOut(Course course) {

    }
}
