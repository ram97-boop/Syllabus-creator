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

    private FirstPanel() {
        previousPanelButton.setEnabled(false);
    }
    private static final FirstPanel INSTANCE = new FirstPanel();
    public static FirstPanel getInstance() {return INSTANCE;}

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

    public String getCourseName() {
        return courseName.getText();
    }

    public String getCourseCode() {
        return courseCode.getText();
    }

    public float getCoursePoints() {
        return Float.parseFloat(coursePoints.getText());
    }

    public void printOut(Course course) {

    }
}
