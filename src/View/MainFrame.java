package View;

import controller.*;
import model.Course;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Properties;

// TODO Save draft Button

public class MainFrame extends JFrame {
    private int width = 800;
    private int height = 800;
    private final Course course = new Course();

    private final StartPanelController startPanelController = new StartPanelController(StartPanel.getInstance());

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
                controllers = startPanelController.getCourseControllers(course);
                changePanel(0);
                addActionListeners();
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
    }

    private void setUpPropertiesFile() {
        properties = new Properties();
        URL url = getClass().getResource("res.properties");
        File file = new File(url.getPath());
        try {
            InputStream input = new FileInputStream(file);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changePanel(int nextIndex) {
        controllers[nextIndex].getPanel().updateView(this, course);
        this.setContentPane(controllers[nextIndex].getPanel().getPanel());
        this.setTitle(properties.getProperty("Frame_name") + " | " + controllers[nextIndex].getPanel().getFrameName());
        keepSize();
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
