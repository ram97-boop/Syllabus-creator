package model;

import java.util.*;

public class Course {
	String name;
	double credits;
	String code;
	ArrayList<CoursePart> courseParts = new ArrayList<CoursePart>();
	ArrayList<String> gradingScale;
	boolean distance;
	String language;
	ArrayList<Goal> goal = new ArrayList<Goal>();
	
	public Course() {
		
	}
	
	public Course(String name,double credits,String code) {
		setName(name);
		setCredits(credits);
		setCode(code);
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
	
	public CoursePart addCoursePart() {
		CoursePart part = new CoursePart();
		courseParts.add(part);
		return part;
	}
	
	public boolean removeCoursePart(CoursePart part) {
		return courseParts.remove(part);
	}
	
	public void setGradingScale(ArrayList<String> gradingScale) {
		this.gradingScale = gradingScale;
	}
	
	public Goal addGoal() {
		Goal newGoal = new Goal();
		goal.add(newGoal);
		return newGoal;
	}
	
	public boolean removeGoal(Goal goal) {
		return this.goal.remove(goal);
	}
}