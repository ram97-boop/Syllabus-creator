package model;

import java.util.*;

public class CoursePart {
	String name; // Name of this course part
	double credits; // Credits tied to this course part
	String examination; // The method of examination used in this course part
	ArrayList<String> gradingScale; // 7-grade, 3-grade or 2-grade scale used for this course part
	
	public CoursePart() {
		
	}
	
	
	
	// Getters
	
	public String getName() {
		return name;
	}
	
	public double getCredits() {
		return credits;
	}
	
	public String getExamination() {
		return examination;
	}
	
	public ArrayList<String> getGradingScale() {
		return gradingScale;
	}
	
	// Setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public void setExamination(String examination) {
		this.examination = examination;
	}
	
	public void setGradingScale(ArrayList<String> gradingScale) {
		this.gradingScale = gradingScale;
	}
}