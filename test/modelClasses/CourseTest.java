// Code written by Rahman Ali
// Last changed 14/10/2020
package modelClasses;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import model.*;
import org.junit.jupiter.api.BeforeAll;

public class CourseTest {
	
	static Course c = new Course("testkurs", 7.5, "TE101");
	
	@BeforeAll
	static void init() {
		
		// mock-up Course
		c.setDistance(true);
		c.setLanguage("svenska");
		c.setPrintGoalsAlt1(true);
		c.setCourseContentText("kursinnehåll");
		c.setTeaching("teachingTestString");
		c.setThesis(true);
		c.setThesisSupervisedHours("thesisSupervisedHourseTestString");
		c.setCanChangeSupervisor(true);
		c.setExamination("examinationTestString");
		c.setHomeExam(true);
		c.setLateHomeExamNotExamined(true);
		c.setExaminationPartiallyInEnglish(false);
		c.setExaminationInEnglish(false);
		c.setAttendanceRequired(true);
		c.setDistanceAttendanceText("distanceAttendanceTestString");
		c.setNotDistanceAttendanceText("notDistanceAttendanceTextTestString");
		c.setTotalGradeFromAllParts(true);
		c.setTotalGradeFromSomeParts(true);
		c.setTotalGradeAlt3Text("totalGradeAlt3TextTestString");
		c.setOtherActivitiesAffectGrade(true);
		c.setOtherActivitiesThatAffectGrade("otherActivitiesThatAffectGradeTestString");
		c.setSupplementsAllowed(true);
		c.setSupplementAlternative(3);
	}
	
	
	@Test
	void testSettersAndGetters() {
		
		assertTrue(
				c.getName().equals("testkurs") &&
				c.getCredits() == 7.5 &&
				c.getCode().equals("TE101") &&
				c.isDistance() == true &&
				c.getLanguage().equals("svenska") &&
				c.getPrintGoalsAlt1() == true &&
				c.getCourseContentText().equals("kursinnehåll") &&
				c.getTeaching().equals("teachingTestString") &&
				c.hasThesis() == true &&
				c.getThesisSupervisedHours().equals("thesisSupervisedHourseTestString") &&
				c.getCanChangeSupervisor() == true &&
				c.getExamination().equals("examinationTestString") &&
				c.hasHomeExam() == true &&
				c.isLateHomeExamNotExamined() == true &&
				c.isExaminationPartiallyInEnglish() == false &&
				c.isExaminationInEnglish() == false &&
				c.isAttendanceRequired() == true &&
				c.getDisanceAttendanceText().equals("distanceAttendanceTestString") &&
				c.getNotDistanceAttendanceText().equals("notDistanceAttendanceTextTestString") &&
				c.isTotalGradeFromAllParts() == true &&
				c.isTotalGradeFromSomeParts() == true &&
				c.getTotalGradeAlt3Text().equals("totalGradeAlt3TextTestString") &&
				c.getOtherActivitiesAffectGrade() == true &&
				c.getOtherActivitiesThatAffectGrade().equals("otherActivitiesThatAffectGradeTestString") &&
				c.areSupplementsAllowed() == true &&
				c.getSupplementAlternative() == 3);
	}
	
	
	@Test
	void testSetCourseParts() {
		
		// making sure courseParts in c is empty before the test.
		ArrayList<CoursePart> emptyPartsArray = new ArrayList<CoursePart>(); 
		c.setCourseParts(emptyPartsArray);
		
		CoursePart cp1 = new CoursePart();
		CoursePart cp2 = new CoursePart();
		ArrayList<CoursePart> cpartsTest = new ArrayList<CoursePart>();
		cpartsTest.add(cp1);
		cpartsTest.add(cp2);
		
		c.setCourseParts(cpartsTest);
		
		//getCourseParts() is also tested here.
		assertTrue(c.getCourseParts().equals(cpartsTest));	
	}
	
	
	@Test
	void testAddCoursePart() {
		
		// making sure courseParts in c is empty before the test.
		ArrayList<CoursePart> emptyPartsArray = new ArrayList<CoursePart>(); 
		c.setCourseParts(emptyPartsArray);
		
		CoursePart cp1 = new CoursePart();
		ArrayList<CoursePart> cpartsTest = new ArrayList<CoursePart>();
		cpartsTest.add(cp1);
		
		c.addCoursePart(cp1);
		
		assertTrue(c.getCourseParts().equals(cpartsTest));
	}
	
	
	@Test
	void testRemoveCoursePart() {
		
		// making sure courseParts in c is empty before the test.
		ArrayList<CoursePart> emptyPartsArray = new ArrayList<CoursePart>(); 
		c.setCourseParts(emptyPartsArray);
		
		CoursePart cp1 = new CoursePart();
		CoursePart cp2 = new CoursePart();
		ArrayList<CoursePart> cpartsTest = new ArrayList<CoursePart>();
		cpartsTest.add(cp1);
		cpartsTest.add(cp2);
		
		c.setCourseParts(cpartsTest);
		c.removeCoursePart(cp1);
		cpartsTest.remove(cp1);
		
		assertTrue(c.getCourseParts().equals(cpartsTest));
	}
	
	
	@Test
	void testSwapCourseParts() throws Exception {
		
		// making sure courseParts in c is empty before the test.
		ArrayList<CoursePart> emptyPartsArray = new ArrayList<CoursePart>();
		c.setCourseParts(emptyPartsArray);
		
		CoursePart cp1 = new CoursePart();
		CoursePart cp2 = new CoursePart();
		ArrayList<CoursePart> cpartsTest = new ArrayList<CoursePart>();
		cpartsTest.add(cp1);
		cpartsTest.add(cp2);
		c.setCourseParts(cpartsTest);
		
		//1st element of courseParts should be cp1.
		assertTrue(c.getCourseParts().get(0).equals(cp1));
		
		//swapping cp1 and cp2.
		c.swapCourseParts(cp1, cp2);
		//1st element should now be cp2.
		assertTrue(c.getCourseParts().get(0).equals(cp2));
	}
	
	
	@Test
	void testSetGradingScale() {
		
		GradingScale g = new GradingScale();
		ArrayList<String> sjugradigG = g.getGradingScale(7);
		
		c.setGradingScale(sjugradigG);
		
		//getGradingScale() is also tested here.
		assertTrue(c.getGradingScale().equals(sjugradigG));
	}
	
	
	@Test
	void testSetGoals() {
		
		// making sure Goals in c is empty before the test.
		ArrayList<Goal> emptyGoalArray = new ArrayList<Goal>();
		c.setGoals(emptyGoalArray);
		
		Goal go1 = new Goal();
		Goal go2 = new Goal();
		ArrayList<Goal> goalsTest = new ArrayList<Goal>();
		goalsTest.add(go1);
		goalsTest.add(go2);
		c.setGoals(goalsTest);
		
		//getGoals() is also tested here.
		assertTrue(c.getGoals().equals(goalsTest));
	}

}
