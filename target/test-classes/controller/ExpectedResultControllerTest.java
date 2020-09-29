/*
Created by: Sofia Ayata Karbin
 */

package controller;

import View.ExpectedResultPanel;
import model.Course;
import model.CoursePart;
import model.Goal;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ExpectedResultControllerTest {

    private final Course course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
    private ExpectedResultController expectedResultController;
    private ExpectedResultPanel panel;

    private final ArrayList<CoursePart> parts = new ArrayList<>();
    private final CoursePart part1 = new CoursePart();
    private final CoursePart part2 = new CoursePart();
    private final CoursePart part3 = new CoursePart();

    @Before
    public void setUp(){
        expectedResultController = new ExpectedResultController(course, ExpectedResultPanel.getInstance());
        panel = expectedResultController.getPanel();

        Arrays.stream(panel.getGoalPanels()).forEach(jPanel -> {
            JPanel buttonPanel = (JPanel) jPanel.getComponent(1);
            for (int i=0; i<8; i++) {
                JRadioButton button = (JRadioButton) buttonPanel.getComponent(i);
                button.setVisible(false);
                button.setSelected(false);
            }
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
    public void updateModelWithGoalsConnectedToThreeCoursePartsSetGoalsCorrectForCourse() {

        JPanel[] goalPanels = panel.getGoalPanels();
        setUppThreeGoals(goalPanels);

        // connect course parts 1 and 2 to first goal
        setButtonSelected(goalPanels, 0, 0);
        setButtonSelected(goalPanels, 0, 1);

        // connect course part 1 to second goal
        setButtonSelected(goalPanels, 1, 0);

        // connect course part 3 to third goal
        setButtonSelected(goalPanels, 2, 2);

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
        JPanel[] goalPanels = panel.getGoalPanels();
        setUppThreeGoals(goalPanels);

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

        JPanel[] goalPanels = panel.getGoalPanels();
        setUppThreeGoals(goalPanels);

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

    private void setUppThreeGoals(JPanel[] goalPanels) {
        // set text fields for three goals
        ((JTextField)goalPanels[0].getComponent(0)).setText("First goal text info");
        ((JTextField)goalPanels[1].getComponent(0)).setText("Second goal text info");
        ((JTextField)goalPanels[2].getComponent(0)).setText("Third goal text info");
    }

    private void setButtonSelected(JPanel[] goalPanels, int panelIndex, int buttonIndex) {
        JRadioButton button = (JRadioButton) ((JPanel) goalPanels[panelIndex].getComponent(1)).getComponent(buttonIndex);
        button.setSelected(true);
        button.setVisible(true);
    }

}