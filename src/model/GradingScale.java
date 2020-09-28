package model;

import java.util.*;

public class GradingScale {
	
	HashMap<Integer,ArrayList<String>> gradingScaleMap = new HashMap<Integer, ArrayList<String>>();
	
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
		three.add("VG");
		three.add("G");
		three.add("U");
	
		// ArrayList of G-U
		ArrayList<String> two = new ArrayList<String>();
		two.add("G");
		two.add("U"); 
	
		// Insert grading scales into gradingScaleMap
		gradingScaleMap.put(7, seven);
		gradingScaleMap.put(3, three);
		gradingScaleMap.put(2, two);
	}
	
	// Constructor
	public GradingScale() {
		createGradingScales();
	}
	
	/**
	 * Returns one of the three grading scales
	 * depending on the input x (int).
	 * 
	 * x has to be either 7, 3 or 2.
	 */
	public ArrayList<String> getGradingScale(int x) {
		ArrayList<String> scale = gradingScaleMap.get(x);
		return scale;
	}
}
