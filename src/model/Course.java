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
	
	public GradingScale() {
		
	}
	
	public Map.Entry<Integer,ArrayList<String>> getGradingSet() {
		return null;
	}
	// Return type is a placeholder likewise
}

class Goal {
	String name;
	ArrayList<CoursePart> parts;
}