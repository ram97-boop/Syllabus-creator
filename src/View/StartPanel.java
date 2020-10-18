package View;

import javax.swing.*;

public class StartPanel {
    /**
     * Swing components
     */
    private JPanel mainPanel;
    private JRadioButton createNewCourseButton;
    private JRadioButton continueCourseButton;
    private JButton nextPanelButton;
    private JTextField courseCode;
    private JPanel courseInfoPanel;

    /**
     * Constructor
     */

    public StartPanel() {
        setupComponents();
        addActionListeners();
    }

    /**
     * Helper methods for constructor
     */

    /**
     * Set initial states of components.
     */
    private void setupComponents() {
        courseInfoPanel.setVisible(false);
    }

    /**
     * Add action listeners to components.
     */
    private void addActionListeners() {
        continueCourseButton.addActionListener(e -> {
            createNewCourseButton.setSelected(false);
            updateCourseInfoPanel();
        });
        createNewCourseButton.addActionListener(e -> {
            continueCourseButton.setSelected(false);
            updateCourseInfoPanel();
        });
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
     * Action listener methods
     */

    /**
     * Shows courseInfoPanel based on if user wants to load course
     */
    private void updateCourseInfoPanel() {
        courseInfoPanel.setVisible(continueCourseButton.isSelected());
    }

    /**
     * Getters
     */

    public JRadioButton getCreateNewCourseButton() {
        return createNewCourseButton;
    }

    public JRadioButton getContinueCourseButton() {
        return continueCourseButton;
    }

    public JTextField getCourseCode() {
        return courseCode;
    }
}
