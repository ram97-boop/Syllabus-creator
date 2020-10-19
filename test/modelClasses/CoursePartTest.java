// Code written by Rahman Ali
// Last changed 14/10/2020
package modelClasses;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import model.*;
import org.junit.jupiter.api.BeforeAll;

public class CoursePartTest {
	
	static CoursePart cp = new CoursePart();
	
	@BeforeAll
	static void init() {
		cp.setName("kursDelNamn");
		cp.setEngName("coursePartName");
		cp.setCredits(2.5);
		cp.setExamination("examinationTestString");
		ArrayList<String> g = new ArrayList<String>();
		g.add("gradingScaleTestString");
		cp.setGradingScale(g);
		cp.setDecidesTotalGrade(true);
	}
	
	
	@Test
	void testSettersAndGetters() {
		
		assertTrue(
				cp.getName().equals("kursDelNamn") &&
				cp.getEngName().equals("coursePartName") &&
				cp.getCredits() == 2.5 &&
				cp.getExamination().equals("examinationTestString") &&
				cp.getGradingScale().get(0).equals("gradingScaleTestString") &&
				cp.getDecidesTotalGrade() == true);
	}

}
