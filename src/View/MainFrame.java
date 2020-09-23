package View;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final CoursePanel[] panels = {
        FirstPanel.getInstance(),
        CourseContentPanel.getInstance(),
        ExpectedResultPanel.getInstance(),
        TeachingPanel.getInstance(),
        ExaminationPanel.getInstance(),
        LiteraturePanel.getInstance()
    };

    public MainFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(panels[0].getPanel());
        this.pack();
        panels[0].getNextPanelButton().addActionListener(e -> changePanel(1));
        panels[0].getPreviousPanelButton().addActionListener(e -> changePanel(0));
    }

    public void changePanel(int nextIndex) {
        this.setContentPane(panels[nextIndex].getPanel());
        this.pack();
        panels[nextIndex].getNextPanelButton().addActionListener(e -> changePanel(nextIndex + 1));
        panels[nextIndex].getPreviousPanelButton().addActionListener(e -> changePanel(nextIndex - 1));
    }

    public CoursePanel[] getPanels() {
        return panels;
    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Syllabus Creator");
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
