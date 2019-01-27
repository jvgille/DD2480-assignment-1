package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.*;

public class TestLic0 {

		//intitialize PARAMETERS
		@Before
		public void before() {
		Decide.PARAMETERS = new Parameters();
		//euclidian distance between these points is one
		double[] x = {1.0,2.0,3.0};
		double[] y = {0.0,0.0,0.0};
		Decide.X = x;
		Decide.Y = y;
		}

		/*
		LIC 0 is met iff there exists at least one set of 2 consecutive data points that are a distance
		greater than LENGTH1 apart. LENGTH1 >= 0.
		*
		*/

		//Test edge case LENGT1 == 0.
    @Test
    public void testedge1() {
			Decide.PARAMETERS.length1 = 0.0;
			//should assert to true as long as there are more than one point (with different coordinates)
			assertTrue(Decide.LIC0());
    }

		//Test edge case LENGT1 == 0.
		@Test
		public void testedge2() {
			Decide.PARAMETERS.length1 = 0.0;
			//should assert to false since there is only one point)
			Decide.X = new double[]{1.0};
			Decide.Y = new double[]{1.0};
			assertFalse(Decide.LIC0());
		}

		//Test edge-case LEGTH1 == infinity
    @Test
		public void testedge3() {
			Decide.PARAMETERS.length1 = Double.MAX_VALUE;
			//should aöways be false
			Decide.X = new double[]{1.0,99.9,45.9,123.2345};
			Decide.Y = new double[]{1.0,239488.78743,234.21,98495858.2834774};
			assertFalse(Decide.LIC0());
		}

		//Positive test, should be true since the points are further than LENGTH1 apart
		@Test
		public void testpos() {
			Decide.PARAMETERS.length1 = 1;
			Decide.X = new double[]{1.0,1.9,4.0};
			Decide.Y = new double[]{1.0,1.1,4.0};
			assertTrue(Decide.LIC0());
		}
		//Negative test, should be false since no points are further than Length1 apart.
		@Test
		public void testfal() {
			Decide.PARAMETERS.length1 = 1;
			Decide.X = new double[]{1.0,1.5,2.4};
			Decide.Y = new double[]{0.0,0.0,0.0};
			assertFalse(Decide.LIC0());
		}
}
