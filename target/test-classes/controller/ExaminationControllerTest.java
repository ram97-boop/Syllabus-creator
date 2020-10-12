/*
Created by: Sofia Ayata Karbin
 */

package controller;

import View.ExaminationPanel;
import model.Course;
import model.CoursePart;
import model.GradingScale;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExaminationControllerTest {

    private Course course;
    private ExaminationController examinationController;
    private ExaminationPanel panel;

    private final ArrayList<CoursePart> parts = new ArrayList<>();
    private final CoursePart part1 = new CoursePart();
    private final CoursePart part2 = new CoursePart();
    private final CoursePart part3 = new CoursePart();
    private final CoursePart part4 = new CoursePart();
    private final CoursePart part5 = new CoursePart();

    @Before
    public void setUp(){
        course = new Course("Mjuvaruutveckling", 7.5, "DA4002");
        panel = new ExaminationPanel();
        examinationController = new ExaminationController(course, panel);

        part1.setName("Course part 1");
        part1.setCredits(2.5);
        part2.setName("Course part 2");
        part2.setCredits(3);
        part3.setName("Course part 3");
        part3.setCredits(2.0);
        part4.setName("Course part 4");
        part4.setCredits(1);
        part5.setName("Course part 5");
        part5.setCredits(1.5);

        parts.add(part1);
        parts.add(part2);
        parts.add(part3);

        panel.getHomeExamCheckBox().setSelected(false);
        panel.getExaminationOnEnglishCheckBox().setSelected(false);

    }

    @Test
    public void updateModelForCourseWithoutCoursePartsShouldResultInExaminationIsSet() {
        assertNull(course.getExamination());

        examinationController.updateModel();

        assertEquals("", course.getExamination());

        String expectedResult = "Written examination";
        panel.getExaminationPane().setText(expectedResult);

        examinationController.updateModel();

        assertEquals(expectedResult, course.getExamination());
    }

    @Test
    public void updateModelForCourseWithCoursePartsShouldResultInExaminationIsNull() {
        String expectedResult = "Written examination";
        panel.getExaminationPane().setText(expectedResult);

        course.setCourseParts(parts);

        assertNull(course.getExamination());

        examinationController.updateModel();

        assertNull(course.getExamination());
    }

    @Test
    public void updateModelWithGradingScaleAToFForCourseShouldResultInGradingScaleSetCorrectForCourse() {
        panel.getCourseGradingScaleComboBox().setSelectedItem(GradingScale.getGradingScaleStrings()[0]);

        assertNull(course.getGradingScale());

        examinationController.updateModel();

        ArrayList<String> courseGradingScale = course.getGradingScale();
        assertEquals(7, courseGradingScale.size());
        assertEquals("A", courseGradingScale.get(0).substring(0, 1));
        assertEquals("B", courseGradingScale.get(1).substring(0, 1));
        assertEquals("C", courseGradingScale.get(2).substring(0, 1));
        assertEquals("D", courseGradingScale.get(3).substring(0, 1));
        assertEquals("E", courseGradingScale.get(4).substring(0, 1));
        assertEquals("Fx", courseGradingScale.get(5).substring(0, 2));
        assertEquals("F", courseGradingScale.get(6).substring(0, 1));
    }

    @Test
    public void updateModelWithGradingScaleVGToUForCourseShouldResultInGradingScaleSetCorrectForCourse() {
        panel.getCourseGradingScaleComboBox().setSelectedItem(GradingScale.getGradingScaleStrings()[1]);

        assertNull(course.getGradingScale());

        examinationController.updateModel();

        ArrayList<String> courseGradingScale = course.getGradingScale();
        assertEquals(3, courseGradingScale.size());
        assertEquals("V", courseGradingScale.get(0).substring(0, 1));
        assertEquals("G", courseGradingScale.get(1).substring(0, 1));
        assertEquals("U", courseGradingScale.get(2).substring(0, 1));
    }

    @Test
    public void updateModelWithGradingScaleUToUForCourseShouldResultInGradingScaleSetCorrectForCourse() {
        panel.getCourseGradingScaleComboBox().setSelectedItem(GradingScale.getGradingScaleStrings()[2]);

        assertNull(course.getGradingScale());

        examinationController.updateModel();

        ArrayList<String> courseGradingScale = course.getGradingScale();
        assertEquals(2, courseGradingScale.size());
        assertEquals("G", courseGradingScale.get(0).substring(0, 1));
        assertEquals("U", courseGradingScale.get(1).substring(0, 1));
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
    public void updateModelForCourseWithFiveCoursePartsShouldResultInExaminationAndGradingScaleAreSetCorrectForAllParts() {
        // add three course parts to course
        ArrayList<CoursePart> courseParts = new ArrayList<>(parts);
        courseParts.add(part4);
        courseParts.add(part5);
        course.setCredits(10);
        course.setCourseParts(courseParts);

        setUpFiveExaminationTextFields();
        setUpFiveGradingScales();

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
        assertEquals(2, secondPartGradingScale.size());
        assertEquals("G", secondPartGradingScale.get(0).substring(0, 1));
        assertEquals("U", secondPartGradingScale.get(1).substring(0, 1));
        assertEquals("Examination 2", secondPart.getExamination());

        CoursePart thirdPart = course.getCourseParts().get(2);
        ArrayList<String> thirdPartGradingScale = thirdPart.getGradingScale();
        assertEquals(3, thirdPartGradingScale.size());
        assertEquals("V", thirdPartGradingScale.get(0).substring(0, 1));
        assertEquals("G", thirdPartGradingScale.get(1).substring(0, 1));
        assertEquals("U", thirdPartGradingScale.get(2).substring(0, 1));
        assertEquals("Examination 3", thirdPart.getExamination());

        CoursePart fourthPart = course.getCourseParts().get(3);
        ArrayList<String> fourthPartGradingScale = fourthPart.getGradingScale();
        assertEquals(2, fourthPartGradingScale.size());
        assertEquals("G", fourthPartGradingScale.get(0).substring(0, 1));
        assertEquals("U", fourthPartGradingScale.get(1).substring(0, 1));
        assertEquals("Examination 4", fourthPart.getExamination());

        CoursePart fifthPart = course.getCourseParts().get(4);
        ArrayList<String> fifthPartGradingScale = fifthPart.getGradingScale();
        assertEquals(7, fifthPartGradingScale.size());
        assertEquals("A", fifthPartGradingScale.get(0).substring(0, 1));
        assertEquals("B", fifthPartGradingScale.get(1).substring(0, 1));
        assertEquals("C", fifthPartGradingScale.get(2).substring(0, 1));
        assertEquals("D", fifthPartGradingScale.get(3).substring(0, 1));
        assertEquals("E", fifthPartGradingScale.get(4).substring(0, 1));
        assertEquals("Fx", fifthPartGradingScale.get(5).substring(0, 2));
        assertEquals("F", fifthPartGradingScale.get(6).substring(0, 1));
        assertEquals("Examination 5", fifthPart.getExamination());
    }

    @Test
    public void updateModelForCourseWithNoCoursePartsShouldResultInNoAttributesAreSet() {

        // add empty course part to course
        course.setCourseParts(new ArrayList<>());

        examinationController.updateModel();

        assertEquals(0, course.getCourseParts().size());

    }

    @Test
    public void courseWithoutHomeExamShouldResultInHomeExamAndLateHomeExamNotExaminedAreFalse() {
        assertFalse(course.hasHomeExam());
        assertFalse(course.isLateHomeExamNotExamined());

        examinationController.updateModel();

        assertFalse(course.hasHomeExam());
        assertFalse(course.isLateHomeExamNotExamined());
    }

    @Test
    public void courseWithHomeExamAndLateHomeExamMightBeExaminedShouldResultInHomeExamIsTrueAndLateHomeExamNotExaminedIsFalse() {
        panel.getHomeExamCheckBox().setSelected(true);
        panel.getHomeExamRadio1().setSelected(false);

        assertFalse(course.hasHomeExam());
        assertFalse(course.isLateHomeExamNotExamined());

        examinationController.updateModel();

        assertTrue(course.hasHomeExam());
        assertFalse(course.isLateHomeExamNotExamined());
    }

    @Test
    public void courseWithHomeExamAndLateHomeExamNotExaminedShouldResultInHomeExamAndLateHomeExamNotExaminedAreTrue() {
        panel.getHomeExamCheckBox().setSelected(true);
        panel.getHomeExamRadio1().setSelected(true);

        assertFalse(course.hasHomeExam());
        assertFalse(course.isLateHomeExamNotExamined());

        examinationController.updateModel();

        assertTrue(course.hasHomeExam());
        assertTrue(course.isLateHomeExamNotExamined());
    }

    @Test
    public void courseWithExaminationNotInEnglishShouldResultInExaminationInEnglishAndDefinitelyInEnglishAreFalse() {
        assertFalse(course.isExaminationPartiallyInEnglish());
        assertFalse(course.isExaminationInEnglish());

        examinationController.updateModel();

        assertFalse(course.isExaminationPartiallyInEnglish());
        assertFalse(course.isExaminationInEnglish());
    }

    @Test
    public void courseWithExaminationPartiallyInEnglishShouldResultInExaminationPartiallyInEnglishIsTrueAndDefinitelyInEnglishIsFalse() {
        panel.getExaminationOnEnglishCheckBox().setSelected(true);
        panel.getEnglishRadio1().setSelected(false);

        assertFalse(course.isExaminationPartiallyInEnglish());
        assertFalse(course.isExaminationInEnglish());

        examinationController.updateModel();

        assertTrue(course.isExaminationPartiallyInEnglish());
        assertFalse(course.isExaminationInEnglish());
    }

    @Test
    public void courseWithExaminationInEnglishShouldResultInExaminationPartiallyInEnglishAndDefinitelyInEnglishAreTrue() {
        panel.getExaminationOnEnglishCheckBox().setSelected(true);
        panel.getEnglishRadio1().setSelected(true);

        assertFalse(course.isExaminationPartiallyInEnglish());
        assertFalse(course.isExaminationInEnglish());

        examinationController.updateModel();

        assertTrue(course.isExaminationPartiallyInEnglish());
        assertTrue(course.isExaminationInEnglish());
    }

    @Test
    public void noAttendanceRequiredShouldResultInAttendanceRequiredFalseAndAttendanceTextsAreNull() {
        panel.getHasAttendanceCheckBox().setSelected(false);
        panel.getDistanceAttendancePane().setText("Seminar attendance");
        panel.getNotDistanceAttendancePane().setText("Presentation");

        assertFalse(course.isAttendanceRequired());
        assertNull(course.getDisanceAttendanceText());
        assertNull(course.getNotDistanceAttendanceText());

        examinationController.updateModel();

        assertFalse(course.isAttendanceRequired());
        assertNull(course.getDisanceAttendanceText());
        assertNull(course.getNotDistanceAttendanceText());
    }

    @Test
    public void attendanceRequiredForDistanceCourseShouldResultInAttendanceRequiredTrueAndDistanceAttendanceTextIsSetCorrect() {
        course.setDistance(true);

        panel.getHasAttendanceCheckBox().setSelected(true);
        panel.getDistanceAttendancePane().setText("Seminar attendance");
        panel.getNotDistanceAttendancePane().setText("Presentation");

        assertFalse(course.isAttendanceRequired());
        assertNull(course.getDisanceAttendanceText());
        assertNull(course.getNotDistanceAttendanceText());

        examinationController.updateModel();

        assertTrue(course.isAttendanceRequired());
        assertEquals("Seminar attendance", course.getDisanceAttendanceText());
        assertNull(course.getNotDistanceAttendanceText());
    }

    @Test
    public void attendanceRequiredForNotDistanceCourseShouldResultInAttendanceRequiredTrueAndNotDistanceAttendanceTextIsSetCorrect() {
        course.setDistance(false);

        panel.getHasAttendanceCheckBox().setSelected(true);
        panel.getDistanceAttendancePane().setText("Seminar attendance");
        panel.getNotDistanceAttendancePane().setText("Presentation");

        assertFalse(course.isAttendanceRequired());
        assertNull(course.getDisanceAttendanceText());
        assertNull(course.getNotDistanceAttendanceText());

        examinationController.updateModel();

        assertTrue(course.isAttendanceRequired());
        assertNull(course.getDisanceAttendanceText());
        assertEquals("Presentation", course.getNotDistanceAttendanceText());
    }

    @Test
    public void otherActivitiesNotAffectingGradeShouldResultInOtherActivitiesAffectingGradeBoolIsFalseAndStringIsNull() {

        panel.getOtherActivitiesCheckBox().setSelected(false);
        panel.getOtherActivitiesPane().setText("Active during seminar");

        assertFalse(course.getOtherActivitiesAffectGrade());
        assertNull(course.getOtherActivitiesThatAffectGrade());

        examinationController.updateModel();

        assertFalse(course.getOtherActivitiesAffectGrade());
        assertNull(course.getOtherActivitiesThatAffectGrade());

    }

    @Test
    public void otherActivitiesIsAffectingGradeShouldResultInOtherActivitiesAffectingGradeBoolIsTrueAndStringIsNotNull() {

        panel.getOtherActivitiesCheckBox().setSelected(true);
        panel.getOtherActivitiesPane().setText("Active during seminar");

        assertFalse(course.getOtherActivitiesAffectGrade());
        assertNull(course.getOtherActivitiesThatAffectGrade());

        examinationController.updateModel();

        assertTrue(course.getOtherActivitiesAffectGrade());
        assertEquals("Active during seminar", course.getOtherActivitiesThatAffectGrade());

    }

    @Test
    public void allPartsAffectingGradeShouldResultInTotalGradeFromAllPartsTrue() {
        ArrayList<CoursePart> courseParts = new ArrayList<>(parts);
        courseParts.add(part4);
        course.setCredits(8.5);
        course.setCourseParts(courseParts);

        panel.getTotalGradeRadio1().setSelected(true);
        panel.getTotalGradeRadio2().setSelected(false);
        panel.getTotalGradeAlt3TextPane().setText("Grade is only pass or fail");

        assertFalse(course.isTotalGradeFromAllParts());
        assertFalse(course.isTotalGradeFromSomeParts());
        assertNull(course.getTotalGradeAlt3Text());

        examinationController.updateModel();

        assertTrue(course.isTotalGradeFromAllParts());
        assertFalse(course.isTotalGradeFromSomeParts());
        assertNull(course.getTotalGradeAlt3Text());

        course.getCourseParts().forEach(part -> assertFalse(part.getDecidesTotalGrade()));
    }

    @Test
    public void gradeIsSetInOtherWayShouldResultInTotalGradeFromAllPartsAndSomePartsAreFalseAndAlt3TextIsNotNull() {
        ArrayList<CoursePart> courseParts = new ArrayList<>(parts);
        courseParts.add(part4);
        course.setCredits(8.5);
        course.setCourseParts(courseParts);

        panel.getTotalGradeRadio1().setSelected(false);
        panel.getTotalGradeRadio2().setSelected(false);
        panel.getTotalGradeAlt3TextPane().setText("Grade is only pass or fail");

        assertFalse(course.isTotalGradeFromAllParts());
        assertFalse(course.isTotalGradeFromSomeParts());
        assertNull(course.getTotalGradeAlt3Text());

        examinationController.updateModel();

        assertFalse(course.isTotalGradeFromAllParts());
        assertFalse(course.isTotalGradeFromSomeParts());
        assertEquals("Grade is only pass or fail", course.getTotalGradeAlt3Text());

        course.getCourseParts().forEach(part -> assertFalse(part.getDecidesTotalGrade()));
    }

    @Test
    public void somePartsAffectingGradeShouldResultInTotalGradeFromSomePartsTrueAndDecidesTotalGradeForEachPartIsSetCorrect() {
        ArrayList<CoursePart> courseParts = new ArrayList<>(parts);
        courseParts.add(part4);
        course.setCourseParts(courseParts);
        course.setCredits(8.5);

        setUpFourGradeCertainPartsRadios();
        panel.getTotalGradeRadio1().setSelected(false);
        panel.getTotalGradeRadio2().setSelected(true);
        panel.getTotalGradeRadio2().setVisible(true);
        panel.getTotalGradeAlt3TextPane().setText("Grade is only pass or fail");

        assertFalse(course.isTotalGradeFromAllParts());
        assertFalse(course.isTotalGradeFromSomeParts());
        assertNull(course.getTotalGradeAlt3Text());

        examinationController.updateModel();

        assertFalse(course.isTotalGradeFromAllParts());
        assertTrue(course.isTotalGradeFromSomeParts());
        assertNull(course.getTotalGradeAlt3Text());

        assertTrue(course.getCourseParts().get(0).getDecidesTotalGrade());
        assertFalse(course.getCourseParts().get(1).getDecidesTotalGrade());
        assertFalse(course.getCourseParts().get(2).getDecidesTotalGrade());
        assertTrue(course.getCourseParts().get(3).getDecidesTotalGrade());
    }

    private void setUpThreeExaminationTextFields() {
        JTextField[] examinationFields = panel.getExaminationFields();
        examinationFields[0].setText("Examination 1");
        examinationFields[1].setText("Examination 2");
        examinationFields[2].setText("Examination 3");
    }

    private void setUpFiveExaminationTextFields() {
        JTextField[] examinationFields = panel.getExaminationFields();
        examinationFields[0].setText("Examination 1");
        examinationFields[1].setText("Examination 2");
        examinationFields[2].setText("Examination 3");
        examinationFields[3].setText("Examination 4");
        examinationFields[4].setText("Examination 5");
    }

    private void setUpThreeGradingScales() {
        ArrayList<JComboBox<String>> gradingScales = panel.getGradingScales();
        gradingScales.get(0).setSelectedItem(GradingScale.getLongGradingScaleStrings()[1]); // (VG-U)
        gradingScales.get(1).setSelectedItem(GradingScale.getLongGradingScaleStrings()[0]); // A-F
        gradingScales.get(2).setSelectedItem(GradingScale.getLongGradingScaleStrings()[2]); // (G-U)
    }

    private void setUpFiveGradingScales() {
        ArrayList<JComboBox<String>> gradingScales = panel.getGradingScales();
        gradingScales.get(0).setSelectedItem(GradingScale.getLongGradingScaleStrings()[1]); // (VG-U)
        gradingScales.get(1).setSelectedItem(GradingScale.getLongGradingScaleStrings()[2]); // (G-U)
        gradingScales.get(2).setSelectedItem(GradingScale.getLongGradingScaleStrings()[1]); // (VG-U)
        gradingScales.get(3).setSelectedItem(GradingScale.getLongGradingScaleStrings()[2]); // (G-U)
        gradingScales.get(4).setSelectedItem(GradingScale.getLongGradingScaleStrings()[0]); // A-F
    }

    private void setUpFourGradeCertainPartsRadios() {
        JRadioButton[] gradeCertainPartsRadios = panel.getGradeCertainPartsRadios();

        // set part 1 and 4 to affect total grade
        gradeCertainPartsRadios[0].setVisible(true);
        gradeCertainPartsRadios[0].setSelected(true);
        gradeCertainPartsRadios[0].setText(part1.getName());

        gradeCertainPartsRadios[1].setVisible(true);
        gradeCertainPartsRadios[1].setSelected(false);
        gradeCertainPartsRadios[1].setText(part2.getName());

        gradeCertainPartsRadios[2].setVisible(true);
        gradeCertainPartsRadios[2].setSelected(false);
        gradeCertainPartsRadios[2].setText(part3.getName());

        gradeCertainPartsRadios[3].setVisible(true);
        gradeCertainPartsRadios[3].setSelected(true);
        gradeCertainPartsRadios[3].setText(part4.getName());

    }

}
