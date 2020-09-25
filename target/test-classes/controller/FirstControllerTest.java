/*
Created by: Sofia Ayata Karbin
 */

package controller;
import static org.junit.Assert.*;

import View.FirstPanel;
import model.Course;
import org.junit.Before;
import org.junit.Test;



public class FirstControllerTest {

    private FirstController firstController;
    private Course course;
    private FirstPanel panel;


    @Before
    public void setUp() {
        course = new Course();
        firstController = new FirstController(course, FirstPanel.getInstance());
        panel = firstController.getPanel();
    }

    @Test
    public void updateModelWithCorrectTypeShouldResultInCourseAttributesAreSetCorrect() {

        panel.getCourseCode().setText("Mjukvaruutveckling");
        panel.getCoursePoints().setText("7.5");
        panel.getCourseCode().setText("DA4002");

        firstController.updateModel();

        assertEquals(panel.getCourseName().getText(), course.getName());
        assertEquals(panel.getCourseCode().getText(), course.getCode());
        assertTrue(Math.abs(Float.parseFloat(panel.getCoursePoints().getText())-course.getCredits())<1e-8);
        assertFalse(course.isDistance());

        // assert have not been set
        assertTrue(course.getCourseParts().isEmpty());
        assertTrue(course.getGoals().isEmpty());
        assertNull(course.getLanguage());
        assertNull(course.getGradingScale());
    }

    @Test (expected = NumberFormatException.class)
    public void updateModelWithWrongTypeOnCreditShouldResultInException() {

        firstController.updateModel();

        assertNull(course.getName());
        assertNull(course.getCode());
        assertTrue(Math.abs(0.0-course.getCredits())<1e-8);
        assertFalse(course.isDistance());
        assertTrue(course.getCourseParts().isEmpty());
        assertTrue(course.getGoals().isEmpty());
        assertNull(course.getLanguage());
        assertNull(course.getGradingScale());
    }

}