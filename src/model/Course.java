package model;

import java.util.*;

public class Course {
	String name;
	double credits;
	String code;
	ArrayList<CoursePart> courseParts;
	ArrayList<String> gradingScale;
	boolean distance;
	String language;
	ArrayList<Goal> goal;
	
	public Course() {
		
	}
	
	public Course(String name,double credits,String code) {
		this.name = name;
		this.credits = credits;
		this.code = code;
	}
	
	// Getters
	
	public String getName() {
		return name;
	}
	
	public double getCredits() {
		return credits;
	}
	
	public String getCode() {
		return code;
	}
	
	public ArrayList<CoursePart> getCourseParts() {
		return courseParts;
	}
	
	public boolean isDistance() {
		return distance;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public ArrayList<Goal> getGoals() {
		return goal;
	}
}

class CoursePart {
	String name;
	double credits;
	String examination;
	Map.Entry<Integer,ArrayList<String>> gradingScale;
		// Placeholder, will need to be changed since Map.Entry cannot be extracted from a HashMap
	
	public CoursePart() {
		
	}
}

class GradingScale {
	HashMap<Integer,ArrayList<String>> gradingScaleMap;
	
	// Insert grading scales into gradingScaleMap here?
	
	
	// Constructor
	public GradingScale() {
		
	}
	
//	public Map.Entry<Integer,ArrayList<String>> getGradingSet(int x) {
//		return null;
//	}
//	// Return type is a placeholder likewise
	
	public ArrayList<String> getGradingScale(int x) {
		return gradingScaleMap.get(x);
	}
	
	// Fixed getGradingScale just so the program will compile. (Not normally my task)
}

class Goal {
	String name;
	ArrayList<CoursePart> parts;
}