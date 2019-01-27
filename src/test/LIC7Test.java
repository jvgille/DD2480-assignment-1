package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LIC7Test {

  /*
  LIC is met iff there exist at least one set of 2 data points, separated by exactly K_PTS
  consecutive intervening points, that are a distance greater than LENGHT1 apart.
  Length1 >= 0
  K_PTS >=1
  K_PTS <= NUM_POINTS - 2
  */

  @Before
	public void before() {
		Decide.PARAMETERS = new Parameters();
	}

  //Test edge-case where NUM_POINTS < 3, should return false
  @Test
  public void edgetest1(){
    Decide.NUM_POINTS = 2;
    Decide.PARAMETERS.k_pts = 1;
    Decide.X = new double[]{1.0,2.0};
    Decide.Y = new double[]{1.0,2.0};
    assertFalse(Decide.LIC7());
  }

  //Test case where kpts is max
  @Test
  public void edgetest2(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = Decide.NUM_POINTS-2;
    Decide.PARAMETERS.length1 = 1;
    Decide.X = new double[]{1.0,1.02,1.03,1.04,3.4};
    Decide.Y = new double[]{1.0,1.02,1.03,1.04,2.8};
    //should be true since first and last point has distance greater than 1
    //if not there is some mistake in the size of the steps
    assertTrue(Decide.LIC7());
  }

  //true test; where there exist a pair of points (1.0,1.0) and (3.0,3.0) that are farther than
  //length1 apart
  @Test
  public void testpos(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = 1;
    Decide.PARAMETERS.length1 = 2;
    Decide.X = new double[]{1.0,1.02,3.0,1.04,1.05};
    Decide.Y = new double[]{1.0,1.02,3.0,1.04,1.05};
    assertTrue(Decide.LIC7());
  }

  //false test; where there does not exist a pair of points (with kpts between them)
  // that are farther than length1 apart
  @Test
  public void testfal(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = 1;
    Decide.PARAMETERS.length1 = 2;
    Decide.X = new double[]{1.0,3.0,1.1,3.1,1.05};
    Decide.Y = new double[]{1.0,3.0,1.1,3.1,1.05};
    assertFalse(Decide.LIC7());
  }



}
