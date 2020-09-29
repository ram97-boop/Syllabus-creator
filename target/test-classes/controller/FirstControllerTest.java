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
        assertFalse(course.hasThesis());

        assertEquals(7, course.getGradingScale().size());
        assertEquals("A", course.getGradingScale().get(0).substring(0,1));
        assertEquals("B", course.getGradingScale().get(1).substring(0,1));
        assertEquals("C", course.getGradingScale().get(2).substring(0,1));
        assertEquals("D", course.getGradingScale().get(3).substring(0,1));
        assertEquals("E", course.getGradingScale().get(4).substring(0,1));
        assertEquals("Fx", course.getGradingScale().get(5).substring(0,2));
        assertEquals("F", course.getGradingScale().get(6).substring(0,1));

        // assert have not been set
        assertTrue(course.getCourseParts().isEmpty());
        assertTrue(course.getGoals().isEmpty());
        assertNull(course.getLanguage());

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