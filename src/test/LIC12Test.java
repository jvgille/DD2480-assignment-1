package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LIC12Test {

  /*
  LIC met if both of the following statements are true;
  * There are 2 points (at least) separated by exactly k_pts, which are a distance greater than length1 apart.
  * There are 2 points (could be same as above, could be other points) separated by exactly k_pts, that are less than
    length2 apart.
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
    assertFalse(Decide.LIC12());
  }

  //Test case where kpts is max
  @Test
  public void edgetest2(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = Decide.NUM_POINTS-2;
    Decide.PARAMETERS.length1 = 1;
    Decide.PARAMETERS.length2 = 100;
    Decide.X = new double[]{1.0,1.02,1.03,1.04,3.4};
    Decide.Y = new double[]{1.0,1.02,1.03,1.04,2.8};
    //should be true since first and last point has distance greater than 1 and less than 100
    //if not there is some mistake in the size of the steps
    assertTrue(Decide.LIC12());
  }

  //true test1 (different points); where there exist a pair of points (1.0,1.0) and (3.0,3.0) that are farther than
  //length1 apart, and a set of points (1.02,1.02) and (1.04,1.04) that are closer than length2 apart
  @Test
  public void testpos1(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = 1;
    Decide.PARAMETERS.length1 = 2;
    Decide.PARAMETERS.length2 = 1;
    Decide.X = new double[]{1.0,1.02,3.0,1.04,1.05};
    Decide.Y = new double[]{1.0,1.02,3.0,1.04,1.05};
    assertTrue(Decide.LIC12());
  }

  //true test2 (same point); where there exist a pair of points (1.0,0.0) and (2.0,0.0) that are farther than
  //length1 apart, and closer than length2 apart, no other points fulfill this
  @Test
  public void testpos2(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = 1;
    Decide.PARAMETERS.length1 = 0.999;
    Decide.PARAMETERS.length2 = 1.0001;
    Decide.X = new double[]{1.0,100.02,2.0,2001.04,12.05};
    Decide.Y = new double[]{0.0,9.12,0.0,99.04,50.05};
    assertTrue(Decide.LIC12());
  }
  //false test 1 (no points further than length1); where there does not exist a pair of points (with kpts between them)
  // that are farther than length1 apart
  @Test
  public void testfal1(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = 1;
    Decide.PARAMETERS.length1 = 2;
    Decide.PARAMETERS.length2 = 50;
    Decide.X = new double[]{1.0,1.05,1.1,1.12,1.05};
    Decide.Y = new double[]{1.0,1.05,1.1,1.12,1.05};
    assertFalse(Decide.LIC12());
  }

  //false test 2 (no points closer than length2); where there does not exist a pair of points (with kpts between them)
  // that are farther than length1 apart
  @Test
  public void testfal2(){
    Decide.NUM_POINTS = 5;
    Decide.PARAMETERS.k_pts = 1;
    Decide.PARAMETERS.length1 = 1;
    Decide.PARAMETERS.length2 = 1;
    //distance is roughly 2.8 (sqrt(8)) when k_pts is 1
    Decide.X = new double[]{1.0,2.0,3.0,4.0,5.0};
    Decide.Y = new double[]{1.0,2.0,3.0,4.0,5.0};
    assertFalse(Decide.LIC12());
  }



}
