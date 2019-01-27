package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LIC5Test {

	/**
	 * The LIC should be satisfied if there exists at least two consecutive data points (X[i],Y[i]) and (X[j],Y[j]), 
	 * such that X[j] - X[i] < 0. (where i = j-1)
	 * The first four tests are basic tests with values which work or not and some border cases.
	 * This one has only incrementing Xs so it should return false.
	 */
	@Test
	void testClassicFalse() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,15,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,0,0,0,0,0,0};
		Decide.X = x;
		Decide.Y = y;
		assertFalse(Decide.LIC5());
	}
	/**
	 * This test should return true as X[2]>X[3]
	 */
    @Test
	void testClassicTrue() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,8,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,0,0,0,0,0,0};
		Decide.X = x;
		Decide.Y = y;
		assertTrue(Decide.LIC5());
	}
    /**
     * This test checks border cases when values are equal. it should return false.
     */
    @Test
	void testBorderCaseFalse() {
		Decide.NUM_POINTS = 10;
		double x[] = {1,1,2,2,3,3,4,4,5,5};
		double y[] = {1,2,3,4,5,6,7,8,9,0};
		Decide.X = x;
		Decide.Y = y;
		assertFalse(Decide.LIC5());
	}
    /**
     * Here we check that LIC5() returns false when there aren't enough points.
     */
    @Test
    void notEnoughPoints() {
        Decide.NUM_POINTS = 1;
        double x[] = {5};
        double y[] = {0};
        Decide.X = x;
        Decide.Y = y;
        assertFalse(Decide.LIC5());
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
		assertThrows(IndexOutOfBoundsException.class,()->{Decide.LIC5();});
	}
}

