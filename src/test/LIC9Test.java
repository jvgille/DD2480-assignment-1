package main;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LIC9Test {

    @Test
    void test3() { // Basic test that should return true
        Parameters p = new Parameters();
        p.c_pts = 3;
        p.d_pts = 4;
        p.epsilon = Math.PI / 2;
        Decide.PARAMETERS = p;
        double[] x = { -99, -99, -99, 1, -99, -99, -99, 0, -99, -99, -99, -99, 1, -99, -99, -99 }; // coordinates of our
                                                                                                   // points
        double[] y = { -99, -99, -99, 0, -99, -99, -99, 0, -99, -99, -99, -99, 1, -99, -99, -99 };
        // There is one angle of PI/4 using the 3rd, 7th and 12th point and PI/4 < PI-
        // PI/2
        Decide.X = x;
        Decide.Y = y;
        Decide.NUM_POINTS = x.length;

        assertTrue(Decide.LIC9());
    }

    @Test
    void test4() { // Basic test that should return false
        Parameters p = new Parameters();
        p.c_pts = 3;
        p.d_pts = 4;
        p.epsilon = (3 * Math.PI / 4) + 0.01; // There is one angle of PI/4 but PI/4 > PI - 3PI/4 - 0.01, so the
                                              // condition isn't satisfied
        Decide.PARAMETERS = p;
        double[] x = { -99, -99, 1, -99, -99, -99, 0, -99, -99, -99, -99, 1, -99, -99, -99, -99 }; // coordinates of our
                                                                                                   // points
        double[] y = { -99, -99, 0, -99, -99, -99, 0, -99, -99, -99, -99, 1, -99, -99, -99, -99 };
        Decide.X = x;
        Decide.Y = y;
        Decide.NUM_POINTS = x.length;

        assertFalse(Decide.LIC9());
    }

    @Test
    void test5() { // Basic test that should return true
        Parameters p = new Parameters();
        p.c_pts = 1;
        p.d_pts = 1;
        p.epsilon = Math.PI / 4;
        Decide.PARAMETERS = p;
        double[] x = { -99, -99, -99, -1, -99, 0, -99, 1, -99, -99, -99, -99, -99, -99, -99, -99 }; // coordinates of
                                                                                                    // our points
        double[] y = { -99, -99, -99, 0, -99, 0, -99, 1, -99, -99, -99, -99, -99, -99, -99, -99 };
        // There is one angle of 135deg using the 3rd, 5th and 7th point and 135 < 190-
        // 45
        Decide.X = x;
        Decide.Y = y;
        Decide.NUM_POINTS = x.length;

        assertTrue(Decide.LIC9());
    }

}
