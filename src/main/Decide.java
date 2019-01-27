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

    /**
    Returns true if there is a set of n_pts consecutive points such that
    if the first and last point coincide:
        one of the points is further than dist units away from those points
    else:
        one of the points is further than dist units away from
        the line that passes through the first and the last point
    */
    public static boolean LIC6() {
        assert(NUM_POINTS >= PARAMETERS.n_pts && PARAMETERS.n_pts >= 3);
        assert(PARAMETERS.dist >= 0);

        for (int start = 0; start < NUM_POINTS - PARAMETERS.n_pts + 1; start++) {
            int end = start + PARAMETERS.n_pts - 1;

            if (same(X[start], Y[start], X[end], Y[end])) {     // calculate distance to point
                for (int i = start + 1; i < end; i++) {
                    if (distance(X[start], Y[start], X[i], Y[i]) > PARAMETERS.dist) {
                        return true;
                    }
                }
            } else {                                            // calculate distance to line
                // the general equation for a line is
                // a*x + b*y + c = 0
                double a, b, c;

                if (X[start] == X[end]) {
                    // the slope is infinite, and the equation is just
                    // x = X[start]
                    a = 1;
                    b = 0;
                    c = -X[start];
                } else {
                    // determine y = kx+m and then convert to general equation
                    double dx = X[end] - X[start];
                    double dy = Y[end] - Y[start];
                    double k = dy / dx;
                    double m = Y[start] - k * X[start];
                    a = -k;
                    b = 1;
                    c = -m;
                }

                for (int i = start + 1; i < end; i++) {
                    // the distance between a point (x,y) and a line a*x+b*y+c=0 is given by
                    // |a*x+b*y+c|/(sqrt(a²+b²))
                    double distance = Math.abs(a*X[i] + b*Y[i] + c) / Math.sqrt(a*a + b*b);
                    if (distance > PARAMETERS.dist) {
                        return true;
                    }
                }
            }
        }
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

    /**
    - There exists a set of three points separated by APTS and BPTS consecutive intervening points,
      that CANNOT be contained within or on a circle of radius RADIUS1.
    - There exists a set of three points separated by APTS and BPTS consecutive intervening points,
      that CAN be contained in or on a circle of radius RADIUS2.
    Both parts must be true for the LIC to be true.
    The condition is not met when NUMPOINTS < 5.
    */
    public static boolean LIC13() {
        if (NUM_POINTS < 5) {
            return false;
        }

        boolean cond_1_met = false;
        boolean cond_2_met = false;

        for (int i = 0; i + PARAMETERS.a_pts + PARAMETERS.b_pts + 2 < NUM_POINTS; i++) {
            int j = i + PARAMETERS.a_pts + 1;
            int k = j + PARAMETERS.b_pts + 1;

            Point first = new Point(X[i], Y[i]);
            Point second = new Point(X[j], Y[j]);
            Point third = new Point(X[k], Y[k]);

            if (smallestCircle(new Point[]{first, second, third}) > PARAMETERS.radius1) {
                cond_1_met = true;
            }

            if (smallestCircle(new Point[]{first, second, third}) <= PARAMETERS.radius2) {
                cond_2_met = true;
            }
        }

        return cond_1_met && cond_2_met;
    }

    private static boolean LIC14() {
        return false;
    }

    /**
    Returns the radius of the smallest circle containing all the points.
    */
    public static double smallestCircle(Point[] points) {
        Point circle = Point.average(points[0], points[1]);
        double radius = distance(points[0], points[1]) / 2;
        if (distance(circle, points[2]) <= radius) {
            return radius;
        }
        circle = Point.average(points[1], points[2]);
        radius = distance(points[1], points[2]) / 2;
        if (distance(circle, points[0]) <= radius) {
            return radius;
        }
        circle = Point.average(points[0], points[2]);
        radius = distance(points[0], points[2]) / 2;
        if (distance(circle, points[1]) <= radius) {
            return radius;
        }

        // at this point we know all the points are on the circle's perimeter
        // we use black magic (determinants) to get the radius
        double x_i = points[0].x;
        double y_i = points[0].y;
        double x_j = points[1].x;
        double y_j = points[1].y;
        double x_k = points[2].x;
        double y_k = points[2].y;

        double a = determinant(x_i, y_i, 1,
                               x_j, y_j, 1,
                               x_k, y_k, 1);

        double d = determinant(x_i*x_i + y_i*y_i, y_i, 1,
                               x_j*x_j + y_j*y_j, y_j, 1,
                               x_k*x_k + y_k*y_k, y_k, 1);

        double e = determinant(x_i*x_i + y_i*y_i, x_i, 1,
                               x_j*x_j + y_j*y_j, x_j, 1,
                               x_k*x_k + y_k*y_k, x_k, 1);

        double f = determinant(x_i*x_i + y_i*y_i, x_i, y_i,
                               x_j*x_j + y_j*y_j, x_j, y_j,
                               x_k*x_k + y_k*y_k, x_k, y_k);

        return Math.sqrt((d*d+e*e)/(4*a*a)+f/a);
    }

    /**
    Returns the determinant of the given matrix:
    a b c
    d e f
    g h i
    */
    public static double determinant(double a, double b, double c,
                                     double d, double e, double f,
                                     double g, double h, double i) {
        return a*(e*i-f*h) - b*(d*i-f*g) + c*(d*h-e*g);
    }

    /**
    Returns the distance between two points
    */
    public static double distance(Point a, Point b) {
        return distance(a.x, a.y, b.x, b.y);
    }

    /**
    Returns the distance between two points
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

    public static boolean same(double x0, double y0, double x1, double y1) {
        return x0 == x1 && y0 == y1;
    }
}
