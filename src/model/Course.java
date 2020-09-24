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
	
	public ArrayList<String> getGradingScale() {
		return gradingScale;
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
	
	// Basic setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setDistance(boolean distance) {
		this.distance = distance;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	// Container setters
	
	public void addCoursePart(CoursePart part) {
		courseParts.add(part);
	}
	
	public boolean removeCoursePart(CoursePart part) {
		return courseParts.remove(part);
	}
	
	public void setGradingScale(ArrayList<String> gradingScale) {
		this.gradingScale = gradingScale;
	}
	
	public void addGoal(Goal goal) {
		this.goal.add(goal);
	}
	
	public boolean removeGoal(Goal goal) {
		return this.goal.remove(goal);
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

class Goal {
	String name;
	ArrayList<CoursePart> parts;
}