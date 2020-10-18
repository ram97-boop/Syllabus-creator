package View;

import model.Course;
import javax.swing.*;
import java.util.Properties;

public class FirstPanel implements CoursePanel {
    /**
     * Swing components
     */
    private JButton nextPanelButton;
    private JButton previousPanelButton;
    private JPanel mainPanel;
    private JTextField courseName;
    private JTextField courseCode;
    private JTextField coursePoints;
    private JCheckBox isDistanceCheckBox;
    private JCheckBox thesisCheckBox;
    private JSplitPane splitPane;
    private JButton saveButton;

    /**
     * MainFrame attributes
     */
    MainFrame frame;
    Properties properties;

    /**
     * Constructors
     */
    public FirstPanel(MainFrame frame) {
        setupComponents();
        this.frame = frame;
        properties = frame.getProperties();
    }

    public FirstPanel(MainFrame frame, Course course) {
        setupComponents();
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

    /**
     * Helper methods for constructors
     */

    /**
     * Set initial states of components.
     */
    private void setupComponents() {
        previousPanelButton.setEnabled(false);
    }

    /**
     * Setting tool tips (help text popups).
     */
    private void setToolTips() {
        coursePoints.setToolTipText("Använd punkt som kommatecken.");
    }

    /**
     * Methods from interface CoursePanel
     */

    /**
     * Returns JPanel with all content.
     * @return
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    /**
     * Returns next panel button.
     * @return
     */
    public JButton getNextPanelButton() {
        return nextPanelButton;
    }

    /**
     * Returns previous panel button.
     * @return
     */
    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }

    /**
     * Returns button to save course.
     * @return
     */
    public JButton getSaveButton() {return saveButton;}

    /**
     * Returns title to be set in MainFrame when this panel is shown.
     * @return A string from properties file.
     */
    public String getFrameName() {
        return properties.getProperty("FirstPanelTitle");
    }

    /**
     * Returns the JSplitPane of this panel.
     * @return
     */
    public JSplitPane getSplitPane() {
        return splitPane;
    }

    /**
     * Updates components of the panel based on previously entered attributes of course.
     * @param course
     */
    public void updateView(Course course) {
        splitPane.setDividerLocation(0.5);
        System.out.println(splitPane.getDividerLocation());
    }

    /**
     * Getters
     */

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
