package View;

import model.Course;
import model.GradingScale;

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
    private JCheckBox thesisCheckBox;
    private final String[] gradingScaleStrings = GradingScale.getGradingScaleStrings();
    private final HashMap<String, Integer> gradingScaleMap = new HashMap<>();

    private boolean isDistance = false;
    private int gradingScale;
    private boolean thesis;

    // Constructors
    private FirstPanel() {
        previousPanelButton.setEnabled(false);
        gradingScaleComboBox.setEditable(false);

        gradingScaleMap.put(gradingScaleStrings[0], 7);
        gradingScaleMap.put(gradingScaleStrings[1], 3);
        gradingScaleMap.put(gradingScaleStrings[2], 2);

        gradingScale = gradingScaleMap.get(gradingScaleStrings[0]);

        gradingScaleComboBox.addActionListener(e -> updateGradingScale());
        thesisCheckBox.addActionListener(e -> updateThesis());
        isDistanceCheckBox.addActionListener(e -> updateIsDistance());

        for (String gradingScaleString : gradingScaleStrings) {
            gradingScaleComboBox.addItem(gradingScaleString);
        }
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

    public void updateView(MainFrame frame, Course course) {

    }

    // Action listeners methods


    private void updateGradingScale() {
        gradingScale = gradingScaleMap.get(gradingScaleComboBox.getSelectedItem());
    }

    private void updateThesis() {
        thesis = thesisCheckBox.isSelected();
    }

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

    public int getGradingScale() {
        return gradingScale;
    }

    public boolean getThesis() {
        return thesis;
    }

    public boolean getIsDistance() {
        return isDistance;
    }

    // Print Out

    public void printOut(Course course) {

    }

}
