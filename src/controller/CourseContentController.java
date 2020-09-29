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

        JTextField[][] textFields = courseContentPanel.getPartFields();

        for (JTextField[] row : textFields) {
            if (row[0].isVisible()) {
                String name = row[0].getText();
                String credit = row[2].getText();

                CoursePart coursePart = new CoursePart();
                coursePart.setName(name);
                coursePart.setCredits((Double.parseDouble(credit)));

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
