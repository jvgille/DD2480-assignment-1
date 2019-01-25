package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.*;

public class TestLIC4 {

    @Test
    public void test1() { // Test the whichQuadrant function for corner and normal cases
        double[] x = { 0, -1, 0, 0, 1, 10, 10, -10, -10 }; // coordinates of our points
        double[] y = { 0, 0, -1, 1, 0, 10, -10, 10, -10 };
        int[] realIndexes = { 0, 1, 2, 0, 0, 0, 3, 1, 2 }; // quadrants in which they should belong
        int[] indexes = new int[x.length];
        boolean test = true;
        for (int i = 0; i < x.length; ++i) {
            indexes[i] = Decide.whichQuadrants(x[i], y[i]);
            test = test && (indexes[i] == realIndexes[i]); // verifying that our index is the same as the real one
        }

        assertTrue(test);
    }

    @Test
    public void test2() { // Test the LIC4 function for corner case
        Parameters p = new Parameters();
        p.q_pts = 2;
        p.quads = 3;
        double[] x = { 0, -1 }; // coordinates of our points
        double[] y = { 0, 0 };
        Decide.NUM_POINTS = x.length;
        Decide.X = x;
        Decide.Y = y;
        Decide.PARAMETERS = p;

        assertFalse(Decide.LIC4()); // Cannot be true since we need points in three different quadrants but we only
                                    // have 2 points

    }

    @Test
    public void test3() { // Test the LIC4 function for corner case
        Parameters p = new Parameters();
        p.q_pts = 2;
        p.quads = 1;
        double[] x = { 0, -1 }; // coordinates of our points
        double[] y = { 0, 0 };
        Decide.NUM_POINTS = x.length;
        Decide.X = x;
        Decide.Y = y;
        Decide.PARAMETERS = p;
        assertTrue(Decide.LIC4()); // Should be true since the first point is in quadrant 1 and the second is in
                                   // quadrant 2
    }

    @Test
    public void test4() { // Test the LIC4 function on regular array (answer should be false)
        Parameters p = new Parameters();
        p.q_pts = 4;
        p.quads = 3;
        double[] x = { 0, -1, 0, 10, 10, 10, -10 }; // coordinates of our points
        double[] y = { 0, 0, 0, 10, -10, 10, -10 };
        Decide.NUM_POINTS = x.length;
        Decide.X = x;
        Decide.Y = y;
        Decide.PARAMETERS = p;
        assertFalse(Decide.LIC4()); // Should be false since we don't have 4 consecutive points lying in more than 3
                                    // quadrants
    }

    @Test
    public void test5() { // Test the LIC4 function on regular array (answer should be true)
        Parameters p = new Parameters();
        p.q_pts = 4;
        p.quads = 3;
        double[] x = { 0, -1, 0, 10, 10, 10, 10, 0, -10 }; // coordinates of our points
        double[] y = { 0, 0, 0, 10, -10, 10, -10, -10, 0 };
        Decide.NUM_POINTS = x.length;
        Decide.X = x;
        Decide.Y = y;
        Decide.PARAMETERS = p;
        assertTrue(Decide.LIC4()); // Should be true since the last four point belongs to quadrants respectively 1,
                                   // 2, 3, and 4
    }

}
