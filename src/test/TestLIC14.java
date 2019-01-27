package main;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestLIC14 {

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
