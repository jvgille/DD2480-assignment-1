package main;

public class Decide {
    public static int NUM_POINTS;
    public static double[] X;
    public static double[] Y;

    public static Parameters PARAMETERS;

    public static Connector[][] LCM;
    public static boolean[] PUV;

    public static boolean decide() {
        return false;
    }

    private static boolean LIC0() {
        return false;
    }

    private static boolean LIC1() {
        return false;
    }

    private static boolean LIC2() {
        return false;
    }

    private static boolean LIC3() {
        return false;
    }

    public static boolean LIC4() {
        assert (PARAMETERS.q_pts <= NUM_POINTS && PARAMETERS.q_pts >= 2); // has to be true otherwise a wrong input was
                                                                          // given
        assert (PARAMETERS.quads <= 3 && PARAMETERS.quads >= 1); // has to be true otherwise a wrong input was given

        for (int i = 0; i <= NUM_POINTS - PARAMETERS.q_pts; ++i) { // i is the index of the first element of the q_pts
                                                                   // points
            double count = 0; // count is the number of different quadrants in which there are points
                              // so count will be between 0 and 4
            int quadrants[] = { 0, 0, 0, 0 }; // each cell of the array will contain the number of points in
                                              // repsectivly the first, second, third and fourth quadrants
            for (int j = 0; j < PARAMETERS.q_pts; ++j) { // iteration over the q_pts consecutive points
                double x = X[i + j];
                double y = Y[i + j];
                int index = whichQuadrants(x, y); // find the right quadrant for the current point
                assert (index != -1); // index should never return -1
                quadrants[index] += 1;

            }

            for (int j = 0; j < 4; ++j) { // if a quadrant has at least one element in it, we can increment count
                if (quadrants[j] > 0) {
                    count += 1;
                }
            }
            if (count > PARAMETERS.quads) {
                return true;
            }
        }
        return false;
    }

    private static boolean LIC5() {
        return false;
    }

    private static boolean LIC6() {
        return false;
    }

    private static boolean LIC7() {
        return false;
    }

    private static boolean LIC8() {
        return false;
    }

    public static boolean LIC9() {
        return false;
    }

    private static boolean LIC10() {
        return false;
    }

    private static boolean LIC11() {
        return false;
    }

    private static boolean LIC12() {
        return false;
    }

    private static boolean LIC13() {
        return false;
    }

    private static boolean LIC14() {
        return false;
    }

    /**
     * returns the quadrant to which the point (x,y) belongs
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return an index between 0 and 3 included
     */
    public static int whichQuadrants(double x, double y) {
        if (x > 0) {
            if (y > 0) {
                return 0;
            } else if (y < 0) {
                return 3;
            }
        } else if (x < 0) {
            if (y > 0) {
                return 1;
            } else if (y < 0) {
                return 2;
            }
        }
        if (x == 0) {
            if (y > 0) {
                return 0;
            } else if (y < 0) {
                return 2;
            }
        }
        if (y == 0) {
            if (x > 0) {
                return 0;
            } else if (x < 0) {
                return 1;
            }
        }
        if (x == 0 && y == 0) {
            return 0;
        }
        return -1; // the function should not reach this line
    }

}
