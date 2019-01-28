package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DecideTest {

    @Test
    void test1() { //Basic test that should return true as the PUV[i] = false for all i.
        Connector a = Connector.ANDD;
        Connector o = Connector.ORR;
        Connector n = Connector.NOTUSED;
        Connector[][] LCM = {{a,a,o,a,n,n,n,n,n,n,n,n,n,n,n},
                {a,a,o,o,n,n,n,n,n,n,n,n,n,n,n},
                {o,o,a,a,n,n,n,n,n,n,n,n,n,n,n},
                {a,o,a,a,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n}
                };
        Decide.LCM = LCM;
        Decide.NUM_POINTS = 10;
        double[] x = {0,0,0,0,0,0,0,0,0,0};
        double[] y = {0,0,0,0,0,0,0,0,0,0};
        Decide.X = x;
        Decide.Y = y;
        Parameters p = new Parameters();
        //these parameters are set so that no assertion is raised
        p.q_pts = 2;
        p.quads = 1;
        p.n_pts = 3;
        p.c_pts = 1;
        p.d_pts = 1;
        boolean[] puv = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
        Decide.PUV = puv;
        Decide.PARAMETERS = p;
        assertTrue(Decide.decide());
    }


    @Test
    void test2() { //Basic test that should return false as PUV[0] is true but PUM[0,1] and PUM[0,3] are false (this test reproduce the example from the description file)
        Connector a = Connector.ANDD;
        Connector o = Connector.ORR;
        Connector n = Connector.NOTUSED;
        Connector[][] LCM = {{a,a,o,a,n,n,n,n,n,n,n,n,n,n,n},
                {a,a,o,o,n,n,n,n,n,n,n,n,n,n,n},
                {o,o,a,a,n,n,n,n,n,n,n,n,n,n,n},
                {a,o,a,a,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n},
                {n,n,n,n,n,n,n,n,n,n,n,n,n,n,n}
                };
        Decide.LCM = LCM;
        double[] x = { 1, 6, 3, 4, 0, 1, 2, 1, 0 }; // coordinates of our points
        double[] y = { 1, 2, 9, 4, 0, 1, 2, 3, 4 };
        Decide.X = x;
        Decide.Y = y;
        Decide.NUM_POINTS = x.length;

        Parameters p = new Parameters();
        p.length1 = 999; // to ensure that LIC0() is false
        p.radius1 = 3; //to ensure that LIC1() is true
        p.epsilon = 0; // to ensure that LIC2() is true;

        //these parameters are set so that no assertion is raised
        p.q_pts = 2;
        p.quads = 1;
        p.n_pts = 3;
        p.c_pts = 1;
        p.d_pts = 1;

        boolean[] puv = {true,false,true,false,false,false,false,false,false,false,false,false,false,false,false};
        Decide.PUV = puv;
        Decide.PARAMETERS = p;

        assertFalse(Decide.LIC0());
        assertTrue(Decide.LIC1());
        assertTrue(Decide.LIC2());

        assertFalse(Decide.decide()); //LAUNCH is false because FUV[0] is false


        assertFalse(Decide.PUM[0][1]);//PUM[0,1] is false because LCM[0,1] is ANDD, and at least one of CMV[0] and CMV[1] is false.
        assertTrue(Decide.PUM[0][2]); //PUM[0,2] is true because LCM[0,2] is ORR, and at least one of CMV[0] and CMV[2] is true.
        assertTrue(Decide.PUM[1][2]); //PUM[1,2] is true because LCM[1,2] is ORR, and at least one of CMV[1] and CMV[2] is true.
        assertTrue(Decide.PUM[2][3]); //PUM[2,3] is true because LCM[2,3] is ANDD, and both CMV[2] and CMV[3] are true.
        assertTrue(Decide.PUM[0][4]); //PUM[0,4] is true because LCM[0,4] is NOTUSED


        assertFalse(Decide.FUV[0]); //FUV[0] is false because PUV[0] is true, but PUM[0,1] and PUM[0,3] are false.
        assertTrue(Decide.FUV[1]); //FUV[1] is true because PUV[1] is false.
        assertTrue(Decide.FUV[2]); // FUV[2] is true because PUV[2] is true and PUM[2,i] is true for all i6 = 2, 0 ≤ i ≤ 14.

    }


}
