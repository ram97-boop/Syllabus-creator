/*
Created by: Sofia Ayata Karbin
 */

package controller;

import View.CourseContentPanel;
import model.Course;
import model.CoursePart;
import model.GradingScale;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CourseContentControllerTest {

    private Course course;
    private CourseContentController courseContentController;
    private CourseContentPanel panel = new CourseContentPanel();

    private CoursePart firstPart;
    private CoursePart secondPart;
    private CoursePart thirdPart;

    private ArrayList<CoursePart> courseParts;
    private final GradingScale gradingScale = new GradingScale();

    @Before
    public void setUp() {
        course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
        courseContentController = new CourseContentController(course, panel);

        // set all text-fields to not visible
        JTextField[][] partFields = panel.getPartFields();
        for (JTextField[] row : partFields) {
            row[0].setVisible(false);
            row[1].setVisible(false);
            row[2].setVisible(false);
        }

        firstPart = new CoursePart();
        secondPart = new CoursePart();
        thirdPart = new CoursePart();

        firstPart.setCredits(1.5);
        firstPart.setName("Teori");
        firstPart.setEngName("Theory");
        secondPart.setCredits(3.0);
        secondPart.setName("Laboration");
        secondPart.setEngName("Lab");
        thirdPart.setCredits(3);
        thirdPart.setName("Projekt");
        thirdPart.setEngName("Project");

        courseParts = new ArrayList<>();
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
    public void noEnteredTextInCourseContentTextPaneShouldResultInEmptyStringForCourseContentText() {
        String expectedResult  = "";

        assertNull(course.getCourseContentText());

        courseContentController.updateModel();

        assertEquals(expectedResult, course.getCourseContentText());
    }

    @Test
    public void enteredTextInCourseContentTextPaneShouldResultInCorrectCourseContentTextIsSet() {
        String expectedResult  = "Text Content For Course";
        panel.getCourseContentTextPane().setText(expectedResult);

        assertNull(course.getCourseContentText());

        courseContentController.updateModel();

        assertEquals(expectedResult, course.getCourseContentText());
    }

    @Test
    public void threeEnteredCoursePartsShouldResultInCoursePartArrayIsSetForCourse() {
        setUpThreeTextFields();

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());
        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());
        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());

    }

    @Test (expected = RuntimeException.class)
    public void enteredCoursePartsWithWrongSumShouldResultInException() {

        setUpTwoTextFields();

        courseContentController.updateModel();

    }

    @Test
    public void callingUpdateModelSeveralTimesShouldResultInCorrectCoursePartArrayForCourse() {

        setUpThreeTextFields();

        courseContentController.updateModel();
        courseContentController.updateModel();
        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());
        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());
        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());

    }

    @Test
    public void changingNumberOfCoursePartsForCourseShouldResultInCorrectCoursePartArrayForCourse() {

        // set 3 course parts
        setUpThreeTextFields();

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());
        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());
        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());

        // changing to 2 course parts. Set new credits to add up to course credit
        firstPart.setCredits(3);
        secondPart.setCredits(4.5);
        setUpTwoTextFields();

        courseContentController.updateModel();

        assertEquals(2, course.getCourseParts().size());
        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());

        // change to 0 course parts
        JComboBox<Integer> nPartsComboBox = panel.getNPartsComboBox();
        nPartsComboBox.setSelectedItem(0);

        courseContentController.updateModel();

        assertEquals(0, course.getCourseParts().size());

    }

    @Test
    public void callingUpdateModelOnCourseWithCoursePartsShouldNotChangeGradingScaleAndExaminationForCourseParts() {

        setUpThreeTextFields();

        firstPart.setGradingScale(gradingScale.getGradingScale(2)); // G-U
        firstPart.setExamination("Examination 1");
        secondPart.setGradingScale(gradingScale.getGradingScale(3)); // VG-U
        secondPart.setExamination("Examination 2");
        thirdPart.setGradingScale(gradingScale.getGradingScale(7)); // A-F
        thirdPart.setExamination("Examination 3");

        course.addCoursePart(firstPart);
        course.addCoursePart(secondPart);
        course.addCoursePart(thirdPart);

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());

        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertTrue(Math.abs(1.5 - course.getCourseParts().get(0).getCredits()) < 1e-8);
        assertEquals(firstPart.getExamination(), course.getCourseParts().get(0).getExamination());
        assertEquals(2, course.getCourseParts().get(0).getGradingScale().size());

        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(1).getCredits()) < 1e-8);
        assertEquals(secondPart.getExamination(), course.getCourseParts().get(1).getExamination());
        assertEquals(3, course.getCourseParts().get(1).getGradingScale().size());

        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(2).getCredits()) < 1e-8);
        assertEquals(thirdPart.getExamination(), course.getCourseParts().get(2).getExamination());
        assertEquals(7, course.getCourseParts().get(2).getGradingScale().size());

    }

    @Test
    public void changingEnglishNameAndCreditForCoursePartAndAddingCoursePartShouldResultInCorrectCoursePartArrayForCourse() {

        setUpThreeTextFields();

        // set "old" value of parts
        firstPart.setCredits(5.5);
        firstPart.setName("Teori");
        firstPart.setEngName("Examination");
        secondPart.setCredits(2.0);
        secondPart.setName("Laboration");
        secondPart.setEngName("Labs");

        // set grading scale and examination
        firstPart.setGradingScale(gradingScale.getGradingScale(3)); // G-U
        firstPart.setExamination("Examination 1");
        secondPart.setGradingScale(gradingScale.getGradingScale(7)); // VG-U
        secondPart.setExamination("Examination 2");

        course.addCoursePart(firstPart);
        course.addCoursePart(secondPart);

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());

        assertEquals("Teori", course.getCourseParts().get(0).getName());
        assertEquals("Theory", course.getCourseParts().get(0).getEngName());
        assertEquals(firstPart.getExamination(), course.getCourseParts().get(0).getExamination());
        assertEquals(3, course.getCourseParts().get(0).getGradingScale().size());

        assertEquals("Laboration", course.getCourseParts().get(1).getName());
        assertEquals("Lab", course.getCourseParts().get(1).getEngName());
        assertEquals(secondPart.getExamination(), course.getCourseParts().get(1).getExamination());
        assertEquals(7, course.getCourseParts().get(1).getGradingScale().size());

        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());
        assertNull(course.getCourseParts().get(2).getExamination());
        assertNull(course.getCourseParts().get(2).getGradingScale());

    }

    @Test
    public void changingEnglishNameAndCreditForCoursePartsAndRemovingCoursePartShouldResultInCorrectCoursePartArrayForCourse() {

        setUpThreeTextFields();

        // set "old" value of parts
        firstPart.setCredits(2);
        firstPart.setEngName("Examination");
        secondPart.setCredits(1.5);
        secondPart.setEngName("Labs");
        thirdPart.setCredits(1.5);
        thirdPart.setEngName("Pro");

        // set grading scale and examination
        firstPart.setGradingScale(gradingScale.getGradingScale(3)); // G-U
        firstPart.setExamination("Examination 1");
        secondPart.setGradingScale(gradingScale.getGradingScale(7)); // VG-U
        secondPart.setExamination("Examination 2");

        CoursePart extraPart = new CoursePart();
        extraPart.setName("Extra");
        extraPart.setEngName("Extra");
        extraPart.setCredits(2.5);

        course.addCoursePart(firstPart);
        course.addCoursePart(secondPart);
        course.addCoursePart(thirdPart);
        course.addCoursePart(extraPart);

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());

        assertEquals("Teori", course.getCourseParts().get(0).getName());
        assertEquals("Theory", course.getCourseParts().get(0).getEngName());
        assertTrue(Math.abs(1.5 - course.getCourseParts().get(0).getCredits()) < 1e-8);
        assertEquals(firstPart.getExamination(), course.getCourseParts().get(0).getExamination());
        assertEquals(3, course.getCourseParts().get(0).getGradingScale().size());

        assertEquals("Laboration", course.getCourseParts().get(1).getName());
        assertEquals("Lab", course.getCourseParts().get(1).getEngName());
        assertEquals(secondPart.getExamination(), course.getCourseParts().get(1).getExamination());
        assertTrue(Math.abs(3 - course.getCourseParts().get(1).getCredits()) < 1e-8);
        assertEquals(7, course.getCourseParts().get(1).getGradingScale().size());

        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(2).getCredits()) < 1e-8);
        assertNull(course.getCourseParts().get(2).getExamination());
        assertNull(course.getCourseParts().get(2).getGradingScale());

    }

    @Test
    public void changingOrderOfCoursePartsShouldResultInNewOrderIsSetWithSameCoursePartsForCourse() {

        course.addCoursePart(thirdPart);
        course.addCoursePart(firstPart);
        course.addCoursePart(secondPart);

        setUpThreeTextFields();

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());

        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertTrue(Math.abs(1.5 - course.getCourseParts().get(0).getCredits()) < 1e-8);

        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(1).getCredits()) < 1e-8);

        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(2).getCredits()) < 1e-8);

    }

    @Test
    public void changingOrderOfCoursePartsAndAddingNewCoursePartShouldResultInNewOrderIsSetWithSameCoursePartsForCourse() {

        course.addCoursePart(thirdPart);
        course.addCoursePart(firstPart);

        setUpThreeTextFields();

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());

        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertTrue(Math.abs(1.5 - course.getCourseParts().get(0).getCredits()) < 1e-8);

        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(1).getCredits()) < 1e-8);

        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(2).getCredits()) < 1e-8);

    }

    @Test
    public void changingOrderOfCoursePartsAndRemovingCoursePartShouldResultInNewOrderIsSetWithSameCoursePartsForCourse() {

        CoursePart extraPart = new CoursePart();
        extraPart.setName("Extra");
        extraPart.setEngName("Extra");
        extraPart.setCredits(2.5);

        course.addCoursePart(secondPart);
        course.addCoursePart(extraPart);
        course.addCoursePart(thirdPart);
        course.addCoursePart(firstPart);

        setUpThreeTextFields();

        courseContentController.updateModel();

        assertEquals(3, course.getCourseParts().size());

        assertEquals(firstPart.getName(), course.getCourseParts().get(0).getName());
        assertEquals(firstPart.getEngName(), course.getCourseParts().get(0).getEngName());
        assertTrue(Math.abs(1.5 - course.getCourseParts().get(0).getCredits()) < 1e-8);

        assertEquals(secondPart.getName(), course.getCourseParts().get(1).getName());
        assertEquals(secondPart.getEngName(), course.getCourseParts().get(1).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(1).getCredits()) < 1e-8);

        assertEquals(thirdPart.getName(), course.getCourseParts().get(2).getName());
        assertEquals(thirdPart.getEngName(), course.getCourseParts().get(2).getEngName());
        assertTrue(Math.abs(3 - course.getCourseParts().get(2).getCredits()) < 1e-8);

    }

    private void setUpThreeTextFields() {

        JComboBox<Integer> nPartsComboBox = panel.getNPartsComboBox();
        nPartsComboBox.setSelectedItem(3);

        JTextField[][] partFields = panel.getPartFields();

        partFields[0][0].setVisible(true);
        partFields[0][1].setVisible(true);
        partFields[0][2].setVisible(true);
        partFields[1][0].setVisible(true);
        partFields[1][1].setVisible(true);
        partFields[1][2].setVisible(true);
        partFields[2][0].setVisible(true);
        partFields[2][1].setVisible(true);
        partFields[2][2].setVisible(true);

        partFields[0][0].setText(firstPart.getName());
        partFields[0][1].setText(firstPart.getEngName());
        partFields[0][2].setText(Double.toString(firstPart.getCredits()));

        partFields[1][0].setText(secondPart.getName());
        partFields[1][1].setText(secondPart.getEngName());
        partFields[1][2].setText(Double.toString(secondPart.getCredits()));

        partFields[2][0].setText(thirdPart.getName());
        partFields[2][1].setText(thirdPart.getEngName());
        partFields[2][2].setText(Double.toString(thirdPart.getCredits()));

    }

    private void setUpTwoTextFields() {
        JTextField[][] partFields = panel.getPartFields();

        JComboBox<Integer> nPartsComboBox = panel.getNPartsComboBox();
        nPartsComboBox.setSelectedItem(2);

        partFields[0][0].setVisible(true);
        partFields[0][1].setVisible(true);
        partFields[0][2].setVisible(true);
        partFields[1][0].setVisible(true);
        partFields[1][1].setVisible(true);
        partFields[1][2].setVisible(true);

        partFields[0][0].setText(firstPart.getName());
        partFields[0][1].setText(firstPart.getEngName());
        partFields[0][2].setText(Double.toString(firstPart.getCredits()));

        partFields[1][0].setText(secondPart.getName());
        partFields[1][1].setText(secondPart.getEngName());
        partFields[1][2].setText(Double.toString(secondPart.getCredits()));
    }

}
