package modelClasses;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import model.Goal;
import model.CoursePart;
import org.junit.jupiter.api.Test;

class GoalTest {
	Goal g = new Goal();
	String testDescription = "This is a test goal.";
	CoursePart testC1 = new CoursePart();
	CoursePart testC2 = new CoursePart();
	ArrayList<CoursePart> testCourseParts = new ArrayList<CoursePart>();
	
	
	@Test
	void testDescribeGoal() {
		g.describeGoal(testDescription);
		assertEquals(testDescription, g.getGoal());
	}
	
	@Test
	void testAddCoursePart() {
		testC1.setName("testCoursePart1");
		g.addCoursePart(testC1);
		assertEquals("testCoursePart1", testC1.getName());
	}
	
	@Test
	void testSetCourseParts() {
		testC2.setName("testCoursePart2");
		testCourseParts.add(testC1);
		testCourseParts.add(testC2);
		g.setCourseParts(testCourseParts);
		
		assertEquals(testCourseParts, g.getCourseParts());
	}
	
	@Test
	void testGetGoal() {
		g.describeGoal(testDescription);
		assertEquals(testDescription, g.getGoal());
	}
	
	@Test
	void testGetCourseParts() {
		
	}
}
