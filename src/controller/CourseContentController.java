package controller;

import View.CourseContentPanel;
import model.Course;
import model.CoursePart;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CourseContentController
 * Implements CourseController
 *
 * Update course with content entered by user in all fields in the mainPanel in CourseContentPanel.
 *
 * @author Sofia Ayata Karbin
 * @author Mikael Stener
 */

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

        JTextPane courseContentTextPane = courseContentPanel.getCourseContentTextPane();

        course.setCourseContentText(courseContentTextPane.getText());

        ArrayList<CoursePart> enteredCourseParts = setUpArrayListOfCoursePartsFromUserInput();

        if (!enteredCourseParts.isEmpty()) {
            if (Math.abs(sumCourseParts(enteredCourseParts)-course.getCredits()) > 1e-8) {
                throw new RuntimeException("Summan av kursdelarna är inte samma som totala poängen för kursen ("
                        + course.getCredits() + ").");
            }
        }

        if (course.getCourseParts().isEmpty()) {
            course.setCourseParts(enteredCourseParts);
        } else {
            updateCoursePartsForCourse(enteredCourseParts);
        }
    }

    private void updateCoursePartsForCourse(ArrayList<CoursePart> enteredCourseParts) {

        ArrayList<CoursePart> courseParts = course.getCourseParts();

        // collect name of all entered course parts
        List<String> collect = enteredCourseParts.stream().map(CoursePart::getName).map(String::toLowerCase).collect(Collectors.toList());

        // collect course parts that should no longer be in course
        List<CoursePart> notEnteredParts = courseParts.stream().filter(part -> !collect.contains(part.getName().toLowerCase())).collect(Collectors.toList());
        notEnteredParts.forEach(course::removeCoursePart);

        // collect name of all parts in course
        List<String> collect1 = courseParts.stream().map(CoursePart::getName).map(String::toLowerCase).collect(Collectors.toList());

        // collect course parts that should be added to course
        List<CoursePart> notAddedParts = enteredCourseParts.stream().filter(part -> !collect1.contains(part.getName().toLowerCase())).collect(Collectors.toList());
        notAddedParts.forEach(course::addCoursePart);

        // update existing parts with new given data
        enteredCourseParts.stream()
                .filter(part -> collect1.contains(part.getName().toLowerCase()))
                .forEach(coursePartEntered -> {
                    CoursePart coursePart = course.getCourseParts().stream().filter(p -> p.getName().toLowerCase().equals(coursePartEntered.getName().toLowerCase())).findAny().get();
                    coursePart.setName(coursePartEntered.getName()); // if upper/lowercase changed
                    coursePart.setEngName(coursePartEntered.getEngName());
                    coursePart.setCredits(coursePartEntered.getCredits());
                });

        enteredCourseParts.forEach(part -> {
            int index = enteredCourseParts.indexOf(part);
            List<CoursePart> collect2 = courseParts.stream().filter(coursePart -> coursePart.getName().toLowerCase().equals(part.getName().toLowerCase())).collect(Collectors.toList());
            String name = collect2.get(0).getName().toLowerCase();
            if (!courseParts.get(index).getName().toLowerCase().equals(name)) {
                try {
                    course.swapCourseParts(courseParts.get(index), collect2.get(0));
                } catch (Exception e) {
                    throw new RuntimeException("Kunde inte byta plats på kursdelar");
                }
            }
        });
    }

    private ArrayList<CoursePart> setUpArrayListOfCoursePartsFromUserInput() {
        ArrayList<CoursePart> courseParts = new ArrayList<>();
        JTextField[][] textFields = courseContentPanel.getPartFields();

        try {
            int nParts = (int) courseContentPanel.getNPartsComboBox().getSelectedItem();

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

            return courseParts;
        } catch (RuntimeException e) {
            throw new RuntimeException("Fel i inmatning! Vänligen kontrollera att inmatning är korrekt.");
        }
    }

    double sumCourseParts(ArrayList<CoursePart> courseParts) {
        return courseParts.stream().map(CoursePart::getCredits).reduce(0.0, Double::sum);
    }

}
