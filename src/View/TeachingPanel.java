package View;

import model.Course;

import javax.swing.*;
import java.awt.*;

public class TeachingPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JTextArea textArea1;
    private JCheckBox otherThanSwedishCheckBox;
    private JTextField textField1;
    private JCheckBox canChangeSupervisorCheckBox;
    private JPanel languagePanel;
    private JPanel thesisPanel;
    private JPanel notDistancePanel;
    private JPanel distancePanel;
    private JRadioButton radio1;
    private JRadioButton radio2;

    // Constructors

    private TeachingPanel() {
        languagePanel.setVisible(false);
        otherThanSwedishCheckBox.addActionListener(e -> updateLanguagePanel());
        radio1.addActionListener(e -> updateRadios(radio2));
        radio2.addActionListener(e -> updateRadios(radio1));
    }
    private static final TeachingPanel INSTANCE = new TeachingPanel();
    public static TeachingPanel getInstance() {return INSTANCE;}

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
        distancePanel.setVisible(course.isDistance());
        notDistancePanel.setVisible(!course.isDistance());
        thesisPanel.setVisible(course.hasThesis());
    }

    // Action listeners methods
    private void updateLanguagePanel() {
        languagePanel.setVisible(otherThanSwedishCheckBox.isSelected());
        radio1.setSelected(true);
    }

    private void updateRadios(JRadioButton radio) {
        radio.setSelected(false);
    }

    // PrintOut method
    public void printOut(Course course) {

    }
}
