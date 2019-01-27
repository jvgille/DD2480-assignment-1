package main;

import java.lang.Math;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

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
    /*
    There exists at least one set of three consecutive data points that 
    cannot all be contained within or on a circle of radius RADIUS1.
    (0 ≤ RADIUS1)
    */
    public static boolean LIC1() {
        //if radius is less than 0
        if (0 > PARAMETERS.radius1) {
            return false;
        }
        for (int i = 0; i < NUM_POINTS - 2; i++){ //has to be three in a row (-2)
            double first_x = X[i];
            double first_y = Y[i];
            double second_x = X[i+1];
            double second_y = Y[i+1];
            double third_x = X[i+2];
            double third_y = Y[i+2];
    
            //throw to smallestCircle
            if(smallestCircle(first_x, first_y, second_x, second_y, third_x, third_y, PARAMETERS.radius1)) {
                return true;
            } 
        }
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

    //static boolean that can checks if 3 points are in a radius
    static boolean smallestCircle(double x1, double y1, double x2, double y2, double x3, double y3, double radiusIn ){

        //length between points
        double distFirstSecond = sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
        double distFirstThird = sqrt(pow(x1 - x3, 2) + pow(y1 - y3, 2));
        double distSecondThird = sqrt(pow(x2 - x3, 2) + pow(y2 - y3, 2));
        
        double longesDist = 0;
        double longestDistAverageX = 0;
        double longestDistAverageY = 0;
        double distCenterToOutsider = 0;
        double longestDistRadius = 0;

        //find the smallest cyrcle, tests
        if(distFirstSecond >= distFirstThird){
            if(distFirstSecond >= distSecondThird){
                longesDist = distFirstSecond;
                longestDistAverageX = (x1 + x2)/2;
                longestDistAverageY = (y1 + y2)/2;
                distCenterToOutsider = sqrt(pow(longestDistAverageX - x3, 2) + pow(longestDistAverageY - y3, 2));
            }
            else{
                longesDist = distSecondThird;
                longestDistAverageX = (x2 + x3)/2;
                longestDistAverageY = (y2 + y3)/2;
                distCenterToOutsider = sqrt(pow(longestDistAverageX - x1, 2) + pow(longestDistAverageY - y1, 2));
            }
        }
        else if(distFirstThird >= distFirstSecond){
            if(distFirstThird >= distSecondThird){
                longesDist = distFirstThird;
                longestDistAverageX = (x1 + x3)/2;
                longestDistAverageY = (y1 + y3)/2;
                distCenterToOutsider = sqrt(pow(longestDistAverageX - x2, 2) + pow(longestDistAverageY - y2, 2));
            }
            else{
                longesDist = distSecondThird;
                longestDistAverageX = (x2 + x3)/2;
                longestDistAverageY = (y2 + y3)/2;
                distCenterToOutsider = sqrt(pow(longestDistAverageX - x1, 2) + pow(longestDistAverageY - y1, 2));
            }
        }
        double radiusCircle = 0;
        longestDistRadius = longesDist/2;
        if (longestDistRadius >= distCenterToOutsider){
            radiusCircle = longestDistRadius;
        }
        else{
            // Calculating the radius of the circumcircle
            double totalLength = distFirstSecond * distFirstThird * distSecondThird;
            double radiusRange =
                (distFirstSecond + distFirstThird + distSecondThird) *
                (distFirstSecond + distFirstThird - distSecondThird) *
                (distFirstThird + distSecondThird - distFirstSecond) *
                (distSecondThird + distFirstSecond - distFirstThird);
            radiusCircle = totalLength / sqrt(radiusRange);
        }
        // Check 
        if (radiusCircle <= radiusIn) {
            return true;
        }
        return false;
    }
}
