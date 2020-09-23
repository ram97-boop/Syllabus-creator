package model;

import java.util.*;

public class Course {
	String name;
	double credits;
	String code;
	ArrayList<CoursePart> courseParts;
	Map.Entry<Integer,ArrayList<String>> gradingScale;
		// Placeholder, will need to be changed since Map.Entry cannot be extracted from a HashMap
	boolean distance;
	String language;
	ArrayList<Goal> goal;
	
	public Course() {
		
	}
}

class CoursePart {
	String name;
	double credits;
	String examination;
	Map.Entry<Integer,ArrayList<String>> gradingScale;
		// Placeholder, will need to be changed since Map.Entry cannot be extracted from a HashMap
}

class GradingScale {
	
	HashMap<Integer,ArrayList<String>> gradingScaleMap;
	
	// ArrayList of A-F grading scale.
	ArrayList<String> seven = new ArrayList<String>();
	seven.add("A = Utmärkt");
	seven.add("B = Mycket bra");
	seven.add("C = Bra");
	seven.add("D = Tillfredsställande");
	seven.add("E = Tillräckligt");
	seven.add("Fx = Underkänd, något mer arbete krävs");
	seven.add("F = Underkänd, mycker mer arbete krävs");
	
	// ArrayList of VG-G-U
	ArrayList<String> three = new ArrayList<String>();
	three.add("VG");
	three.add("G");
	three.add("U");
	
	// ArrayList of G-U
	ArrayList<String> two = new ArrayList<String>();
	two.add("G");
	two.add("U");
	
	// Insert grading scales into gradingScaleMap
	gradingScaleMap.put(7, seven);
	gradingScaleMap.put(3, three);
	gradingScaleMap.put(2, two);
	
	// Constructor
	public GradingScale() {
	}
	
//	public Map.Entry<Integer,ArrayList<String>> getGradingSet(int x) {
//		return null;
//	}
//	// Return type is a placeholder likewise
	
	public ArrayList<String> getGradingScale(int x) {
		ArrayList<String> scale = gradingScaleMap.get(x);
		return scale;
	}
}

class Goal {
	String name;
	ArrayList<CoursePart> parts;
}