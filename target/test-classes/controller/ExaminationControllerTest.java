package controller;

import View.ExaminationPanel;
import model.Course;
import model.CoursePart;
import model.GradingScale;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ExaminationControllerTest {

    private final Course course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
    private ExaminationController examinationController;
    private final ExaminationPanel panel = new ExaminationPanel();

    private final ArrayList<CoursePart> parts = new ArrayList<>();
    private final CoursePart part1 = new CoursePart();
    private final CoursePart part2 = new CoursePart();
    private final CoursePart part3 = new CoursePart();

    @Before
    public void setUp(){
        examinationController = new ExaminationController(course, panel);

        part1.setName("Course part 1");
        part1.setCredits(2.5);
        part2.setName("Course part 2");
        part2.setCredits(3);
        part3.setName("Course part 3");
        part3.setCredits(2.0);

        parts.add(part1);
        parts.add(part2);
        parts.add(part3);

    }

    @Test
    public void updateModelForCourseWithThreeCoursePartsShouldResultInExaminationAndGradingScaleAreSetCorrectForAllParts() {
        // add three course parts to course
        course.setCourseParts(parts);

        setUpThreeExaminationTextFields();
        setUpThreeGradingScales();

        examinationController.updateModel();

        CoursePart firstPart = course.getCourseParts().get(0);
        ArrayList<String> firstPartGradingScale = firstPart.getGradingScale();
        assertEquals(3, firstPartGradingScale.size());
        assertEquals("V", firstPartGradingScale.get(0).substring(0, 1));
        assertEquals("G", firstPartGradingScale.get(1).substring(0, 1));
        assertEquals("U", firstPartGradingScale.get(2).substring(0, 1));
        assertEquals("Examination 1", firstPart.getExamination());

        CoursePart secondPart = course.getCourseParts().get(1);
        ArrayList<String> secondPartGradingScale = secondPart.getGradingScale();
        assertEquals(7, secondPartGradingScale.size());
        assertEquals("A", secondPartGradingScale.get(0).substring(0, 1));
        assertEquals("B", secondPartGradingScale.get(1).substring(0, 1));
        assertEquals("C", secondPartGradingScale.get(2).substring(0, 1));
        assertEquals("D", secondPartGradingScale.get(3).substring(0, 1));
        assertEquals("E", secondPartGradingScale.get(4).substring(0, 1));
        assertEquals("Fx", secondPartGradingScale.get(5).substring(0, 2));
        assertEquals("F", secondPartGradingScale.get(6).substring(0, 1));
        assertEquals("Examination 2", secondPart.getExamination());

        CoursePart thirdPart = course.getCourseParts().get(2);
        ArrayList<String> thirdPartGradingScale = thirdPart.getGradingScale();
        assertEquals(2, thirdPartGradingScale.size());
        assertEquals("G", thirdPartGradingScale.get(0).substring(0, 1));
        assertEquals("U", thirdPartGradingScale.get(1).substring(0, 1));
        assertEquals("Examination 3", thirdPart.getExamination());

    }

    @Test
    public void updateModelForCourseWithNoCoursePartsShouldResultInNoAttributesAreSet() {

        // add empty course part to course
        course.setCourseParts(new ArrayList<>());

        examinationController.updateModel();

        assertEquals(0, course.getCourseParts().size());

    }

    private void setUpThreeExaminationTextFields() {
        JTextField[] examinationFields = panel.getExaminationFields();
        examinationFields[0].setText("Examination 1");
        examinationFields[1].setText("Examination 2");
        examinationFields[2].setText("Examination 3");
    }

    private void setUpThreeGradingScales() {
        ArrayList<JComboBox<String>> gradingScales = panel.getGradingScales();
        gradingScales.get(0).setSelectedItem(GradingScale.getGradingScaleStrings()[1]); // VG-U
        gradingScales.get(1).setSelectedItem(GradingScale.getGradingScaleStrings()[0]); // A-F
        gradingScales.get(2).setSelectedItem(GradingScale.getGradingScaleStrings()[2]); // G-U
    }

}
