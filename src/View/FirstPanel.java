package View;

import model.Course;

import javax.swing.*;
import java.util.HashMap;

public class FirstPanel implements CoursePanel {
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JTextField courseName;
    private JTextField courseCode;
    private JTextField coursePoints;
    private JCheckBox isDistanceCheckBox;
    private JComboBox<String> gradingScaleComboBox;
    private final String[] gradingScaleStrings = {
            "7-gradig (A-F)",
            "3-gradig (VG-U)",
            "2-gradig (G-U)"
    };
    private final HashMap<String, Integer> gradingScaleMap = new HashMap<>();

    private boolean isDistance = false;
    private int gradingScale;

    // Constructors
    private FirstPanel() {
        previousPanelButton.setEnabled(false);
        gradingScaleComboBox.setEditable(false);

        gradingScaleMap.put(gradingScaleStrings[0], 7);
        gradingScaleMap.put(gradingScaleStrings[1], 3);
        gradingScaleMap.put(gradingScaleStrings[2], 2);

        gradingScale = gradingScaleMap.get(gradingScaleStrings[0]);

        isDistanceCheckBox.addActionListener(e -> updateIsDistance());
        gradingScaleComboBox.addActionListener(e -> updateGradingScale());

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

    public void updateView(Course course) {
        for (String gradingScaleString : gradingScaleStrings) {
            gradingScaleComboBox.addItem(gradingScaleString);
        }
    }

    // Action listeners methods

    private void updateIsDistance() {
        isDistance = isDistanceCheckBox.isSelected();
    }

    private void updateGradingScale() {
        gradingScale = gradingScaleMap.get(gradingScaleComboBox.getSelectedItem());
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

    public int getGradingScale() {
        return gradingScale;
    }

    // Print Out

    public void printOut(Course course) {

    }

}
