package View;

import model.Course;

import javax.swing.*;

public class ExpectedResultPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;

    private static final ExpectedResultPanel INSTANCE = new ExpectedResultPanel();
    public static ExpectedResultPanel getInstance() {return INSTANCE;}

    public JPanel getPanel() {
        return mainPanel;
    }

    public JButton getNextPanelButton() {
        return nextPanelButton;
    }

    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }

    public void printOut(Course course) {

    }
}
