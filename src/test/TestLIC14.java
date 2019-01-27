package main;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLIC14 {

    /**
     * Test the utility "computeTriangleArea" function. It should be true that:
     * computeTriangleArea(A,B,C) ==
     * computeTriangleArea(A,C,B) and so one i.e computeTriangleArea is commutative
     */
    @Test
    void test1() {
        double[] x = { 10, 13, 24, 0, 1, 10, 14, -10, -10 }; // coordinates of our points
        double[] y = { 98, 4, 13, 98, 0, -10, -10, 10, -10 };
        boolean[] areEqual = new boolean[3]; // quadrants in which they should belong
        boolean test = true;
        for (int i = 0; i < 3; ++i) {
            areEqual[i] = ((Decide.computeTriangleArea(x[3 * i], y[3 * i], x[3 * i + 1], y[3 * i + 1], x[3 * i + 2],
                    y[3 * i + 2]) == Decide.computeTriangleArea(x[3 * i], y[3 * i], x[3 * i + 2], y[3 * i + 2],
                            x[3 * i + 1], y[3 * i + 1]))
                    && (Decide.computeTriangleArea(x[3 * i], y[3 * i], x[3 * i + 2], y[3 * i + 2], x[3 * i + 1],
                            y[3 * i + 1]) == Decide.computeTriangleArea(x[3 * i + 1], y[3 * i + 1], x[3 * i + 2],
                                    y[3 * i + 2], x[3 * i], y[3 * 1]))
                    && (Decide.computeTriangleArea(x[3 * i + 1], y[3 * i + 1], x[3 * i + 2], y[3 * i + 2], x[3 * i],
                            y[3 * i]) == Decide.computeTriangleArea(x[3 * i + 1], y[3 * i + 1], x[3 * i], y[3 * i],
                                    x[3 * i + 2], y[3 * i + 2]))
                    && (Decide.computeTriangleArea(x[3 * i + 1], y[3 * i + 1], x[3 * i], y[3 * i], x[3 * i + 2],
                            y[3 * i + 2]) == Decide.computeTriangleArea(x[3 * i + 2], y[3 * i + 2], x[3 * i], y[3 * i],
                                    x[3 * i + 1], y[3 * i + 1]))
                    && Decide.computeTriangleArea(x[3 * i + 2], y[3 * i + 2], x[3 * i], y[3 * i], x[3 * i + 1],
                            y[3 * i + 1]) == Decide.computeTriangleArea(x[3 * i + 2], y[3 * i + 2], x[3 * i + 1],
                                    y[3 * i + 1], x[3 * i], y[3 * i]));
            test = test && areEqual[i];
        }

        assertTrue(test);
    }

    @Test
    void test2() {// Test the utility computeTriangleArea with basic and corner cases
        double[] x = { 0, 1, 0, 0, 2, 1, 0, 1, 2 }; // coordinates of our points
        double[] y = { 0, 0, 1, 0, 2, 1, 0, 1, 0 };
        double[] realAreas = {0.5, 0, 1}; //we are computing areas for three triangles
        double[] areas = new double[3];
        boolean test = true;
        for (int i = 0; i < areas.length; ++i) {
            areas[i] = Decide.computeTriangleArea(x[3*i],y[3*i],x[3*i+1],y[3*i+1],x[3*i+2],y[3*i+2]);
            test = test && (areas[i] == realAreas[i]); // verifying that our computed area is the same as the real one
        }

        assertTrue(test);
    }

    @Test
    void test3() { //Basic test that should return true
        Parameters p = new Parameters();
        p.e_pts = 3;
        p.f_pts = 4;
        p.area1 = 0.5;
        p.area2 = 1;
        Decide.PARAMETERS = p;
        double[] x = { -99, 0, -99, 0, -99, 1, -99, 1, -99, -99, 2, -99, 0, -99, -99, -99 }; // coordinates of our
                                                                                                   // points
        double[] y = { -99, 0, -99, 0, -99, 1, -99, 0, -99, -99, 0, -99, 1, -99, -99, -99 };

        //we have a triangle with area = 1 so it meets the condition area > area1 (the indexes for the points of the triangle are 1,5,10)
        //we have a triangle with area = 0.5 (and also with area 0) so it meets the condition area < area2 (the indexes for the points of the triangle are 3,7,12)
        Decide.X = x;
        Decide.Y = y;
        Decide.NUM_POINTS = x.length;

        assertTrue(Decide.LIC14());
    }


    @Test
    void test4() { //Corner case that should return false because we don't have enough points
        Parameters p = new Parameters();
        p.e_pts = 1;
        p.f_pts = 1;
        p.area1 = 0.5;
        p.area2 = 1;
        Decide.PARAMETERS = p;
        double[] x = {0,1,0,0};
        double[] y = {0,1,1,0};
        Decide.X = x;
        Decide.Y = y;
        Decide.NUM_POINTS = x.length;

        assertFalse(Decide.LIC14());
    }

    @Test
    void test5() { //Basic test that should return false
        Parameters p = new Parameters();
        p.e_pts = 1;
        p.f_pts = 2;
        p.area1 = 4;
        p.area2 = 1;
        Decide.PARAMETERS = p;
        double[] x = {-99,0, 0 ,1, 2,-99,0,4};
        double[] y = {-99,0 ,0 ,0, 2,-99,1,0};
        //there is a triangle (with indexes 2,4,7) of area = 4 but the condition to pass is having an area > 4 but 4 is not bigger than 4
        Decide.X = x;
        Decide.Y = y;
        Decide.NUM_POINTS = x.length;

        assertFalse(Decide.LIC14());
    }

}
