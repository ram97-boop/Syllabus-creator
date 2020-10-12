package model;

import java.util.*;

public class Course {
	
	// General attributes
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
	boolean supplementsAllowed; // Whether supplements are allowed during examination
	int supplementAlternative; // Types of supplements allowed
	
	// Literature
	String institution; // Where the course is taught
	boolean multipleInstitutions; // Whether there are multiple insitutions involved
	boolean printMultipleInstitutionsAlt1;

	public Course() {
		
	}
	
	public Course(String name,double credits,String code) {
		setName(name);
		setCredits(credits);
		setCode(code);
	}
	
	/**
	 * Swaps two elements of courseParts.
	 * @param part0 - First part to swap.
	 * @param part1 - Second part to swap.
	 * @throws Exception if either part does not exist in courseParts.
	 */
	public void swapCourseParts(CoursePart part0, CoursePart part1) throws Exception {
		CoursePart[] parts = {part0,part1};
		int[] index = {courseParts.indexOf(part0),courseParts.indexOf(part1)};
		
		for (int i = 0; i <= 1; i++) {
			if (index[i] == -1) {
				throw new Exception(parts[i].name + " (param " + Integer.toString(i + 1) + ") not found in courseParts.");
			}
		}
		
		courseParts.set(index[0], part1);
		courseParts.set(index[1], part0);
	}
	
	
	
	// Getters
	
	public String getName() {return name;}
	public String getEngName() {return engName;}
	public double getCredits() {return credits;}
	public String getCode() {return code;}
	public ArrayList<CoursePart> getCourseParts() {return courseParts;}
	public ArrayList<String> getGradingScale() {return gradingScale;}
	public boolean isDistance() {return distance;}
	public String getLanguage() {return language;}

	public ArrayList<Goal> getGoals() {return goals;}
	public boolean getPrintGoalsAlt1() {return printGoalsAlt1;}

	public String getContent() {return content;}
	public String getCourseContentText() {return courseContentText;}
	public String getTeaching() {return teaching;}
	public boolean hasThesis() {return thesis;}
	public String getThesisSupervisedHours() {return thesisSupervisedHours;}
	public boolean getCanChangeSupervisor() {return canChangeSupervisor;}
	
	public String getExamination() {return examination;}
	public boolean hasHomeExam() {return homeExam;}
	public boolean isLateHomeExamNotExamined() {return lateHomeExamNotExamined;}
	public boolean isExaminationPartiallyInEnglish() {return examinationPartiallyInEnglish;}
	public boolean isExaminationInEnglish() {return examinationInEnglish;}
	public boolean isAttendanceRequired() {return attendanceRequired;}
	public String getDisanceAttendanceText() {return distanceAttendanceText;}
	public String getNotDistanceAttendanceText() {return notDistanceAttendanceText;}
	public boolean isTotalGradeFromAllParts() {return totalGradeFromAllParts;}
	public boolean isTotalGradeFromSomeParts() {return totalGradeFromSomeParts;}
	public String getTotalGradeAlt3Text() {return totalGradeAlt3Text;}
	public boolean getOtherActivitiesAffectGrade() {return otherActivitiesAffectGrade;}
	public String getOtherActivitiesThatAffectGrade() {return otherActivitiesThatAffectGrade;}
	public boolean areSupplementsAllowed() {return supplementsAllowed;}
	public int getSupplementAlternative() {return supplementAlternative;}
	
	public String getInstitution() {return institution;}
	public boolean hasMultipleInstitutions() {return multipleInstitutions;}
	public boolean getPrintMultipleInstitutionsAlt1() {return printMultipleInstitutionsAlt1;}
	
	
	
	// Basic setters
	
	public void setName(String name) {this.name = name;}
	public void setEngName(String engName) {this.engName = engName;}
	public void setCredits(double credits) {this.credits = credits;}
	public void setCode(String code) {this.code = code;}
	public void setDistance(boolean distance) {this.distance = distance;}
	public void setLanguage(String language) {this.language = language;}
	
	public void setPrintGoalsAlt1(boolean printGoalsAlt1) {this.printGoalsAlt1 = printGoalsAlt1;}

	public void setContent(String content) {this.content = content;}
	public void setCourseContentText(String courseContentText) {this.courseContentText = courseContentText;}
	public void setTeaching(String teaching) {this.teaching = teaching;}
	public void setThesis(boolean thesis) {this.thesis = thesis;}
	public void setThesisSupervisedHours(String supervisedHours) {this.thesisSupervisedHours = supervisedHours;}
	public void setCanChangeSupervisor(boolean canChangeSupervisor) {this.canChangeSupervisor = canChangeSupervisor;}
	
	public void setExamination(String examination) {this.examination = examination;}
	public void setHomeExam(boolean homeExam) {this.homeExam = homeExam;}
	public void setLateHomeExamNotExamined(boolean notExamined) {this.lateHomeExamNotExamined = notExamined;}
	public void setExaminationPartiallyInEnglish(boolean partEng) {this.examinationPartiallyInEnglish = partEng;}
	public void setExaminationInEnglish(boolean fullEng) {this.examinationInEnglish = fullEng;}
	public void setAttendanceRequired(boolean attendanceRequired) {this.attendanceRequired = attendanceRequired;}
	public void setDistanceAttendanceText(String distText) {this.distanceAttendanceText = distText;}
	public void setNotDistanceAttendanceText(String notDistText) {this.notDistanceAttendanceText = notDistText;}
	public void setTotalGradeFromAllParts(boolean fromAllParts) {this.totalGradeFromAllParts = fromAllParts;}
	public void setTotalGradeFromSomeParts(boolean fromSomeParts) {this.totalGradeFromSomeParts = fromSomeParts;}
	public void setTotalGradeAlt3Text(String totalGradeAlt3Text) {this.totalGradeAlt3Text = totalGradeAlt3Text;}
	public void setOtherActivitiesAffectGrade(boolean otherAffected) {this.otherActivitiesAffectGrade = otherAffected;}
	public void setOtherActivitiesThatAffectGrade(String others) {this.otherActivitiesThatAffectGrade = others;}
	public void setSupplementsAllowed(boolean supplementsAllowed) {this.supplementsAllowed = supplementsAllowed;}
	public void setSupplementAlternative(int supplementAlternative) {this.supplementAlternative = supplementAlternative;}
	
	public void setInstitution(String institution) {this.institution = institution;}
	public void setMultipleInstitutions(boolean multipleInstitutions) {this.multipleInstitutions = multipleInstitutions;}
	public void setPrintMultipleInstitutionsAlt1(boolean printAlt1) {this.printMultipleInstitutionsAlt1 = printAlt1;}
	
	
	
	// Container setters
	
	public void setCourseParts(ArrayList<CoursePart> parts) {courseParts = parts;}
	public void addCoursePart(CoursePart part) {courseParts.add(part);}
	public boolean removeCoursePart(CoursePart part) {return courseParts.remove(part);}
	
	public void setGradingScale(ArrayList<String> gradingScale) {this.gradingScale = gradingScale;}
	
	public void setGoals(ArrayList<Goal> goals) {this.goals = goals;}
	public void addGoal(Goal goal) {goals.add(goal);}
	public boolean removeGoal(Goal goal) {return goals.remove(goal);}
	
}