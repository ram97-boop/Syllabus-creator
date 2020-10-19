package controller;

import View.ExpectedResultPanel;
import model.Course;
import model.CoursePart;
import model.Goal;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * ExpectedResultControllerTest
 *
 * Test method updateModel() in class ExpectedResultController.
 *
 * @author Sofia Ayata Karbin
 */

public class ExpectedResultControllerTest {

    private Course course;
    private ExpectedResultController expectedResultController;
    private ExpectedResultPanel panel;

    private final ArrayList<CoursePart> parts = new ArrayList<>();
    private final CoursePart part1 = new CoursePart();
    private final CoursePart part2 = new CoursePart();
    private final CoursePart part3 = new CoursePart();

    @Before
    public void setUp(){
        course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
        panel = new ExpectedResultPanel();
        expectedResultController = new ExpectedResultController(course, panel);

        HashMap<JTextField, JRadioButton[]> goalsMap = panel.getGoals();
        goalsMap.forEach((key, value) -> {
            Arrays.stream(value).forEach(button -> {
                button.setVisible(false);
                button.setSelected(false);
            });
        });

        JCheckBox isConnectedToAll = panel.getIsConnectedToAll();
        isConnectedToAll.setSelected(false);

        part1.setName("Course part 1");
        part1.setCredits(2.5);
        part2.setName("Course part 2");
        part2.setCredits(3);
        part3.setName("Course part 3");
        part3.setCredits(2.0);

        parts.add(part1);
        parts.add(part2);
        parts.add(part3);

        course.setCourseParts(parts);
    }

    @Test
    public void printAltOneButtonSelectedShouldResultInTrue() {
        assertFalse(course.getPrintGoalsAlt1());

        panel.getPrintAlt1Radio().setSelected(true);

        expectedResultController.updateModel();

        assertTrue(course.getPrintGoalsAlt1());
    }

    @Test
    public void printAltOneButtonNotSelectedShouldResultInFalse() {
        assertFalse(course.getPrintGoalsAlt1());

        panel.getPrintAlt1Radio().setSelected(false);

        expectedResultController.updateModel();

        assertFalse(course.getPrintGoalsAlt1());
    }

    @Test
    public void updateModelWithGoalsConnectedToThreeCoursePartsSetGoalsCorrectForCourse() {

        HashMap<JTextField, JRadioButton[]> goalsMap = panel.getGoals();
        setUppThreeGoals(goalsMap);

        // connect course parts 1 and 2 to first goal
        setButtonSelected(goalsMap, 0, 0);
        setButtonSelected(goalsMap, 0, 1);

        // connect course part 1 to second goal
        setButtonSelected(goalsMap, 1, 0);

        // connect course part 3 to third goal
        setButtonSelected(goalsMap, 2, 2);

        expectedResultController.updateModel();

        assertEquals(3, course.getGoals().size());

        Goal firstGoal = course.getGoals().get(0);
        assertEquals("First goal text info", firstGoal.getGoal());
        assertEquals(2, firstGoal.getCourseParts().size());
        assertEquals("Course part 1", firstGoal.getCourseParts().get(0).getName());
        assertEquals("Course part 2", firstGoal.getCourseParts().get(1).getName());

        Goal secondGoal = course.getGoals().get(1);
        assertEquals("Second goal text info", secondGoal.getGoal());
        assertEquals(1, secondGoal.getCourseParts().size());
        assertEquals("Course part 1", secondGoal.getCourseParts().get(0).getName());

        Goal thirdGoal = course.getGoals().get(2);
        assertEquals("Third goal text info", thirdGoal.getGoal());
        assertEquals(1, thirdGoal.getCourseParts().size());
        assertEquals("Course part 3", thirdGoal.getCourseParts().get(0).getName());

    }

    @Test
    public void updateModelWithAllGoalsConnectedToAllCoursePartsSetGoalsCorrectForCourse() {

        JCheckBox isConnectedToAll = panel.getIsConnectedToAll();
        isConnectedToAll.setSelected(true);
        HashMap<JTextField, JRadioButton[]> goalsMap = panel.getGoals();
        setUppThreeGoals(goalsMap);

        expectedResultController.updateModel();

        assertEquals(3, course.getGoals().size());

        Goal firstGoal = course.getGoals().get(0);
        assertEquals("First goal text info", firstGoal.getGoal());
        assertEquals(3, firstGoal.getCourseParts().size());
        assertEquals("Course part 1", firstGoal.getCourseParts().get(0).getName());
        assertEquals("Course part 2", firstGoal.getCourseParts().get(1).getName());
        assertEquals("Course part 3", firstGoal.getCourseParts().get(2).getName());

        Goal secondGoal = course.getGoals().get(1);
        assertEquals("Second goal text info", secondGoal.getGoal());
        assertEquals(3, secondGoal.getCourseParts().size());
        assertEquals("Course part 1", secondGoal.getCourseParts().get(0).getName());
        assertEquals("Course part 2", secondGoal.getCourseParts().get(1).getName());
        assertEquals("Course part 3", secondGoal.getCourseParts().get(2).getName());

        Goal thirdGoal = course.getGoals().get(2);
        assertEquals("Third goal text info", thirdGoal.getGoal());
        assertEquals(3, thirdGoal.getCourseParts().size());
        assertEquals("Course part 1", thirdGoal.getCourseParts().get(0).getName());
        assertEquals("Course part 2", thirdGoal.getCourseParts().get(1).getName());
        assertEquals("Course part 3", thirdGoal.getCourseParts().get(2).getName());

    }

    @Test
    public void updateModelForCourseWithNoCoursePartsSetGoalsCorrectForCourse() {

        course.setCourseParts(new ArrayList<>());

        HashMap<JTextField, JRadioButton[]> goalsMap = panel.getGoals();
        setUppThreeGoals(goalsMap);

        expectedResultController.updateModel();

        assertEquals(3, course.getGoals().size());

        Goal firstGoal = course.getGoals().get(0);
        assertEquals("First goal text info", firstGoal.getGoal());
        assertEquals(0, firstGoal.getCourseParts().size());

        Goal secondGoal = course.getGoals().get(1);
        assertEquals("Second goal text info", secondGoal.getGoal());
        assertEquals(0, secondGoal.getCourseParts().size());

        Goal thirdGoal = course.getGoals().get(2);
        assertEquals("Third goal text info", thirdGoal.getGoal());
        assertEquals(0, thirdGoal.getCourseParts().size());

    }

    private void setUppThreeGoals(HashMap<JTextField, JRadioButton[]> goalsMap) {
        // set text fields for three goals
        List<JTextField> jTextFields = new ArrayList<>(goalsMap.keySet());
        jTextFields.get(0).setText("First goal text info");;
        jTextFields.get(1).setText("Second goal text info");
        jTextFields.get(2).setText("Third goal text info");
    }

    private void setButtonSelected(HashMap<JTextField, JRadioButton[]> goalsMap, int panelIndex, int buttonIndex) {
        List<JTextField> jTextFields = new ArrayList<>(goalsMap.keySet());
        JTextField jTextField = jTextFields.get(panelIndex);
        JRadioButton[] jRadioButtons = goalsMap.get(jTextField);
        JRadioButton button = jRadioButtons[buttonIndex];
        button.setSelected(true);
        button.setVisible(true);
    }

}