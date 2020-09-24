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
}