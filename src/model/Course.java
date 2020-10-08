package model;

import java.util.*;

public class Course {
	
	// General
	String name; // Name of the course
	String engName; // English name of the course
	double credits; // Credits earned from completing the course
	String code; // The course's code
	ArrayList<CoursePart> courseParts = new ArrayList<CoursePart>(); // Parts that the course consists of
	ArrayList<String> gradingScale; // 7-grade, 3-grade or 2-grade scale used for the course
	boolean distance; // Whether the course is taught on campus or from a distance
	String language; // The language the course is taught in
	
	// Expected results
	ArrayList<Goal> goals = new ArrayList<Goal>(); // List of expected results from going the course
	boolean printGoalsAlt1;
	
	// Content, teaching
	String content; // Content covered in the course
	String courseContentText; // Description of the course content
	String teaching; // How the course content is covered
	boolean thesis; // Whether this course has a thesis or not
	String thesisSupervisedHours; // Number of hours alotted to thesis supervision
	boolean canChangeSupervisor; // Whether changing supervisor is allowed
	
	// Examination
	String examination; // The method of examination used for a course not divided into parts
	boolean homeExam; // Whether examination is done from a distance
	boolean lateHomeExamNotExamined; // Whether late home exam hand-ins are accepted
	boolean examinationPartiallyInEnglish; // Whether the examination is partially done in english
	boolean examinationInEnglish; // Whether the examination is fully done in english
	boolean attendanceRequired; // Whether students are required to attend to pass
	String distanceAttendanceText;
	String notDistanceAttendanceText;
	boolean totalGradeFromAllParts; // Whether the total grade is determined from the grading on all parts
	boolean totalGradeFromSomeParts; // Whether the total grade is determined from the grading on some parts
	String totalGradeAlt3Text;
	boolean otherActivitiesAffectGrade; // Whether the grade is affected by other activities specified below
	String otherActivitiesThatAffectGrade; // Other activities that affect affect grade
	boolean supplementsAllowed; // Whether additional supplements are allowed during examination
	int supplementAlternative; // Types of supplements allowed

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
	
	public String getContent() {
		return content;
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
	
	public void setContent(String content) {
		this.content = content;
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
	
}