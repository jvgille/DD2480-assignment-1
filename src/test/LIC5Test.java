package decide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LIC5Test {

	@Test
	void testClassicFalse() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,15,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,0,0,0,0,0,0};
		Decide.X = x;
		Decide.Y = y;
		assertFalse(Decide.LIC5());
	}

    @Test
	void testClassicTrue() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,8,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,0,0,0,0,0,0};
		Decide.X = x;
		Decide.Y = y;
		assertTrue(Decide.LIC5());
	}

    @Test
	void testBorderCaseFalse() {
		Decide.NUM_POINTS = 10;
		double x[] = {1,1,2,2,3,3,4,4,5,5};
		double y[] = {1,2,3,4,5,6,7,8,9,0};
		Decide.X = x;
		Decide.Y = y;
		assertFalse(Decide.LIC5());
	}

    @Test
	void testBorderCaseTrue() {
		Decide.NUM_POINTS = 10;
		double x[] = {1,0.999999,2,2,3,3,4,4,5,5};
		double y[] = {1,2,3,4,5,6,7,8,9,0};
		Decide.X = x;
		Decide.Y = y;
		assertTrue(Decide.LIC5());
	}

    @Test
    void notEnoughPoints() {
        Decide.NUM_POINTS = 1;
        double x[] = {5};
        double y[] = {0};
        Decide.X = x;
        Decide.Y = y;
        assertFalse(Decide.LIC5());
    }

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
