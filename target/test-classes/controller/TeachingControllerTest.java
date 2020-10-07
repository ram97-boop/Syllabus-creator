/*
Created by: Sofia Ayata Karbin
 */

package controller;


import View.TeachingPanel;
import model.Course;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TeachingControllerTest {

    private final Course course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
    private TeachingPanel teachingPanel = new TeachingPanel();
    private TeachingController teachingController;

    @Before
    public void setUp() {
        teachingController = new TeachingController(course, teachingPanel);
        teachingPanel.getOtherThanSwedishCheckBox().setSelected(false);
        teachingPanel.getRadioButtonCourseInEnglish().setSelected(false);
        teachingPanel.getRadioButtonLanguageGivenAtStart().setSelected(false);
    }

    @Test
    public void updateModelForCourseInSwedishShouldResultInSwedishLanguage() {
        assertNull(course.getLanguage());

        teachingController.updateModel();

        String expectedString = "swedish";

        assertEquals(expectedString, course.getLanguage());
    }

    @Test
    public void radioButtonsPressedButNotOtherThanSwedishCheckBoxShouldResultInSwedishLanguage() {

        String expectedString = "swedish";

        teachingPanel.getRadioButtonCourseInEnglish().setSelected(true);
        teachingController.updateModel();

        assertEquals(expectedString, course.getLanguage());

        teachingPanel.getRadioButtonCourseInEnglish().setSelected(false);
        teachingPanel.getRadioButtonLanguageGivenAtStart().setSelected(true);
        teachingController.updateModel();

        assertEquals(expectedString, course.getLanguage());

        teachingPanel.getRadioButtonCourseInEnglish().setSelected(true);
        teachingPanel.getRadioButtonLanguageGivenAtStart().setSelected(true);
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
    public void radioButtonsPressedAndOtherThanSwedishCheckBoxShouldResultInEnglishLanguage() {
        assertNull(course.getLanguage());

        teachingPanel.getOtherThanSwedishCheckBox().setSelected(true);
        teachingPanel.getRadioButtonCourseInEnglish().setSelected(true);
        teachingPanel.getRadioButtonLanguageGivenAtStart().setSelected(true);

        teachingController.updateModel();

        String expectedString = "english";

        assertEquals(expectedString, course.getLanguage());
    }

    @Test
    public void updateModelForCourseLanguageGivenAtStartShouldResultInNotSpecified() {
        assertNull(course.getLanguage());

        teachingPanel.getOtherThanSwedishCheckBox().setSelected(true);
        teachingPanel.getRadioButtonLanguageGivenAtStart().setSelected(true);

        teachingController.updateModel();

        String expectedString = "not specified";

        assertEquals(expectedString, course.getLanguage());
    }

    @Test
    public void updateModelForCourseWithUnspecifiedLanguageShouldResultInUnknownLanguage() {
        assertNull(course.getLanguage());

        teachingPanel.getOtherThanSwedishCheckBox().setSelected(true);

        teachingController.updateModel();

        String expectedString = "unknown";

        assertEquals(expectedString, course.getLanguage());
    }

}