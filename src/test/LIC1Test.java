package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.*;

public class LIC1Test {
	
    /*
	There exists at least one set of three consecutive data points that 
    cannot all be contained within or on a circle of radius RADIUS1.
	(0 ≤ RADIUS1)
	
	TO-DO
	This is not finished... Im having test issues and wanted tu rush smallestCircle in master.
	 */
	@Test
	public void test1() {
		Parameters p = new Parameters();
        double[] x = { 0, 1, 2, 3, 4 }; // coordinates of our points
        double[] y = { 0, 1, 2, 3, 4 };
		Decide.NUM_POINTS = x.length;
        Decide.X = x;
		Decide.Y = y;
		p.radius1 = -1;
		Decide.PARAMETERS = p;
		
        assertFalse(Decide.LIC1()); //should fail (radius<0)
	}

	@Test
	public void test2() {
		Parameters p = new Parameters();
        double[] x = { 1, 6, 3, 4 }; // coordinates of our points
        double[] y = { 1, 2, 9, 4 };
		Decide.NUM_POINTS = x.length;
        Decide.X = x;
		Decide.Y = y;
		p.radius1 = 6;
		Decide.PARAMETERS = p;
		
		assertTrue(Decide.LIC1()); //should pass.
	}
}
