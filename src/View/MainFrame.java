package View;

import controller.*;
import model.Course;

import javax.swing.*;

public class MainFrame extends JFrame {
    private int width = 800;
    private int height = 800;
    private final Course course = new Course();

    private final StartPanelController startPanelController = new StartPanelController(StartPanel.getInstance());

    private CourseController[] controllers;

    public MainFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(startPanelController.getPanel().getPanel());
        this.pack();


        this.setSize(width, height);


        startPanelController.getPanel().getNextPanelButton().addActionListener(l -> {
            try {
                controllers = startPanelController.getCourseControllers(course);

                this.setContentPane(controllers[0].getPanel().getPanel());
                this.pack();
                this.setSize(width, height);

                for (int i = 0; i < controllers.length; i++) {
                    int finalI = i;
                    controllers[i].getPanel().getNextPanelButton().addActionListener(e -> {
                        try{
                            controllers[finalI].updateModel();
                            changePanel(finalI + 1);
                        } catch (RuntimeException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage());
                        }
                    });
                    controllers[i].getPanel().getPreviousPanelButton().addActionListener(e -> {
                        try {
                            changePanel(finalI - 1);
                        } catch (RuntimeException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage());
                        }
                    });
                }
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
    }

    public void changePanel(int nextIndex) {
        controllers[nextIndex].getPanel().updateView(this, course);
        this.setContentPane(controllers[nextIndex].getPanel().getPanel());
        keepSize();
    }

    public void keepSize() {
        width = this.getWidth();
        height = this.getHeight();
        this.pack();
        this.setSize(width, height);
    }


    public static void main(String[] args) {
        JFrame frame = new MainFrame("Syllabus Creator");
        frame.setVisible(true);
    }
}
