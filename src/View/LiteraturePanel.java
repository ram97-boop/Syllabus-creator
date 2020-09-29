package View;

import model.Course;

import javax.swing.*;

public class LiteraturePanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;

    private LiteraturePanel() {
        nextPanelButton.setEnabled(false);
    }
    private static final LiteraturePanel INSTANCE = new LiteraturePanel();
    public static LiteraturePanel getInstance() {return INSTANCE;}

    public JPanel getPanel() {
        return mainPanel;
    }

    public JButton getNextPanelButton() {
        return nextPanelButton;
    }

    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }

    public void updateView(Course course) {     }

    public void printOut(Course course) {

    }
}
