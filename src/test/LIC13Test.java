package main;

import static org.junit.Assert.*;

import org.junit.*;
import java.lang.Math;

public class LIC13Test {

	@Before
	public void before() {
		Decide.PARAMETERS = new Parameters();
	}

    /**
    Decide.determinant() should calculate the determinant of the given matrix.
    */
	@Test
	public void test_determinant() {
        double epsilon = 0.00001;
		assertEquals(0, Decide.determinant(1,2,3,4,5,6,7,8,9), epsilon);
		assertEquals(-208, Decide.determinant(2,9,1,8,4,5,6,3,7), epsilon);
		assertEquals(237804, Decide.determinant(23,91,13,87,42,54,64,63,5), epsilon);
	}

    /**
    Decide.smallestCircle() should return the radius of the smallest circle which contains all the points.
    */
	@Test
	public void test_smallest_circle() {
        double epsilon = 0.00001;

        // one point inside circle
        Point[] points = new Point[]{new Point(0,0), new Point(1.1,0.9), new Point(2,2)};
		assertEquals(Math.sqrt(2), Decide.smallestCircle(points), epsilon);

        // all points on perimeter
        points = new Point[]{new Point(0,0), new Point(1,0), new Point(0.5, 1.2071)};
        assertEquals(1.0/Math.sqrt(2), Decide.smallestCircle(points), epsilon);
	}

    /**
    The LIC should not be fulfilled when NUMPOINTS < 5.
    */
	@Test
	public void test_numpoints() {
        Decide.PARAMETERS.a_pts = 0;
        Decide.PARAMETERS.b_pts = 0;
        Decide.PARAMETERS.radius1 = 0.5;
        Decide.PARAMETERS.radius2 = 0.15;
        Decide.NUM_POINTS = 4;
		Decide.X = new double[]{0, 1, 1.1, 1.2};
		Decide.Y = new double[]{0, 0, 0, 0};

        assertFalse(Decide.LIC13());
	}

    /**
    The LIC should not be fulfilled when only the first condition is met.
    */
	@Test
	public void test_first_condition_only() {
        Decide.PARAMETERS.a_pts = 0;
        Decide.PARAMETERS.b_pts = 0;
        Decide.PARAMETERS.radius1 = 0.9;
        Decide.PARAMETERS.radius2 = 0.15;
        Decide.NUM_POINTS = 5;
		Decide.X = new double[]{0, 1, 2, 3, 4};
		Decide.Y = new double[]{0, 0, 0, 0, 0};

        assertFalse(Decide.LIC13());
	}

    /**
    The LIC should not be fulfilled when only the second condition is met.
    */
	@Test
	public void test_second_condition_only() {
        Decide.PARAMETERS.a_pts = 0;
        Decide.PARAMETERS.b_pts = 0;
        Decide.PARAMETERS.radius1 = 1.1;
        Decide.PARAMETERS.radius2 = 1.1;
        Decide.NUM_POINTS = 5;
		Decide.X = new double[]{0, 1, 2, 3, 4};
		Decide.Y = new double[]{0, 0, 0, 0, 0};

        assertFalse(Decide.LIC13());
	}

    /**
    The LIC should be fulfilled when both conditions are met.
    */
	@Test
	public void test_positive() {
        Decide.PARAMETERS.a_pts = 0;
        Decide.PARAMETERS.b_pts = 0;
        Decide.PARAMETERS.radius1 = 0.9;
        Decide.PARAMETERS.radius2 = 0.6;
        Decide.NUM_POINTS = 5;
		Decide.X = new double[]{0, 1, 2, 2.5, 3};
		Decide.Y = new double[]{0, 0, 0, 0, 0};

        assertTrue(Decide.LIC13());
	}

    /**
    Positive test using different a_pts and b_pts.
    */
	@Test
	public void test_pts() {
        Decide.PARAMETERS.a_pts = 1;
        Decide.PARAMETERS.b_pts = 1;
        Decide.PARAMETERS.radius1 = 0.9;
        Decide.PARAMETERS.radius2 = 0.6;
        Decide.NUM_POINTS = 6;
		Decide.X = new double[]{0, 0, 0, 0, 0, 0};
		Decide.Y = new double[]{0, 3, 1, 3.5, 2, 4};

        assertTrue(Decide.LIC13());
	}
}
