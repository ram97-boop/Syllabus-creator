package View;

import model.Course;

import javax.swing.*;

public class ExaminationPanel implements CoursePanel {
    private JPanel mainPanel;
    private JButton nextPanelButton;
    private JButton previousPanelButton;

    private static final ExaminationPanel INSTANCE = new ExaminationPanel();
    public static ExaminationPanel getInstance() {return INSTANCE;}

    public JPanel getPanel() {
        return mainPanel;
    }

    public JButton getNextPanelButton() {
        return nextPanelButton;
    }

    public JButton getPreviousPanelButton() {
        return previousPanelButton;
    }

    public void updateView() {     }

    public void printOut(Course course) {

    }
}
