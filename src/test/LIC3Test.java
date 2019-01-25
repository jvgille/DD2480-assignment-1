package decide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LIC3Test {

	/**
	 * The LIC should be satisfied iff there exist a set of three consecutive points which form a triangle whose area is greater than AREA1.
	 * In this first test we check that the LIC returns true only if the area is strictly greater than area1.
	 */
	@Test
	void testClassicTrue() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,15,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,5,0,0,10,0,0};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 25;
		Decide.PARAMETERS = p;
		assertTrue(Decide.LIC3());
	}
	/**
	 * In this test, no triangle has an area greater than area1 and the test returns false.
	 */
	@Test
	void testClassicFalse() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,15,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,5,0,0,5,0,0};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 25;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC3());
	}
	/**
	 * Here we check that LIC3() returns false when there aren't enough points.
	 */
	@Test
	void notEnoughPoints() {
		Decide.NUM_POINTS = 2;
		double x[] = {5,10};
		double y[] = {0,2};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 25;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC3());
	}
	/**
	 * We check that when incorrect data is entered, the method throws the right exception.
	 * In this case we give a NUM_POINTS higher than the real number of points.
	 * We expect an IndexOutOfBoundsException.
	 */
	@Test()
	void throwsIndexOutOfBoundsException() {
		Decide.NUM_POINTS = 4;
		double x[] = {5,10,15};
		double y[] = {0,2,4};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 200;
		Decide.PARAMETERS = p;
		assertThrows(IndexOutOfBoundsException.class,()->{Decide.LIC3();});
	}
	/**
	 * In this case we give a negative area1.
	 * The assertion in the beginning of the method should return an AssertError.
	 */
	@Test()
	void assertErrorWithNegativeArea() {
		Decide.NUM_POINTS = 3;
		double x[] = {5,10,15};
		double y[] = {0,2,4};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = -2;
		Decide.PARAMETERS = p;
		assertThrows(AssertionError.class,()->{Decide.LIC3();});
	}
}

