import static org.junit.Assert.*;

import org.junit.*;
import java.lang.Math;

import main.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class LIC1Test {
	
    /*
	There exists at least one set of three consecutive data points that 
    cannot all be contained within or on a circle of radius RADIUS1.
	(0 ≤ RADIUS1)
	
	TO-DO
	This is not finished... Im having test issues and wanted tu rush smallestCircle in master.
	 */
	@Before
	public void before() {
		Decide.PARAMETERS = new Parameters();
	}

	
	@Test
	public void test1() {
		Decide.NUM_POINTS = 5;
        Decide.PARAMETERS.radius1 = 3;

        //expect infinity and true
		Decide.X = new double[]{0, 1, 2, 3, 4};
        Decide.Y = new double[]{1, 2, 3, 4, 5};
        assertEquals(Decide.LIC1(), true);
	}

	@Test
	public void test2() {
		Decide.NUM_POINTS = 5;
        Decide.PARAMETERS.radius1= 15;

        //expect false
		Decide.X = new double[]{10, 5, 8, 15, 6};
        Decide.Y = new double[]{5, 8, 11, 13, 15};
        assertEquals(Decide.LIC1(), false);
	}
}
