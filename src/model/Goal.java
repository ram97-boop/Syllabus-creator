package model;

import java.util.*; 

public class Goal {
	// Description of this goal.
	String description;
	
	// The course parts which this goal belongs to.
	ArrayList<CoursePart> parts;
	
	// Constructor
	public Goal() {
	}
	
	
	// Getters
	/**
	 * Returns this goal's description.
	 * @return
	 */
	public String getGoal() {
		return this.description;
	}
	
	/**
	 * Returns an ArrayList of the course parts which
	 * this goal belongs to.
	 * @return
	 */
	public ArrayList<CoursePart> getCourseParts() {
		return this.parts;
	}
	
	
	// Setters
	/**
	 * Gives this goal a description.
	 * @param g
	 */
	public void describeGoal(String g) {
		this.description = g;
	}
	
	/**
	 * Adds a course part that has this goal
	 * into an ArrayList.
	 * @param p
	 */
	public void addCoursePart(CoursePart p) {
		this.parts.add(p);
	}
	
}
