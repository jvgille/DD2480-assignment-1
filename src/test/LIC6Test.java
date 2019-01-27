package main;

import static org.junit.Assert.*;

import org.junit.*;
import java.lang.Math;

public class LIC6Test {

	@Before
	public void before() {
		Decide.PARAMETERS = new Parameters();
	}

	/**
	A line is drawn between the first and last points of the set.
	The LIC is satisfied iff there's a point in the set further than
	dist units away from the line.
	 */
	@Test
	public void test_distance_to_line() {
		Decide.NUM_POINTS = 5;
		Decide.X = new double[]{0, 1, 1, 1, 2};
		Decide.Y = new double[]{0, 1, 2, 3, 0};
		Decide.PARAMETERS.dist = 3.5;
		Decide.PARAMETERS.n_pts = 5;
		assertFalse(Decide.LIC6());
		Decide.PARAMETERS.dist = 2.5;
		assertTrue(Decide.LIC6());
		Decide.PARAMETERS.n_pts = 4;
		Decide.PARAMETERS.dist = 1;
		assertTrue(Decide.LIC6());
		Decide.PARAMETERS.n_pts = 3;
		Decide.PARAMETERS.dist = 1.1;
		assertFalse(Decide.LIC6());

		Decide.X = new double[]{0, 1, 2, 3, 4, 5, 6};
		Decide.Y = new double[]{0, 1, 0, 0, 0, 0, 0};
		Decide.PARAMETERS.dist = 0.9;
		Decide.PARAMETERS.n_pts = 3;
		assertTrue(Decide.LIC6());
		Decide.PARAMETERS.dist = 2;
		assertFalse(Decide.LIC6());
	}

	/**
	If the first and last point of the set coincide,
	the LIC is satisfied iff there's a point in the set further than
	dist units away from the coinciding points
	 */
	@Test
	public void test_distance_to_point() {
		Decide.NUM_POINTS = 5;
		Decide.X = new double[]{0, 1, 2, 3, 0};
		Decide.Y = new double[]{0, 0, 0, 0, 0};
		Decide.PARAMETERS.dist = 3.5;
		Decide.PARAMETERS.n_pts = 5;
		assertFalse(Decide.LIC6());
		Decide.PARAMETERS.dist = 2.5;
		assertTrue(Decide.LIC6());

		Decide.NUM_POINTS = 7;
		Decide.X = new double[]{0, 1, 0, 1, 0, 1, 0};
		Decide.Y = new double[]{0, 0, 0, 0, 0, 0, 0};
		Decide.PARAMETERS.dist = 0.9;
		Decide.PARAMETERS.n_pts = 3;
		assertTrue(Decide.LIC6());
		Decide.PARAMETERS.dist = 2;
		assertFalse(Decide.LIC6());
		Decide.PARAMETERS.n_pts = 5;
		assertFalse(Decide.LIC6());
	}
}
