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
        this.setSize(800, 600);

        for (int i = 0; i < controllers.length; i++) {
            int finalI = i;
            controllers[i].getPanel().getNextPanelButton().addActionListener(e -> {
                try{
                    controllers[finalI].updateModel();
                    changePanel(finalI + 1);
                } catch (RuntimeException exception) {
                    // do something meaningful
                }
            });
            controllers[i].getPanel().getPreviousPanelButton().addActionListener(e -> {
                try {
                    changePanel(finalI - 1);
                } catch (RuntimeException exception) {
                    // do something meaningful
                }
            });
        }
    }

    public void changePanel(int nextIndex) {
        controllers[nextIndex].getPanel().updateView(course);
        this.setContentPane(controllers[nextIndex].getPanel().getPanel());
        this.pack();
        this.setSize(800, 600);
    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Syllabus Creator");
        frame.setVisible(true);
    }
}
