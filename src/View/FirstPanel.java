package View;

import model.Course;
import model.GradingScale;

import javax.swing.*;
import java.util.HashMap;
import java.util.Properties;

// TODO skicka med värden eller hela components? Koden är inkonsekvent

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

    Properties properties;

    // Constructors
    private FirstPanel() {
        setVisibilityOfComponents();
        setUpComboBox();
        addActionListeners();
    }

    private static final FirstPanel INSTANCE = new FirstPanel();
    public static FirstPanel getInstance() {return INSTANCE;}

    private void setVisibilityOfComponents() {
        previousPanelButton.setEnabled(false);
        gradingScaleComboBox.setEditable(false);
    }

    private void setUpComboBox() {
        gradingScaleMap.put(gradingScaleStrings[0], 7);
        gradingScaleMap.put(gradingScaleStrings[1], 3);
        gradingScaleMap.put(gradingScaleStrings[2], 2);

        gradingScale = gradingScaleMap.get(gradingScaleStrings[0]);

        for (String gradingScaleString : gradingScaleStrings) {
            gradingScaleComboBox.addItem(gradingScaleString);
        }
    }

    private void addActionListeners() {
        gradingScaleComboBox.addActionListener(e -> updateGradingScale());
        thesisCheckBox.addActionListener(e -> updateThesis());
        isDistanceCheckBox.addActionListener(e -> updateIsDistance());
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

    public void updateView(MainFrame frame, Course course) {
        properties = frame.getProperties();
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


}
