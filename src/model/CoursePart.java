package model;

import java.util.*;

public class CoursePart {
	String name; // Name of this course part
	String engName; // English name of this course part
	double credits; // Credits tied to this course part
	String partCode; // Optional code of the course part
	String examination; // The method of examination used in this course part
	ArrayList<String> gradingScale; // 7-grade, 3-grade or 2-grade scale used for this course part
	boolean decidesTotalGrade; // Whether the parent Course's total grade is affected by this course part
	
	public CoursePart() {
		
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
	
	public String getPartCode() {
		return partCode;
	}
	
	public String getExamination() {
		return examination;
	}
	
	public ArrayList<String> getGradingScale() {
		return gradingScale;
	}
	
	public boolean getDecidesTotalGrade() {
		return decidesTotalGrade;
	}
	
	// Setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEngName(String engName) {
		this.engName = engName;
	}
	
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	
	public void setExamination(String examination) {
		this.examination = examination;
	}
	
	public void setGradingScale(ArrayList<String> gradingScale) {
		this.gradingScale = gradingScale;
	}
	
	public void setDecidesTotalGrade(boolean decidesTotalGrade) {
		this.decidesTotalGrade = decidesTotalGrade;
	}
}