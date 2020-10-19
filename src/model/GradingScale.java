package model;

import java.util.*;

public class GradingScale {
	
	private HashMap<Integer,ArrayList<String>> gradingScaleMap = new HashMap<Integer, ArrayList<String>>();
	
	// An array of strings that the user will see
	// when choosing a grading scale.
	private static String[] gradingScaleStrings = {
			"sjugradig m�lrelaterad skala",
			"tregradig m�lrelaterad skala",
			"tv�gradig m�lrelaterad skala"
	};
	
	private static String[] longGradingScaleStrings = {
		"sjugradig m�lrelaterad skala",
		"tregradig skala underk�nd (U), godk�nd (G), v�l godk�nd (VG)",
		"tv�gradig betygsskala: underk�nd (U) eller godk�nd (G)"
	};
	
	private void createGradingScales() {
		// ArrayList of A-F grading scale.
		ArrayList<String> seven = new ArrayList<String>();
		seven.add("A = Utm�rkt");
		seven.add("B = Mycket bra");
		seven.add("C = Bra");
		seven.add("D = Tillfredsst�llande");
		seven.add("E = Tillr�ckligt");
		seven.add("Fx = Underk�nd, n�got mer arbete kr�vs");
		seven.add("F = Underk�nd, mycket mer arbete kr�vs");
	
		// ArrayList of VG-G-U
		ArrayList<String> three = new ArrayList<String>();
		three.add("V = V�l godk�nd");
		three.add("G = Godk�nd");
		three.add("U = Underk�nd");
	
		// ArrayList of G-U
		ArrayList<String> two = new ArrayList<String>();
		two.add("G = Tillfredsst�llande");
		two.add("U = Underk�nd");
	
		// Insert grading scales into gradingScaleMap
		gradingScaleMap.put(7, seven);
		gradingScaleMap.put(3, three);
		gradingScaleMap.put(2, two);
	}
	
	// Constructor
	public GradingScale() {
		createGradingScales();
	}
	
	// Getters
	public static String[] getGradingScaleStrings() {
		return gradingScaleStrings;
	}
	
	public static String[] getLongGradingScaleStrings() {
		return longGradingScaleStrings;
	}
	
	
	/**
	 * Returns one of the three grading scales
	 * depending on the input x (int).
	 * 
	 * x has to be either 7, 3 or 2.
	 */
	public ArrayList<String> getGradingScale(int x) {
		return gradingScaleMap.get(x);
	}
	
	
	/**
	 * A second method for getting a grading scale,
	 * but this one uses the strings in gradingScaleStrings
	 * and/or the strings in longGradingScaleStrings as input.
	 * 
	 * @param choice
	 * @return
	 */
	public ArrayList<String> userGetGradingScale(String choice) {
		if (choice.equals(gradingScaleStrings[0]) || choice.equals(longGradingScaleStrings[0])) {
			return gradingScaleMap.get(7);
		}
		else if (choice.equals(gradingScaleStrings[1]) || choice.equals(longGradingScaleStrings[1])) {
			return gradingScaleMap.get(3);
		}
		else if (choice.equals(gradingScaleStrings[2]) || choice.equals(longGradingScaleStrings[2])) {
			return gradingScaleMap.get(2);
		}
		else return null;
	}
}