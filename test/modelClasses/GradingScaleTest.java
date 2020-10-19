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
				"getLongGradingScaleStrings() should return {\"sjugradig m�lrelaterad skala.\", ...");
	}

	@Test
	void testGetGradingScale() {
		ArrayList<String> gScale7 = g.getGradingScale(7);
		ArrayList<String> gScale3 = g.getGradingScale(3);
		ArrayList<String> gScale2 = g.getGradingScale(2);
		
		assertTrue(gScale7.get(0) == "A = Utm�rkt" &&
				gScale7.get(1) == "B = Mycket bra" &&
				gScale7.get(2) == "C = Bra" &&
				gScale7.get(3) == "D = Tillfredsst�llande" &&
				gScale7.get(4) == "E = Tillr�ckligt" &&
				gScale7.get(5) == "Fx = Underk�nd, n�got mer arbete kr�vs" &&
				gScale7.get(6) == "F = Underk�nd, mycket mer arbete kr�vs");
		
		assertTrue(gScale3.get(0) == "V = V�l godk�nd" &&
				gScale3.get(1) == "G = Godk�nd" &&
				gScale3.get(2) == "U = Underk�nd");
		
		assertTrue(gScale2.get(0) == "G = Tillfredsst�llande" &&
				gScale2.get(1) == "U = Underk�nd");
	}

	@Test
	void testUserGetGradingScale() {
		ArrayList<String> gScale7 = g.userGetGradingScale("sjugradig m�lrelaterad skala");
		ArrayList<String> gScale3 = g.userGetGradingScale("tregradig m�lrelaterad skala");
		ArrayList<String> gScale2 = g.userGetGradingScale("tv�gradig m�lrelaterad skala");
		
		assertTrue(gScale7.get(0) == "A = Utm�rkt" &&
				gScale7.get(1) == "B = Mycket bra" &&
				gScale7.get(2) == "C = Bra" &&
				gScale7.get(3) == "D = Tillfredsst�llande" &&
				gScale7.get(4) == "E = Tillr�ckligt" &&
				gScale7.get(5) == "Fx = Underk�nd, n�got mer arbete kr�vs" &&
				gScale7.get(6) == "F = Underk�nd, mycket mer arbete kr�vs");
		
		assertTrue(gScale3.get(0) == "V = V�l godk�nd" &&
				gScale3.get(1) == "G = Godk�nd" &&
				gScale3.get(2) == "U = Underk�nd");
		
		assertTrue(gScale2.get(0) == "G = Tillfredsst�llande" &&
				gScale2.get(1) == "U = Underk�nd");
		
		
		ArrayList<String> gScale7long = g.userGetGradingScale("sjugradig m�lrelaterad skala");
		ArrayList<String> gScale3long = g.userGetGradingScale("tregradig skala underk�nd (U), godk�nd (G), v�l godk�nd (VG)");
		ArrayList<String> gScale2long = g.userGetGradingScale("tv�gradig betygsskala: underk�nd (U) eller godk�nd (G)");
		
		assertTrue(gScale7long.get(0) == "A = Utm�rkt" &&
				gScale7long.get(1) == "B = Mycket bra" &&
				gScale7long.get(2) == "C = Bra" &&
				gScale7long.get(3) == "D = Tillfredsst�llande" &&
				gScale7long.get(4) == "E = Tillr�ckligt" &&
				gScale7long.get(5) == "Fx = Underk�nd, n�got mer arbete kr�vs" &&
				gScale7long.get(6) == "F = Underk�nd, mycket mer arbete kr�vs");
		
		assertTrue(gScale3long.get(0) == "V = V�l godk�nd" &&
				gScale3long.get(1) == "G = Godk�nd" &&
				gScale3long.get(2) == "U = Underk�nd");
		
		assertTrue(gScale2long.get(0) == "G = Tillfredsst�llande" &&
				gScale2long.get(1) == "U = Underk�nd");
	}

}