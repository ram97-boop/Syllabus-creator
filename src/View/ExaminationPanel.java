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
    private JTextArea textArea1;
    private JTextField textField1;
    private JLabel examinationLabel1;
    private JLabel examinationLabel2;
    private JLabel examinationLabel3;
    private JLabel examinationLabel4;
    private JLabel examinationLabel5;
    private JLabel examinationLabel6;
    private JLabel examinationLabel7;
    private JLabel examinationLabel8;
    private JPanel examinationPanel1;
    private JPanel examinationPanel2;
    private JPanel examinationPanel3;
    private JPanel examinationPanel4;
    private JPanel examinationPanel5;
    private JPanel examinationPanel6;
    private JPanel examinationPanel7;
    private JPanel examinationPanel8;
    private JRadioButton homeExamRadio1;
    private JRadioButton homeExamRadio2;
    private JRadioButton englishRadio1;
    private JRadioButton englishRadio2;
    private JRadioButton totalGradeRadio1;
    private JRadioButton totalGradeRadio2;
    private JRadioButton totalGradeRadio3;
    private JCheckBox otherActivitiesCheckBox;
    private JTextArea textArea2;
    private JPanel otherAcitivitiesGradePanel;

    private final JLabel[] examinationLabels = {
        examinationLabel1,
        examinationLabel2,
        examinationLabel3,
        examinationLabel4,
        examinationLabel5,
        examinationLabel6,
        examinationLabel7,
        examinationLabel8
    };

    private final JPanel[] examinationPanels = {
            examinationPanel1,
            examinationPanel2,
            examinationPanel3,
            examinationPanel4,
            examinationPanel5,
            examinationPanel6,
            examinationPanel7,
            examinationPanel8
    };


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
        homeExamRadio1.addActionListener(e -> updateHomeExamRadios(homeExamRadio2));
        homeExamRadio2.addActionListener(e -> updateHomeExamRadios(homeExamRadio1));
        englishRadio1.addActionListener(e -> updateEnglishRadios(englishRadio2));
        englishRadio2.addActionListener(e -> updateEnglishRadios(englishRadio1));
        totalGradeRadio1.addActionListener(e -> updateTotalGradeRadios(totalGradeRadio2, totalGradeRadio3));
        totalGradeRadio2.addActionListener(e -> updateTotalGradeRadios(totalGradeRadio3, totalGradeRadio1));
        totalGradeRadio3.addActionListener(e -> updateTotalGradeRadios(totalGradeRadio1, totalGradeRadio2));
        otherActivitiesCheckBox.addActionListener(e -> updateOtherActivitiesGradePanel());

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
    public void updateView(MainFrame frame, Course course) {
        homeExamPanel.setVisible(false);
        englishExaminationPanel.setVisible(false);

        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
        distanceAttendancePanel.setVisible(course.isDistance());
        notDistanceAttendancePanel.setVisible(!course.isDistance());



        ArrayList<CoursePart> courseParts = course.getCourseParts();
        int nParts = courseParts.size();
        boolean hasParts = nParts > 0;

        partsExaminationPanel.setVisible(hasParts);
        noPartsExaminationPanel.setVisible(!hasParts);
        partsGradingScalePanel.setVisible(hasParts);
        if(hasParts) {
            int i = 0;
            for (JPanel examinationPanel : examinationPanels) {
                examinationPanel.setVisible(i < nParts);
                if (i < nParts) {
                    JLabel label = (JLabel) examinationPanel.getComponent(1);
                    label.setText(courseParts.get(i).getName());
                }
                i++;
            }
            i = 0;
            for (JPanel partPanel : partPanels) {
                partPanel.setVisible(i < nParts);
                if (i < nParts) {
                    JLabel label = (JLabel) partPanel.getComponent(0);
                    label.setText(courseParts.get(i).getName());
                }
                i++;
            }
        }
        totalGradeRadio1.setSelected(true);
        otherAcitivitiesGradePanel.setVisible(false);
    }

    // Action listeners methods
    private void updateHomeExamPanel() {
        homeExamPanel.setVisible(homeExamCheckBox.isSelected());
        homeExamRadio1.setSelected(true);
    }

    private void updateHomeExamRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    private void updateEnglishExaminationPanel() {
        englishExaminationPanel.setVisible(examinationOnEnglishCheckBox.isSelected());
        englishRadio1.setSelected(true);
    }

    private void updateEnglishRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    private void updateAttendancePanel() {
        attendancePanel.setVisible(hasAttendanceCheckBox.isSelected());
    }

    private void updateTotalGradeRadios(JRadioButton radio1, JRadioButton radio2) {
        radio1.setSelected(false);
        radio2.setSelected(false);
    }

    private void updateOtherActivitiesGradePanel() {
        otherAcitivitiesGradePanel.setVisible(otherActivitiesCheckBox.isSelected());
    }

    // PrintOut method

    public void printOut(Course course) {

    }

}
