package View;

import model.Course;
import model.GradingScale;

import javax.swing.*;
import java.util.HashMap;
import java.util.Properties;


public class FirstPanel implements CoursePanel {
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JTextField courseName;
    private JTextField courseCode;
    private JTextField coursePoints;
    private JCheckBox isDistanceCheckBox;
    private JCheckBox thesisCheckBox;
    private JSplitPane splitPane;

    MainFrame frame;
    Properties properties;

    // Constructors
    public FirstPanel(MainFrame frame) {
        setVisibilityOfComponents();
        this.frame = frame;
        properties = frame.getProperties();
    }

    public FirstPanel(MainFrame frame, Course course) {
        setVisibilityOfComponents();
        setToolTips();
        properties = frame.getProperties();

        // set text fields
        courseName.setText(course.getName());
        courseCode.setText(course.getCode());
        coursePoints.setText(String.valueOf(course.getCredits()));
        isDistanceCheckBox.setSelected(course.isDistance());
        thesisCheckBox.setSelected(course.hasThesis());
    }

    public FirstPanel() {

    }


    private void setVisibilityOfComponents() {
        previousPanelButton.setEnabled(false);
    }

    private void setToolTips() {
        coursePoints.setToolTipText("Använd punkt som kommatecken.");
    }

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

    public String getFrameName() {
        return properties.getProperty("FirstPanelTitle");
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public void updateView(Course course) {
        splitPane.setDividerLocation(0.5);
        System.out.println(splitPane.getDividerLocation());
    }

    // Action listeners methods


    // Getters to Controller

    public JTextField getCourseName() {
        return courseName;
    }

    public JTextField getCourseCode() {
        return courseCode;
    }

    public JTextField getCoursePoints() {
        return coursePoints;
    }

    public JCheckBox getIsDistanceCheckBox() {
        return isDistanceCheckBox;
    }

    public JCheckBox getThesisCheckBox() {
        return thesisCheckBox;
    }

}
