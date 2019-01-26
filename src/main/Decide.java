package main;

import java.lang.Math;

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

    /**
    Returns true if at least one set of three consecutive points form an angle such that
        angle<(PI−EPSILON)
    or
        angle>(PI+EPSILON)
    */
    public static boolean LIC2() {
        for (int i = 2; i < NUM_POINTS; i++) {
            double first_x = X[i-2];
            double first_y = Y[i-2];
            double vertex_x = X[i-1];
            double vertex_y = Y[i-1];
            double second_x = X[i];
            double second_y = Y[i];

            // if either the first or second point coincides with the vertex,
            // the angle is undefined and the LIC is not satisfied for this set of points
            if ((first_x == vertex_x && first_y == vertex_y) ||
                (second_x == vertex_x && second_y == vertex_y)) {
                continue;
            }

            // law of cosines for triangles:
            //    c^2 = a^2 + b^2 − 2*a*b*cos(C)
            // => C = arccos((a^2 + b^2 - c^2)/(2*a*b))
            // where a,b,c are sides and C is the angle opposite c

            double a = distance(first_x, first_y, vertex_x, vertex_y);
            double b = distance(vertex_x, vertex_y, second_x, second_y);
            double c = distance(first_x, first_y, second_x, second_y);
            double angle = Math.acos((a*a + b*b - c*c)/(2*a*b));

            if (angle < Math.PI - PARAMETERS.epsilon ||
                angle > Math.PI + PARAMETERS.epsilon) {
                return true;
            }
        }
        return false;
    }

    /**
     * The LIC should be satisfied iff there exist a set of three consecutive points which form a triangle whose area is greater than AREA1.
     * @return true if the at least one triangle has an area strictly greater than area1, false otherwise.
     */
    public static boolean LIC3() {
    	assert(PARAMETERS.area1 >= 0);
    	if(NUM_POINTS <= 2) {
    		return false;
    	}
    	for(int i=0; i < NUM_POINTS-2; ++i) {
    		double area = Math.abs(((X[i]*(Y[i+1]-Y[i+2])+X[i+1]*(Y[i+2]-Y[i])+X[i+2]*(Y[i]-Y[i+1]))/2.0));
    		if(area>PARAMETERS.area1) {
    			return true;
    		}
    	}
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

    /**
     * Returns true if at least one set of three data points separated by exactly c_pts
     * and d_pts consecutive intervening points, respectively, that form an
     * angle such that: angle<(PI−epsilon) or angle>(PI+epsilon)
     *
     */
    public static boolean LIC9() {
        assert (PARAMETERS.c_pts >= 1 && PARAMETERS.d_pts >= 1);
        assert (PARAMETERS.c_pts + PARAMETERS.d_pts <= NUM_POINTS - 3);
        if (NUM_POINTS < 5) {
            return false;
        }
        for (int i = 0; i <= NUM_POINTS - PARAMETERS.c_pts - PARAMETERS.d_pts - 3; ++i) {
            // the three points a, b, c where: a and b have exactly c_pts points between
            // them and: b and c have exactly d_pts points between them
            // b is the vertex of the angle
            double aX = X[i];
            double bX = X[i + PARAMETERS.c_pts + 1];
            double cX = X[i + PARAMETERS.c_pts + PARAMETERS.d_pts + 2];
            double aY = Y[i];
            double bY = Y[i + PARAMETERS.c_pts + 1];
            double cY = Y[i + PARAMETERS.c_pts + PARAMETERS.d_pts + 2];

            // If either the first
            // point or the last point (or both) coincide with the vertex, the angle is
            // undefined and the LIC is not satisfied by those three points

            if (!((aX == bX && aY == bY) || (cX == bX && cY == bY))) { // if they don't coincide we continu
                                                                       // otherhwise we got to the next set of three
                                                                       // points
                double angle = computeAngle(aX, aY, bX, bY, cX, cY);
                if (angle < Math.PI - PARAMETERS.epsilon || angle > Math.PI + PARAMETERS.epsilon) {
                    return true;
                }

            }
        }

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
     * computes the distance between two points (x0,y0) and (x1, y1)
     *
     * @param x0 the x coordinate of the first point
     * @param y0 the y coordinate of the first point
     * @param x1 the x coordinate of the second point
     * @param y1 the y coordinate of the second point
     * @return the distance between (x0,y0) and (x1, y1)
     */
    public static double distance(double x0, double y0, double x1, double y1) {
        double dx = x0 - x1;
        double dy = y0 - y1;
        return Math.sqrt(dx*dx + dy*dy);
    }

    /** returns the quadrant to which the point (x,y) belongs
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



    /**
     * computes the angle between two rays which share a common endpoint called a
     * vertex the vertex is (bX,bY) and the two rays are the vectors going from
     * (bX,bY) to (aX,aY) and from (bX,bY) to (cX,cY)
     *
     * @param aX the x coordinate of the first point
     * @param aY the y coordinate of the first point
     * @param bX the x coordinate of the second point which is the vertex
     * @param bY the y coordinate of the second point which is the vertex
     * @param cX the x coordinate of the third point
     * @param cY the y coordinate of the third point
     * @return the angle in radian
     */
    public static double computeAngle(double aX, double aY, double bX, double bY, double cX, double cY) {

        if (aX == cX && aY == cY) { // both rays are the same so the angle between them is zero
            return 0;
        }
        return Math.acos((distance(bX, bY, aX, aY) * distance(bX, bY, aX, aY)
                + distance(bX, bY, cX, cY) * distance(bX, bY, cX, cY)
                - distance(aX, aY, cX, cY) * distance(aX, aY, cX, cY))
                / (2 * distance(bX, bY, aX, aY) * distance(bX, bY, cX, cY)));
    }
}
