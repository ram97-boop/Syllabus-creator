package View;

import javax.swing.*;

public class TeachingPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;

    private static final TeachingPanel INSTANCE = new TeachingPanel();
    public static TeachingPanel getInstance() {return INSTANCE;}

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
