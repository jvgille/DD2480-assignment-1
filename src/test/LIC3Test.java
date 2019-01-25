package decide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LIC3Test {

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
	
	@Test
	void testClassicFalse() {
		Decide.NUM_POINTS = 10;
		double x[] = {5,10,15,20,25,30,35,40,45,50};
		double y[] = {0,0,0,0,5,0,0,5,0,0};
		Decide.X = x;
		Decide.Y = y;
		Parameters p = new Parameters();
		p.area1 = 50;
		Decide.PARAMETERS = p;
		assertFalse(Decide.LIC3());
	}

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
