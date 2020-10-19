
package View;

import model.Course;

import javax.swing.*;

/**
 * CoursePanel
 * Interface class
 *
 * @author Mikael Stener
 */

public interface CoursePanel {
    JPanel getPanel();
    JButton getPreviousPanelButton();
    JButton getNextPanelButton();
    JButton getSaveButton();
    String getFrameName();
    JSplitPane getSplitPane();
    void updateView(Course course);
}
