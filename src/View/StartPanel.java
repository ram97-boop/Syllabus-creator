package View;

import javax.swing.*;

public class StartPanel {
    private JPanel mainPanel;
    private JRadioButton createNewCourseButton;
    private JRadioButton continueCourseButton;
    private JButton nextPanelButton;
    private JTextField courseCode;
    private JPanel courseInfoPanel;

    public StartPanel() {
        courseInfoPanel.setVisible(false);
        continueCourseButton.addActionListener(e -> {
            createNewCourseButton.setSelected(false);
            updateCourseInfoPanel();
        });
        createNewCourseButton.addActionListener(e -> {
            continueCourseButton.setSelected(false);
            updateCourseInfoPanel();
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }
    public JButton getNextPanelButton() {
        return nextPanelButton;
    }

    private void updateCourseInfoPanel() {
        courseInfoPanel.setVisible(continueCourseButton.isSelected());
    }

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
