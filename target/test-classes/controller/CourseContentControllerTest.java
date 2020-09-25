/*
Created by: Sofia Ayata Karbin
 */

package controller;

import View.CourseContentPanel;
import model.Course;
import model.CoursePart;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CourseContentControllerTest {

    private final Course course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
    private CourseContentController courseContentController;
    private CourseContentPanel panel;

    private final CoursePart firstPart = new CoursePart();
    private final CoursePart secondPart = new CoursePart();
    private final CoursePart thirdPart = new CoursePart();

    private ArrayList<CoursePart> courseParts;

    @Before
    public void setUp() {
        courseContentController = new CourseContentController(course, CourseContentPanel.getInstance());
        panel = courseContentController.getPanel();
        courseParts = new ArrayList<>();

        Arrays.stream(panel.getPartPanels()).forEach(jPanel -> jPanel.setVisible(false));

        firstPart.setCredits(1.5);
        firstPart.setName("Teori");
        secondPart.setCredits(3.0);
        secondPart.setName("Laboration");
        thirdPart.setCredits(3);
        thirdPart.setName("Individuell uppgift");
    }

    @Test
    public void sumCoursePartsShouldResultInSumOfAllCourseParts() {
        courseParts.add(firstPart);
        courseParts.add(secondPart);
        courseParts.add(thirdPart);

        double expectedSum = firstPart.getCredits() + secondPart.getCredits() + thirdPart.getCredits();
        double result = courseContentController.sumCourseParts(courseParts);

        assertTrue(Math.abs(expectedSum-result) < 1e-8);
    }

    @Test
    public void sumEmptyCoursePartArrayShouldResultInZero() {
        double expectedSum = 0.0;
        double result = courseContentController.sumCourseParts(courseParts);

        assertTrue(Math.abs(expectedSum-result) < 1e-8);
    }

    @Test
    public void noCoursePartEnteredShouldResultInEmptyCoursePartForCourse() {
        courseContentController.updateModel();

        assertTrue(course.getCourseParts().isEmpty());
    }

    @Test
    public void threeEnteredCoursePartsShouldResultInCoursePartArrayIsSetForCourse() {
        setUpTextFields();

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());
        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());

    }

    @Test (expected = RuntimeException.class)
    public void enteredCoursePartsWithWrongSumShouldResultInException() {

        JPanel[] partPanels = panel.getPartPanels();

        // set first three panels visible
        partPanels[0].setVisible(true);
        partPanels[1].setVisible(true);

        // set text fields
        ((JTextField)partPanels[0].getComponent(0)).setText(firstPart.getName());
        ((JTextField)partPanels[0].getComponent(2)).setText(Double.toString(firstPart.getCredits()));
        ((JTextField)partPanels[1].getComponent(0)).setText(secondPart.getName());
        ((JTextField)partPanels[1].getComponent(2)).setText(Double.toString(secondPart.getCredits()));

        courseContentController.updateModel();

    }

    @Test
    public void callingUpdateModelSeveralTimesShouldResultInCorrectSetCoursePartArrayForCourse() {

        setUpTextFields();

        courseContentController.updateModel();
        courseContentController.updateModel();
        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());
        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());

    }

    private void setUpTextFields() {
        JPanel[] partPanels = panel.getPartPanels();

        // set first three panels visible
        partPanels[0].setVisible(true);
        partPanels[1].setVisible(true);
        partPanels[2].setVisible(true);

        // set text fields
        ((JTextField)partPanels[0].getComponent(0)).setText(firstPart.getName());
        ((JTextField)partPanels[0].getComponent(2)).setText(Double.toString(firstPart.getCredits()));
        ((JTextField)partPanels[1].getComponent(0)).setText(secondPart.getName());
        ((JTextField)partPanels[1].getComponent(2)).setText(Double.toString(secondPart.getCredits()));
        ((JTextField)partPanels[2].getComponent(0)).setText(thirdPart.getName());
        ((JTextField)partPanels[2].getComponent(2)).setText(Double.toString(thirdPart.getCredits()));
    }


}