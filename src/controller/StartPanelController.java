package controller;

import View.*;
import model.Course;
import model.FileManagement;

import java.io.IOException;

public class StartPanelController {
    private final MainFrame frame;
    private final StartPanel startPanel;

    public StartPanelController(MainFrame frame, StartPanel startPanel) {
        this.frame = frame;
        this.startPanel = startPanel;
    }

    public StartPanel getPanel() {
        return startPanel;
    }

    public CourseController[] getCourseControllers(FileManagement fileManagement) {
        Course course;
        CoursePanel[] panels;

        if (startPanel.getContinueCourseButton().isSelected()) {
            String courseCode = startPanel.getCourseCode().getText();
            try {
                course = fileManagement.loadCourse(courseCode.toLowerCase() + ".json");
                panels = getAllPanels(); // should get them in another way...
            } catch (RuntimeException | IOException e) {
                throw new RuntimeException("Kunde inte hitta sparad kurs med kurskod: " + courseCode);
            }
        } else if (startPanel.getCreateNewCourseButton().isSelected()) {
            course = new Course();
            panels = getAllPanels();
            course.setCoursePanels(panels);
        } else {
            throw new RuntimeException("Måste välja ett av alternativen");
        }

        return new CourseController[]{
                new FirstController(course, (FirstPanel) panels[0]),
                new CourseContentController(course, (CourseContentPanel) panels[1]),
                new ExpectedResultController(course, (ExpectedResultPanel) panels[2]),
                new TeachingController(course, (TeachingPanel) panels[3]),
                new ExaminationController(course, (ExaminationPanel) panels[4]),
                new CourseLiteratureController(course, (LiteraturePanel) panels[5])
        };

    }

    private CoursePanel[] getAllPanels() {
        return new CoursePanel[]{
                new FirstPanel(frame),
                new CourseContentPanel(frame),
                new ExpectedResultPanel(frame),
                new TeachingPanel(frame),
                new ExaminationPanel(frame),
                new LiteraturePanel(frame)};
    }

}
