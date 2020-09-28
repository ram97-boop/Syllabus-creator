package View;

import model.Course;
import model.CoursePart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
    private JLabel partLabel1;
    private JLabel partLabel2;
    private JLabel partLabel3;
    private JLabel partLabel4;
    private JLabel partLabel5;
    private JLabel partLabel6;
    private JLabel partLabel7;
    private JLabel partLabel8;
    private JPanel partPanel1;
    private JPanel partPanel2;
    private JPanel partPanel3;
    private JPanel partPanel4;
    private JPanel partPanel5;
    private JPanel partPanel6;
    private JPanel partPanel7;
    private JPanel partPanel8;
    private JComboBox<String> gradingScale1;
    private JComboBox<String> gradingScale2;
    private JComboBox<String> gradingScale3;
    private JComboBox<String> gradingScale4;
    private JComboBox<String> gradingScale5;
    private JComboBox<String> gradingScale6;
    private JComboBox<String> gradingScale7;
    private JComboBox<String> gradingScale8;


    private final JPanel[] partPanels = {
            partPanel1,
            partPanel2,
            partPanel3,
            partPanel4,
            partPanel5,
            partPanel6,
            partPanel7,
            partPanel8
    };

    private final String[] gradingScaleStrings = {
            "7-gradig (A-F)",
            "3-gradig (VG-U)",
            "2-gradig (G-U)"
    };

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

        ArrayList<CoursePart> courseParts = course.getCourseParts();
        int nParts = courseParts.size();
        int i = 0;

        for (JPanel partPanel : partPanels) {
            partPanel.setVisible(i < nParts);

            if (i < nParts) {
                JLabel label = (JLabel) partPanel.getComponent(0);
                String courseName = courseParts.get(i).getName();
                label.setText(courseName);
            }
            i++;
        }

        for (String gradingScaleString : gradingScaleStrings) {
            gradingScale1.addItem(gradingScaleString);
            gradingScale2.addItem(gradingScaleString);
            gradingScale3.addItem(gradingScaleString);
            gradingScale4.addItem(gradingScaleString);
            gradingScale5.addItem(gradingScaleString);
            gradingScale6.addItem(gradingScaleString);
            gradingScale7.addItem(gradingScaleString);
            gradingScale8.addItem(gradingScaleString);


        }

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
