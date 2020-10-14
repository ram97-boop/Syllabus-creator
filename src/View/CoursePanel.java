package View;

import model.Course;

import javax.swing.*;

public interface CoursePanel {
    JPanel getPanel();
    JButton getPreviousPanelButton();
    JButton getNextPanelButton();
    String getFrameName();
    JSplitPane getSplitPane();
    void updateView(Course course);
}
