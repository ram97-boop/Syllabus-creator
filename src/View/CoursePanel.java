package View;

import model.Course;

import javax.swing.*;

public interface CoursePanel {
    JPanel getPanel();
    JButton getPreviousPanelButton();
    JButton getNextPanelButton();
    JButton getSaveButton();
    String getFrameName();
    JSplitPane getSplitPane();
    void updateView(Course course);
}
