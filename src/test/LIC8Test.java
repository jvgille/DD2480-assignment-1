package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.*;

public class LIC8Test {

    @Test
    public void test1() { // Test the LIC8 for to few NUM_POINTS
        Parameters p = new Parameters();
        p.a_pts = 1;
        p.b_pts = 1;
        double[] x = { 0, 1 }; // coordinates of our points
        double[] y = { 0, 1 };
        Decide.NUM_POINTS = x.length;
        Decide.X = x;
        Decide.Y = y;
        Decide.PARAMETERS = p;

        assertFalse(Decide.LIC8()); //should fail (NUM_POINTS<5)
    }
    @Test
    public void test2() { // Test the LIC8 for negative a_pts
        Parameters p = new Parameters();
        p.a_pts = -1;
        p.b_pts = 1;
        double[] x = { 0, 1, 3, 5, 2 }; // coordinates of our points
        double[] y = { 0, 1, 4, 2, 4 };
        Decide.NUM_POINTS = x.length;
        Decide.X = x;
        Decide.Y = y;
        Decide.PARAMETERS = p;

        assertFalse(Decide.LIC8()); //should fail (a_pts<5)
    }
    @Test
    public void test3() { // Test the LIC8 for negative a_pts
        Parameters p = new Parameters();
        p.a_pts = 2;
        p.b_pts = 1;
        p.radius1 = 10;
        double[] x = { 0, 1, 3, 5, 2, 6, 9 }; // coordinates of our points
        double[] y = { 0, 1, 4, 2, 4, 9, 8 };
        Decide.NUM_POINTS = x.length;
        Decide.X = x;
        Decide.Y = y;
        Decide.PARAMETERS = p;

        assertTrue(Decide.LIC8()); //should be true. 
    }

}
