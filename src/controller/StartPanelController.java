package controller;

import View.*;
import model.Course;
import sun.applet.Main;

import java.util.Arrays;

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


    public CourseController[] getCourseControllers(Course course) {

        if (startPanel.getContinueCourseButton().isSelected()) {
            throw new RuntimeException("Detta alternativen är inte möjligt just nu");
        } else if (startPanel.getCreateNewCourseButton().isSelected()) {
            CoursePanel[] panels = getAllPanels();

            course.setCoursePanels(panels);

            return new CourseController[]{
                    new FirstController(course, (FirstPanel) panels[0]),
                    new CourseContentController(course, (CourseContentPanel) panels[1]),
                    new ExpectedResultController(course, (ExpectedResultPanel) panels[2]),
                    new TeachingController(course, (TeachingPanel) panels[3]),
                    new ExaminationController(course, (ExaminationPanel) panels[4]),
                    new CourseLiteratureController(course, (LiteraturePanel) panels[5])
            };
        }

        throw new RuntimeException("Måste välja ett av alternativen");

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
