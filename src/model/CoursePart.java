package model;

import java.util.*;

public class CoursePart {
	String name;
	double credits;
	String examination;
	ArrayList<String> gradingScale;
	
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