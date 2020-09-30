package View;

import javax.swing.*;

public class StartPanel {
    private JPanel mainPanel;
    private JRadioButton createNewCourseButton;
    private JRadioButton continueCourseButton;
    private JButton nextPanelButton;
    private JTextField courseCode;
    private JPanel courseInfoPanel;

    private StartPanel() {
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

    private static final StartPanel INSTANCE = new StartPanel();
    public static StartPanel getInstance() {return INSTANCE;}

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
