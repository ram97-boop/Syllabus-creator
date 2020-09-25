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
    private JCheckBox isThesisCheckBox;
    private JTextField textField1;
    private JCheckBox canChangeSupervisorCheckBox;
    private JPanel languagePanel;
    private JPanel supervisedTimePanel;

    private TeachingPanel() {
        for (Component component : supervisedTimePanel.getComponents()) {
            component.setEnabled(false);
        }
        for (Component component : languagePanel.getComponents()) {
            component.setEnabled(false);
        }
        otherThanSwedishCheckBox.addActionListener(e -> upgradeLanguagePanel());
        isThesisCheckBox.addActionListener(e -> upgradeSupervisedTimePanel());
    }
    private static final TeachingPanel INSTANCE = new TeachingPanel();
    public static TeachingPanel getInstance() {return INSTANCE;}

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

    }

    private void upgradeLanguagePanel() {
        for (Component component : languagePanel.getComponents()) {
            component.setEnabled(otherThanSwedishCheckBox.isSelected());
        }
    }

    private void upgradeSupervisedTimePanel() {
        for (Component component : supervisedTimePanel.getComponents()) {
            component.setEnabled(isThesisCheckBox.isSelected());
        }
    }

    public void printOut(Course course) {

    }
}
