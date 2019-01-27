package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LIC10Test {

	/**
	 * The LIC should be satisfied iff there exist a set of three consecutive points, separated by exactly E PTS and F PTS consecutive intervening points,
	 * respectively, which form a triangle whose area is greater than AREA1.
	 * In this first test we check that the LIC returns true only if the area is strictly greater than area1.
	 */
	@Test
	void testClassicTrue() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,15,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,5,0,0,0,0,0};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 30;
		p.e_pts=2;
		p.f_pts=2;
		Decide.PARAMETERS = p;
		assertTrue(Decide.LIC10());
	}
	/**
	 * In this test, no triangle has an area greater than area1 and the test returns false.
	 */
	@Test
	void testClassicFalse() {
		Decide.NUM_POINTS = 10;
		double x[] = {0,2,4,6,8,10,12,14,16,18};
		double y[] = {0,0,0,0,5,0,0,0,0,0};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 30;
		p.e_pts=2;
		p.f_pts=2;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC10());
	}
	/**
	 * Here we check that LIC10() returns false when there aren't enough points.
	 */
	@Test
	void notEnoughPoints() {
		Decide.NUM_POINTS = 4;
		double x[] = {5,10,15,20};
		double y[] = {0,2,4,6};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 25;
		p.e_pts=2;
		p.f_pts=2;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC10());
	}
	/**
	 * Here we check that the LIC returns false when e_pts < 1.
	 */
	@Test
	void ePtsWrong() {
		Decide.NUM_POINTS = 8;
		double x[] = {5,10,15,20,25,30,35,40};
		double y[] = {0,2,4,6,8,10,12,14};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 25;
		p.e_pts=0;
		p.f_pts=2;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC10());
	}
	/**
	 * Here we check that the LIC returns false when f_pts < 1.
	 */
	@Test
	void fPtsWrong() {
		Decide.NUM_POINTS = 8;
		double x[] = {5,10,15,20,25,30,35,40};
		double y[] = {0,2,4,6,8,10,12,14};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 25;
		p.e_pts=1;
		p.f_pts=-1;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC10());
	}
	/**
	 * Here we check that the LIC returns false when e_pts+f_pts > NUM_POINTS-3.
	 */
	@Test
	void sumOfPointsWrong() {
		Decide.NUM_POINTS = 8;
		double x[] = {5,10,15,20,25,30,35,40};
		double y[] = {0,2,4,6,8,10,12,14};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 25;
		p.e_pts=4;
		p.f_pts=3;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC10());
	}
	/**
	 * We check that when incorrect data is entered, the method throws the right exception.
	 * In this case we give a NUM_POINTS higher than the real number of points.
	 * We expect an IndexOutOfBoundsException.
	 */
	@Test()
	void throwsIndexOutOfBoundsException() {
		Decide.NUM_POINTS = 8;
		double x[] = {5,10,15};
		double y[] = {0,2,4};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 200;
		p.e_pts=2;
		p.f_pts=2;
		Decide.PARAMETERS = p;
		assertThrows(IndexOutOfBoundsException.class,()->{Decide.LIC10();});
	}
}
