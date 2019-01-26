package main;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLIC9 {

    @Test
    void test1() { // test the distance method for some points
        double[] x = { 0, 1, 0, 0, 0, 1 }; // coordinates of our points
        double[] y = { 0, 0, 0, 0, 0, 1 };
        double[] realDistances = { 1, 0, 1.414 }; // we compute the distance between (x[0],y[0]) and (x[1],y[1])
                                                  // then between (x[2],y[2]) and (x[3],y[3])...
        double[] distances = new double[3];
        boolean test = true;
        for (int i = 0; i < 3; ++i) {
            distances[i] = Decide.distance(x[2 * i], y[2 * i], x[2 * i + 1], y[2 * i + 1]);
            test = test && (Math.abs(distances[i] - realDistances[i]) < 0.001); // verifying that the computed distance
                                                                                // is the same as the real one
            test = test && (distances[i] == Decide.distance(x[2 * i + 1], y[2 * i + 1], x[2 * i], y[2 * i]));// verifying
                                                                                                                    // that
                                                                                                                    // the
                                                                                                                    // dist(A,B)
                                                                                                                    // ==
                                                                                                                    // dist(B,A)
        }

        assertTrue(test);
    }

    @Test
    void test2() { // test the angle method for some points
        double[] x = { 1, 0, 0, 1, 0, 1, -1, 0, 1 }; // coordinates of our points
        double[] y = { 0, 0, 1, 1, 0, 0, 0, 0, 0 };
        double[] realAngles = { Math.PI / 2, Math.PI / 4, Math.PI }; // we compute the angle function using (x[0],y[0]),
        // (x[1],y[1]) and (x[2],y[2])
        // then using (x[3],y[3]), (x[4],y[4]) and (x[6],y[6]) ...
        double[] angles = new double[3];
        boolean test = true;
        for (int i = 0; i < 3; ++i) {
            angles[i] = Decide.computeAngle(x[3 * i], y[3 * i], x[3 * i + 1], y[3 * i + 1], x[3 * i + 2], y[3 * i + 2]);
            test = test && (Math.abs(angles[i] - realAngles[i]) < 0.001);
        }

        assertTrue(test);
    }

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
