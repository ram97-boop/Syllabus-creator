package controller;

import View.CourseContentPanel;
import model.Course;
import model.CoursePart;
import javax.swing.*;
import java.util.ArrayList;

public class CourseContentController implements CourseController {

    private final Course course;
    private final CourseContentPanel courseContentPanel;

    public CourseContentController(Course course, CourseContentPanel courseContentPanel) {
        this.course = course;
        this.courseContentPanel = courseContentPanel;
    }

    public Course getCourse() {
        return course;
    }

    public CourseContentPanel getPanel() {
        return courseContentPanel;
    }

    public void updateModel() {
        ArrayList<CoursePart> courseParts = new ArrayList<>();

        JPanel[] panels = courseContentPanel.getPartPanels();

        for (JPanel panel: panels) {
            if (panel.isVisible()) {
                JTextField name = (JTextField) panel.getComponent(0);
                JTextField credit = (JTextField) panel.getComponent(2);

                CoursePart coursePart = new CoursePart();
                coursePart.setName(name.getText());
                coursePart.setCredits((Double.parseDouble(credit.getText())));

                courseParts.add(coursePart);
            }
        }

        if (!courseParts.isEmpty()){
            if (Math.abs(sumCourseParts(courseParts)-course.getCredits()) < 1e-8) {
                course.setCourseParts(courseParts);
            } else {
                throw new RuntimeException("Wrong sum of course part credits");
            }
        }

    }

    double sumCourseParts(ArrayList<CoursePart> courseParts) {
        return courseParts.stream().map(CoursePart::getCredits).reduce(0.0, Double::sum);
    }

}
