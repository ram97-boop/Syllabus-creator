package View;

import controller.*;
import model.Course;
import model.FileManagement;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * MainFrame
 * Extends JFrame
 * Main class of project

 * Handles CoursePanel objects by setting their mainPanel as JFrame content.
 * Handles the saving of a course to a .json-file.
 * 
 * @author Mikael Stener
 * @author Sofia Ayata Karbin
 */

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
        CoursePanel coursePanel = controllers[nextIndex].getPanel();
        coursePanel.updateView(course);
        this.setContentPane(coursePanel.getPanel());
        this.setTitle(properties.getProperty("Frame_name") + " | " + coursePanel.getFrameName());
        keepSize(coursePanel.getSplitPane());

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
                    saveCourse();
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
            controllers[i].getPanel().getSaveButton().addActionListener(e -> {
                try {
                    controllers[finalI].updateModel();
                    saveCourse();
                    JOptionPane.showMessageDialog(null, "Kurs sparad.");
                } catch (RuntimeException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            });
        }
    }

    public void keepSize(JSplitPane splitPane) {
        width = this.getWidth();
        height = this.getHeight();
        this.pack();
        this.setSize(width, height);
        splitPane.setDividerLocation(1.0d);
    }

    public Properties getProperties() {
        return properties;
    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Syllabus Creator");
        frame.setVisible(true);
    }
}
