/*
Created by: Sofia Ayata Karbin
 */

package controller;


import View.TeachingPanel;
import model.Course;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeachingControllerTest {

    private final Course course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
    private TeachingPanel teachingPanel = new TeachingPanel();
    private TeachingController teachingController;

    @Before
    public void setUp() {
        teachingController = new TeachingController(course, teachingPanel);
        teachingPanel.getOtherThanSwedishCheckBox().setSelected(false);
        teachingPanel.getRadioButtonCourseInEnglish().setSelected(false);
    }

    @Test
    public void updateModelForCourseInSwedishShouldResultInSwedishLanguage() {
        assertNull(course.getLanguage());

        teachingController.updateModel();

        String expectedString = "swedish";

        assertEquals(expectedString, course.getLanguage());
    }

    @Test
    public void radioButtonPressedButNotOtherThanSwedishCheckBoxShouldResultInSwedishLanguage() {

        String expectedString = "swedish";

        teachingPanel.getRadioButtonCourseInEnglish().setSelected(true);
        teachingController.updateModel();

        assertEquals(expectedString, course.getLanguage());
    }

    @Test
    public void updateModelForCourseInEnglishShouldResultInEnglishLanguage() {
        assertNull(course.getLanguage());

        teachingPanel.getOtherThanSwedishCheckBox().setSelected(true);
        teachingPanel.getRadioButtonCourseInEnglish().setSelected(true);

        teachingController.updateModel();

        String expectedString = "english";

        assertEquals(expectedString, course.getLanguage());
    }


    @Test
    public void updateModelForCourseLanguageGivenAtStartShouldResultInNotSpecified() {
        assertNull(course.getLanguage());

        teachingPanel.getOtherThanSwedishCheckBox().setSelected(true);

        teachingController.updateModel();

        String expectedString = "not specified";

        assertEquals(expectedString, course.getLanguage());
    }

    @Test
    public void courseWithoutThesisShouldResultInThesisSupervisedHoursIsNullAndCanChangeSupervisorIsFalse() {
        assertNull(course.getThesisSupervisedHours());
        assertFalse(course.getCanChangeSupervisor());

        teachingController.updateModel();

        assertNull(course.getThesisSupervisedHours());
        assertFalse(course.getCanChangeSupervisor());
    }

    @Test
    public void courseWithThesisAndCanChangeSupervisorCheckedShouldResultInAttributesAreSetCorrect() {
        course.setThesis(true);
        teachingPanel.getCanChangeSupervisorCheckBox().setSelected(true);
        teachingPanel.getThesisSupervisedHoursField().setText("40");

        assertNull(course.getThesisSupervisedHours());
        assertFalse(course.getCanChangeSupervisor());

        teachingController.updateModel();

        assertEquals("40", course.getThesisSupervisedHours());
        assertTrue(course.getCanChangeSupervisor());
    }

    @Test
    public void courseWithThesisAndCanChangeSupervisorNotCheckedShouldResultInAttributesAreSetCorrect() {
        teachingPanel.getCanChangeSupervisorCheckBox().setSelected(false);
        course.setThesis(true);
        teachingPanel.getThesisSupervisedHoursField().setText("35");

        assertNull(course.getThesisSupervisedHours());
        assertFalse(course.getCanChangeSupervisor());

        teachingController.updateModel();

        assertEquals("35", course.getThesisSupervisedHours());
        assertFalse(course.getCanChangeSupervisor());
    }

    @Test
    public void courseThatIsNotDistanceShouldResultInTeachingIsSetCorrect() {
        course.setDistance(false);
        teachingPanel.getTeachingPane().setText("Teaching text");

        assertNull(course.getTeaching());

        teachingController.updateModel();

        assertEquals("Teaching text", course.getTeaching());
    }

    @Test
    public void courseThatIsDistanceShouldResultInTeachingIsNull() {
        course.setDistance(true);
        teachingPanel.getTeachingPane().setText("Teaching text");
        
        assertNull(course.getTeaching());

        teachingController.updateModel();

        assertNull(course.getTeaching());
    }
}
