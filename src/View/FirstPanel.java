package View;

import model.Course;

import javax.swing.*;

public class FirstPanel implements CoursePanel {
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JTextField courseName;
    private JTextField courseCode;
    private JTextField coursePoints;
    private JCheckBox isDistanceCheckBox;

    private boolean isDistance = false;

    private FirstPanel() {
        previousPanelButton.setEnabled(false);
        isDistanceCheckBox.addActionListener(e -> updateIsDistance());
    }
    private static final FirstPanel INSTANCE = new FirstPanel();
    public static FirstPanel getInstance() {return INSTANCE;}

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

    public void updateView() {     }

    // Actions listener methods

    private void updateIsDistance() {
        isDistance = isDistanceCheckBox.isSelected();
    }

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

    public boolean getIsDistance() {
        return isDistance;
    }

    // Print Out

    public void printOut(Course course) {

    }

}
