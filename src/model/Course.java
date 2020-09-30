package model;

import View.CoursePanel;
import java.util.*;

public class Course {
	String name; // Name of the course
	String engName; // English name of the course
	double credits; // Credits earned from completing the course
	String code; // The course's code
	ArrayList<CoursePart> courseParts = new ArrayList<CoursePart>(); // Parts that the course consists of
	ArrayList<String> gradingScale; // 7-grade, 3-grade or 2-grade scale used for the course
	boolean distance; // Whether the course is taught on campus or from a distance
	String language; // The language the course is taught in
	ArrayList<Goal> goals = new ArrayList<Goal>(); // List of expected results from going the course
	boolean thesis; // Whether this course has a thesis or not
	CoursePanel[] panels;
	
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
	
	public String getEngName() {
		return engName;
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
		return goals;
	}
	
	public boolean hasThesis() {
		return thesis;
	}
	
	public CoursePanel[] getCoursePanels() {
		return panels;
	}
	
	// Basic setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEngName(String engName) {
		this.engName = engName;
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
	
	public void setThesis(boolean thesis) {
		this.thesis = thesis;
	}
	
	// Container setters
	
	public void setCourseParts(ArrayList<CoursePart> parts) {
		courseParts = parts;
	}
	
	public void addCoursePart(CoursePart part) {
		courseParts.add(part);
	}
	
	public boolean removeCoursePart(CoursePart part) {
		return courseParts.remove(part);
	}
	
	public void setGradingScale(ArrayList<String> gradingScale) {
		this.gradingScale = gradingScale;
	}
	
	public void setGoals(ArrayList<Goal> goals) {
		this.goals = goals;
	}
	
	public void addGoal(Goal goal) {
		goals.add(goal);
	}
	
	public boolean removeGoal(Goal goal) {
		return goals.remove(goal);
	}
	
	public void setCoursePanels(CoursePanel[] panels) {
		this.panels = panels;
	}
}