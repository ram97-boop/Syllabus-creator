package controller;
import View.CourseInfoPanel;
import model.Course;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CourseInfoControllerTest
 *
 * Test method updateModel() in class CourseInfoController.
 *
 * @author Sofia Ayata Karbin
 */

public class CourseInfoControllerTest {

    private CourseInfoController courseInfoController;
    private Course course;
    private final CourseInfoPanel panel = new CourseInfoPanel();


    @Before
    public void setUp() {
        course = new Course();
        courseInfoController = new CourseInfoController(course, panel);

        // default values
        panel.getIsDistanceCheckBox().setSelected(false);
        panel.getThesisCheckBox().setSelected(false);

    }

    @Test
    public void updateModelWithCorrectInputShouldResultInCourseAttributesAreSetCorrect() {

        panel.getCourseCode().setText("Mjukvaruutveckling");
        panel.getCoursePoints().setText("7.5");
        panel.getCourseCode().setText("DA4002");

        courseInfoController.updateModel();

        assertEquals(panel.getCourseName().getText(), course.getName());
        assertEquals(panel.getCourseCode().getText(), course.getCode());
        assertTrue(Math.abs(Float.parseFloat(panel.getCoursePoints().getText())-course.getCredits())<1e-8);
        assertFalse(course.isDistance());
        assertFalse(course.hasThesis());

        // assert have not been set
        assertNull(course.getGradingScale());
        assertTrue(course.getCourseParts().isEmpty());
        assertTrue(course.getGoals().isEmpty());
        assertNull(course.getLanguage());

    }

    @Test
    public void updateModelForDistanceCourseWithThesisShouldResultInCourseAttributesAreSetCorrect() {

        panel.getCourseCode().setText("Mjukvaruutveckling");
        panel.getCoursePoints().setText("7.5");
        panel.getCourseCode().setText("DA4002");
        panel.getIsDistanceCheckBox().setSelected(true);
        panel.getThesisCheckBox().setSelected(true);

        courseInfoController.updateModel();

        assertEquals(panel.getCourseName().getText(), course.getName());
        assertEquals(panel.getCourseCode().getText(), course.getCode());
        assertTrue(Math.abs(Float.parseFloat(panel.getCoursePoints().getText())-course.getCredits())<1e-8);
        assertTrue(course.isDistance());
        assertTrue(course.hasThesis());

        // assert have not been set
        assertNull(course.getGradingScale());
        assertTrue(course.getCourseParts().isEmpty());
        assertTrue(course.getGoals().isEmpty());
        assertNull(course.getLanguage());

    }

    @Test (expected = RuntimeException.class)
    public void updateModelWithWrongFormOnCreditShouldResultInException() {

        panel.getCourseCode().setText("Mjukvaruutveckling");
        panel.getCoursePoints().setText("7,5");
        panel.getCourseCode().setText("DA4002");

        courseInfoController.updateModel();

        assertNull(course.getName());
        assertNull(course.getCode());
        assertTrue(Math.abs(0.0-course.getCredits())<1e-8);
        assertFalse(course.isDistance());
        assertFalse(course.hasThesis());
        assertTrue(course.getCourseParts().isEmpty());
        assertTrue(course.getGoals().isEmpty());
        assertNull(course.getLanguage());
        assertNull(course.getGradingScale());
    }

}