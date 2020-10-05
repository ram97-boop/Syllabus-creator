package controller;

import View.CourseContentPanel;
import model.Course;
import model.CoursePart;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

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

        try {
            int nParts = (int) courseContentPanel.getnPartsComboBox().getSelectedItem();

            if (nParts>0) {
                Arrays.stream(textFields).filter(row -> row[0].isVisible()).forEach(row -> {
                    String name = row[0].getText();
                    String engName = row[1].getText();
                    String credit = row[2].getText();

                    CoursePart coursePart = new CoursePart();
                    coursePart.setName(name);
                    coursePart.setEngName(engName);
                    coursePart.setCredits((Double.parseDouble(credit)));

                    courseParts.add(coursePart);
                });
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Fel i inmatning! Vänligen kontrollera att inmatning är korrekt.");
        }

        setCoursePartsForCourse(courseParts);

    }

    private void setCoursePartsForCourse(ArrayList<CoursePart> courseParts) {
        if (!courseParts.isEmpty()){
            if (Math.abs(sumCourseParts(courseParts)-course.getCredits()) < 1e-8) {
                course.setCourseParts(courseParts);
            } else {
                throw new RuntimeException("" +
                        "Summan av kursdelarna är inte samma som totala poängen för kursen ("
                        + course.getCredits() + ").");
            }
        } else {
            course.setCourseParts(courseParts);
        }
    }

    double sumCourseParts(ArrayList<CoursePart> courseParts) {
        return courseParts.stream().map(CoursePart::getCredits).reduce(0.0, Double::sum);
    }

}
