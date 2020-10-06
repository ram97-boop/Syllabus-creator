package View;

import controller.CourseController;
import controller.StartPanelController;
import model.Course;
import model.FileManagement;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO Save draft Button

public class MainFrame extends JFrame {
    private int width = 800;
    private int height = 800;
    private Course course;
    FileManagement fileManagement = new FileManagement();

    private final StartPanelController startPanelController =
            new StartPanelController(this, new StartPanel());

    private CourseController[] controllers;

    private Properties properties;

    public MainFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(startPanelController.getPanel().getPanel());
        this.pack();
        this.setSize(width, height);

        ToolTipManager.sharedInstance().setDismissDelay(60000);
        ToolTipManager.sharedInstance().setInitialDelay(0);

        setUpPropertiesFile();

        startPanelController.getPanel().getNextPanelButton().addActionListener(l -> {
            try {
                controllers = startPanelController.getCourseControllers(fileManagement);
                course = controllers[0].getCourse();
                changePanel(0);
                addActionListeners();
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
    }

    private void setUpPropertiesFile() {
        properties = new Properties();
        try {
            InputStream input = this.getClass().getResourceAsStream("res.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changePanel(int nextIndex) {
        controllers[nextIndex].getPanel().updateView(course);
        this.setContentPane(controllers[nextIndex].getPanel().getPanel());
        this.setTitle(properties.getProperty("Frame_name") + " | " + controllers[nextIndex].getPanel().getFrameName());
        keepSize();

        // should not be here maybe - but just to see that it works
        if (nextIndex>0) {
            saveCourse();
        }
    }

    private void saveCourse() {
        try {
            fileManagement.saveCourse(course, course.getCode().toLowerCase() + ".json");
        } catch (IOException e) {
            throw new RuntimeException("Kunde inte spara kurs");
        }
    }

    private void addActionListeners() {
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
    }

    public void keepSize() {
        width = this.getWidth();
        height = this.getHeight();
        this.pack();
        this.setSize(width, height);
    }

    public Properties getProperties() {
        return properties;
    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Syllabus Creator");
        frame.setVisible(true);
    }
}
