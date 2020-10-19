// Code written by Rahman Ali
// Last changed 14/10/2020
package modelClasses;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import com.google.gson.*;
import org.junit.jupiter.api.Test;
import model.*;

public class FileManagementTest {
	
	Course c = new Course();
	
	@Test
	void saveCourseTest() throws IOException {
		
		// Setting up a mock-up Course. Can be printed out just to see.
		c.setName("testKurs");
		c.setCredits(7.5);
		c.setCode("TE0001");
		c.setDistance(false);
		c.setLanguage("svenska");
		CoursePart cp1 = new CoursePart();
		cp1.setName("testKursDel1");
		cp1.setEngName("testCoursePart1");
		cp1.setCredits(3.5);
		cp1.setExamination("testDelExamination1");
		GradingScale g = new GradingScale();
		cp1.setGradingScale(g.getGradingScale(2));
		CoursePart cp2 = new CoursePart();
		cp2.setName("testKursDel2");
		cp2.setEngName("TestCoursePart2");
		cp2.setCredits(4);
		cp2.setExamination("testDelExamination2");
		cp2.setGradingScale(g.getGradingScale(7));
		ArrayList<CoursePart> cParts = new ArrayList<CoursePart>();
		cParts.add(cp1);
		cParts.add(cp2);
		c.setCourseParts(cParts);
		c.setGradingScale(g.getGradingScale(7));
		Goal go1 = new Goal();
		go1.addCoursePart(cp1);
		go1.describeGoal("Testm�l1");
		Goal go2 = new Goal();
		go2.addCoursePart(cp2);
		go2.describeGoal("Testm�l2");
		ArrayList<Goal> goals = new ArrayList<Goal>();
		goals.add(go1);
		goals.add(go2);
		c.setGoals(goals);
		
		// Creating a JSON of c.
		Gson gson = new Gson();
		String cJSON = gson.toJson(c);
		
		// Using the saveCourse method of FileManagement.
		FileManagement f = new FileManagement();
		f.saveCourse(c, "testJSON.txt");
		
		// Reading the contents of the saved file.
		BufferedReader reader = new BufferedReader(new FileReader("testJSON.txt"));
		String cStringFromFile = reader.readLine();
		reader.close();
		
		// Deleting the test file.
		Files.delete(Paths.get("testJSON.txt"));
		
		// Comparing our created JSON of c to the JSON saved in the file.
		assertEquals(cJSON, cStringFromFile);
	}
	
	
	@Test
	void loadCourseTest() throws IOException {
		
		// Second mock-up.
		c.setName("testKurs2");
		c.setCredits(7.5);
		c.setCode("TE0001");
		c.setDistance(false);
		c.setLanguage("svenska");
		CoursePart cp1 = new CoursePart();
		cp1.setName("testKursDel1");
		cp1.setEngName("testCoursePart1");
		cp1.setCredits(3.5);
		cp1.setExamination("testDelExamination1");
		GradingScale g = new GradingScale();
		cp1.setGradingScale(g.getGradingScale(2));
		CoursePart cp2 = new CoursePart();
		cp2.setName("testKursDel2");
		cp2.setEngName("TestCoursePart2");
		cp2.setCredits(4);
		cp2.setExamination("testDelExamination2");
		cp2.setGradingScale(g.getGradingScale(7));
		ArrayList<CoursePart> cParts = new ArrayList<CoursePart>();
		cParts.add(cp1);
		cParts.add(cp2);
		c.setCourseParts(cParts);
		c.setGradingScale(g.getGradingScale(7));
		Goal go1 = new Goal();
		go1.addCoursePart(cp1);
		go1.describeGoal("Testm�l1");
		Goal go2 = new Goal();
		go2.addCoursePart(cp2);
		go2.describeGoal("Testm�l2");
		ArrayList<Goal> goals = new ArrayList<Goal>();
		goals.add(go1);
		goals.add(go2);
		c.setGoals(goals);
		
		// Creating a JSON of c.
		Gson gson = new Gson();
		String cJSON = gson.toJson(c);
		System.out.print(cJSON);
		
		// Saving cJSON into a file.
		Files.write(Paths.get("testJSON.txt"), cJSON.getBytes());
		
		// Retrieving c using the method loadCourse from FileManagement,
		// and converting it into a JSON string again.
		FileManagement f = new FileManagement();
		Course cFromFile = f.loadCourse("testJSON.txt");
		String cFromFileJSON = gson.toJson(cFromFile);
		
		//Deleting the test file.
		Files.delete(Paths.get("testJSON.txt"));
		
		// Comparing cJSON to cFromFileJSON.
		assertEquals(cJSON, cFromFileJSON);
	}
	
}
