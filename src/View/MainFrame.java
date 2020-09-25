package View;

import controller.*;
import model.Course;

import javax.swing.*;

public class MainFrame extends JFrame {
    private final Course course = new Course();

    private final CourseController[] controllers = {
            new FirstController(course, FirstPanel.getInstance()),
            new CourseContentController(course, CourseContentPanel.getInstance()),
            new ExpectedResultController(course, ExpectedResultPanel.getInstance()),
            new TeachingController(course, TeachingPanel.getInstance()),
            new ExaminationController(course, ExaminationPanel.getInstance()),
            new CourseLiteratureController(course, LiteraturePanel.getInstance())
    };

    public MainFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(controllers[0].getPanel().getPanel());
        this.pack();
        controllers[0].getPanel().getNextPanelButton().addActionListener(e -> {
                    controllers[0].updateModel();
                    changePanel(1);
                });
    }

    public void changePanel(int nextIndex) {
        controllers[nextIndex].getPanel().updateView(course);
        controllers[nextIndex].getPanel().getNextPanelButton().addActionListener(e -> {
            controllers[nextIndex].updateModel();
            changePanel(nextIndex + 1);
        });
        controllers[nextIndex].getPanel().getPreviousPanelButton().addActionListener(e -> {
            controllers[nextIndex].updateModel();
            changePanel(nextIndex - 1);
        });
        this.setContentPane(controllers[nextIndex].getPanel().getPanel());
        this.pack();
        this.setSize(800, 600);
    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Syllabus Creator");
        frame.setVisible(true);
        frame.setSize(800, 600);
    }
}
