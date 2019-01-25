package decide;

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
    private static boolean LIC4() {
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
    private static boolean LIC9() {
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
    Returns the distance between two points
    */
    public static double distance(double x0, double y0, double x1, double y1) {
        double dx = x0 - x1;
        double dy = y0 - y1;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
