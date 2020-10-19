package controller;

import View.LiteraturePanel;
import model.Course;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * CourseLiteratureControllerTest
 *
 * Test method updateModel() in class CourseLiteratureController.
 *
 * @author Sofia Ayata Karbin
 */

public class CourseLiteratureControllerTest {

    private Course course;
    private LiteraturePanel panel;
    private CourseLiteratureController courseLiteratureController;

    @Before
    public void setUp() {
        course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
        panel = new LiteraturePanel();
        courseLiteratureController = new CourseLiteratureController(course, panel);

        panel.getMultipleInstitutionsCheckBox().setSelected(false);
    }

    @Test
    public void noInstitutionGivenShouldResultInInstitutionForCourseIsEmpty() {
        assertNull(course.getInstitution());

        courseLiteratureController.updateModel();

        assertEquals("", course.getInstitution());
    }

    @Test
    public void institutionGivenShouldResultInInstitutionForCourseIsSetCorrect() {
        assertNull(course.getInstitution());

        String expectedString = "Matematiska institutionen";
        panel.getInstitutionField().setText("Matematiska institutionen");

        courseLiteratureController.updateModel();

        assertEquals(expectedString, course.getInstitution());
    }

    @Test
    public void multipleInstitutionsNotCheckedShouldResultInCourseHasNotMultipleInstitutions() {
        assertFalse(course.hasMultipleInstitutions());

        courseLiteratureController.updateModel();

        assertFalse(course.hasMultipleInstitutions());
    }

    @Test
    public void multipleInstitutionsCheckedShouldResultInCourseHasMultipleInstitutions() {
        assertFalse(course.hasMultipleInstitutions());

        panel.getMultipleInstitutionsCheckBox().setSelected(true);

        courseLiteratureController.updateModel();

        assertTrue(course.hasMultipleInstitutions());
    }

    @Test
    public void courseWithRadio1CheckedShouldResultInPrintMultipleInstitutionsAlt1IsTrue() {
        assertFalse(course.getPrintMultipleInstitutionsAlt1());

        panel.getMultipleInstitutionsCheckBox().setSelected(true);
        panel.getRadio1().setVisible(true);
        panel.getRadio1().setSelected(true);

        courseLiteratureController.updateModel();

        assertTrue(course.getPrintMultipleInstitutionsAlt1());
    }

    @Test
    public void courseWithRadio1NotCheckedShouldResultInPrintMultipleInstitutionsAlt1IsFalse() {
        assertFalse(course.getPrintMultipleInstitutionsAlt1());

        panel.getMultipleInstitutionsCheckBox().setSelected(true);
        panel.getRadio1().setVisible(true);
        panel.getRadio1().setSelected(false);

        courseLiteratureController.updateModel();

        assertFalse(course.getPrintMultipleInstitutionsAlt1());
    }

    @Test
    public void courseWithRadio1CheckedButNotVisibleShouldResultInPrintMultipleInstitutionsAlt1IsFalse() {
        assertFalse(course.hasMultipleInstitutions());
        assertFalse(course.getPrintMultipleInstitutionsAlt1());

        panel.getRadio1().setVisible(false);
        panel.getRadio1().setSelected(true);

        courseLiteratureController.updateModel();

        assertFalse(course.getPrintMultipleInstitutionsAlt1());
    }
}