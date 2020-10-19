// Code written by Rahman Ali
// Last changed 14/10/2020
package modelClasses;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import model.GradingScale;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;

class GradingScaleTest {
	GradingScale g = new GradingScale();

	@Test
	void testGetGradingScaleStrings() {
		String[] gStrings = GradingScale.getGradingScaleStrings();
		
		assertSame(GradingScale.getGradingScaleStrings(),
				gStrings, 
				"getGradingScaleStrings() should return {\"7-gradig (A-F)\", ...}");
	}

	@Test
	void testGetLongGradingScaleStrings() {
		String[] gLongStrings = GradingScale.getLongGradingScaleStrings();
		
		assertSame(GradingScale.getLongGradingScaleStrings(),
				gLongStrings,
				"getLongGradingScaleStrings() should return {\"sjugradig målrelaterad skala.\", ...");
	}

	@Test
	void testGetGradingScale() {
		ArrayList<String> gScale7 = g.getGradingScale(7);
		ArrayList<String> gScale3 = g.getGradingScale(3);
		ArrayList<String> gScale2 = g.getGradingScale(2);
		
		assertTrue(gScale7.get(0) == "A = Utmärkt" &&
				gScale7.get(1) == "B = Mycket bra" &&
				gScale7.get(2) == "C = Bra" &&
				gScale7.get(3) == "D = Tillfredsställande" &&
				gScale7.get(4) == "E = Tillräckligt" &&
				gScale7.get(5) == "Fx = Underkänd, något mer arbete krävs" &&
				gScale7.get(6) == "F = Underkänd, mycket mer arbete krävs");
		
		assertTrue(gScale3.get(0) == "V = Väl godkänd" &&
				gScale3.get(1) == "G = Godkänd" &&
				gScale3.get(2) == "U = Underkänd");
		
		assertTrue(gScale2.get(0) == "G = Tillfredsställande" &&
				gScale2.get(1) == "U = Underkänd");
	}

	@Test
	void testUserGetGradingScale() {
		ArrayList<String> gScale7 = g.userGetGradingScale("sjugradig målrelaterad skala");
		ArrayList<String> gScale3 = g.userGetGradingScale("tregradig målrelaterad skala");
		ArrayList<String> gScale2 = g.userGetGradingScale("tvågradig målrelaterad skala");
		
		assertTrue(gScale7.get(0) == "A = Utmärkt" &&
				gScale7.get(1) == "B = Mycket bra" &&
				gScale7.get(2) == "C = Bra" &&
				gScale7.get(3) == "D = Tillfredsställande" &&
				gScale7.get(4) == "E = Tillräckligt" &&
				gScale7.get(5) == "Fx = Underkänd, något mer arbete krävs" &&
				gScale7.get(6) == "F = Underkänd, mycket mer arbete krävs");
		
		assertTrue(gScale3.get(0) == "V = Väl godkänd" &&
				gScale3.get(1) == "G = Godkänd" &&
				gScale3.get(2) == "U = Underkänd");
		
		assertTrue(gScale2.get(0) == "G = Tillfredsställande" &&
				gScale2.get(1) == "U = Underkänd");
		
		
		ArrayList<String> gScale7long = g.userGetGradingScale("sjugradig målrelaterad skala");
		ArrayList<String> gScale3long = g.userGetGradingScale("tregradig skala underkänd (U), godkänd (G), väl godkänd (VG)");
		ArrayList<String> gScale2long = g.userGetGradingScale("tvågradig betygsskala: underkänd (U) eller godkänd (G)");
		
		assertTrue(gScale7long.get(0) == "A = Utmärkt" &&
				gScale7long.get(1) == "B = Mycket bra" &&
				gScale7long.get(2) == "C = Bra" &&
				gScale7long.get(3) == "D = Tillfredsställande" &&
				gScale7long.get(4) == "E = Tillräckligt" &&
				gScale7long.get(5) == "Fx = Underkänd, något mer arbete krävs" &&
				gScale7long.get(6) == "F = Underkänd, mycket mer arbete krävs");
		
		assertTrue(gScale3long.get(0) == "V = Väl godkänd" &&
				gScale3long.get(1) == "G = Godkänd" &&
				gScale3long.get(2) == "U = Underkänd");
		
		assertTrue(gScale2long.get(0) == "G = Tillfredsställande" &&
				gScale2long.get(1) == "U = Underkänd");
	}

	
}