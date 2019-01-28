package main;

import java.lang.Math;

public class Decide {
    // Input variables
    public static int NUM_POINTS;
    public static double[] X;
    public static double[] Y;
    public static Parameters PARAMETERS;
    public static Connector[][] LCM;
    public static boolean[] PUV;

    // Output variables
    public static boolean launch;
    public static boolean[] CMV;
    public static boolean[][] PUM;
    public static boolean[] FUV;

    public static boolean decide() {
        assert(NUM_POINTS >= 2 && NUM_POINTS >= 100);
        CMV = new boolean[15];
        PUM = new boolean[15][15];
        FUV = new boolean[15];
        // compute all 15 LICs
        CMV[0] = LIC0();
        CMV[1] = LIC1();
        CMV[2] = LIC2();
        CMV[3] = LIC3();
        CMV[4] = LIC4();
        CMV[5] = LIC5();
        CMV[6] = LIC6();
        CMV[7] = LIC7();
        CMV[8] = LIC8();
        CMV[9] = LIC9();
        CMV[10] = LIC10();
        CMV[11] = LIC11();
        CMV[12] = LIC12();
        CMV[13] = LIC13();
        CMV[14] = LIC14();

        boolean temp = false;
        // filling the 15x15 PUM matrix according to PUM[i,j] = CMV[i] LCM[i][j] CMV[j];
        // where LCM[i][j] is a logical connector

        for (int i = 0; i < 15; ++i) {
            for (int j = 0; j < 15; ++j) {
                switch (LCM[i][j]) {
                case ANDD:
                    temp = CMV[i] && CMV[j];
                    break;
                case ORR:
                    temp = CMV[i] || CMV[j];
                    break;
                case NOTUSED:
                    temp = true;
                    break;
                default:
                    temp = false;
                    break;
                }
                PUM[i][j] = temp;
            }
        }

        for (int i = 0; i < 15; ++i) {
            if (PUV[i] == false) {
                FUV[i] = true;
            } else {
                temp = true;
                for (int j = 0; j < 15; ++j) {
                    temp = temp && PUM[i][j];
                }
                FUV[i] = temp;
            }
        }
        launch = true;
        for(int i = 0; i < 15; ++i) {
            launch = launch && FUV[i];
        }
        if(launch) {
            System.out.println("YES");
        }else {
            System.out.println("NO");
        }
        return launch;
    }

    //Return true iff 2 consecutive points are a distance gt length1 apart.
    public static boolean LIC0() {
    	for(int i = 0; i < X.length-1; i++){
    		double x1 = X[i];
    		double x2 = X[i+1];
    		double y1 = Y[i];
    		double y2 = Y[i+1];
    		double distance = distance(x1,y1,x2,y2);
    		if(distance > PARAMETERS.length1){
    			return true;
    		}
    	}
    	return false;
    }

    /*
     * There exists at least one set of three consecutive data points that
     * cannot all be contained within or on a circle of radius RADIUS1.
     * (0 ≤ RADIUS1)
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

            Point first = new Point(first_x, first_y);
            Point second = new Point(second_x, second_y);
            Point third = new Point(third_x, third_y);

            if (smallestCircle(new Point[]{first, second, third}) > PARAMETERS.radius1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if at least one set of three consecutive points form an angle such that
     *     angle<(PI−EPSILON)
     * or
     *     angle>(PI+EPSILON)
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

            double angle = computeAngle(first_x, first_y, vertex_x, vertex_y, second_x, second_y);

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
    		double area = computeTriangleArea(X[i], Y[i], X[i+1], Y[i+1], X[i+2], Y[i+2]);
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

    /**
     * The LIC should be satisfied if there exists at least two consecutive data points (X[i],Y[i]) and (X[j],Y[j]),
	 * such that X[j] - X[i] < 0. (where i = j-1)
     * @return true when the above condition is achieved. False otherwise.
     */
    public static boolean LIC5() {
        if(NUM_POINTS <= 1) {
    		return false;
    	}
    	for(int i=0; i < NUM_POINTS-1; ++i) {
    		if(X[i+1]-X[i]<0) {
    			return true;
    		}
    	}
        return false;
    }

    /**
     * Returns true if there is a set of n_pts consecutive points such that
     * if the first and last point coincide:
     *     one of the points is further than dist units away from those points
     * else:
     *     one of the points is further than dist units away from
     *     the line that passes through the first and the last point
     */
    public static boolean LIC6() {
        assert(NUM_POINTS >= PARAMETERS.n_pts && PARAMETERS.n_pts >= 3);
        assert(PARAMETERS.dist >= 0);

        for (int start = 0; start < NUM_POINTS - PARAMETERS.n_pts + 1; start++) {
            int end = start + PARAMETERS.n_pts - 1;

            if (X[start] == X[end] && Y[start] == Y[end]) {     // calculate distance to point
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

    /**
     * LIC is met iff there exist at least one set of 2 data points, separated by exactly K_PTS
     * consecutive intervening points, that are a distance greater than LENGHT1 apart.
     * Length1 >= 0
     * K_PTS >=1
     * K_PTS <= NUM_POINTS - 2
     */
    public static boolean LIC7() {
      int kpt = PARAMETERS.k_pts;
      if(NUM_POINTS < 3){
        return false;
      }
      for(int i = 0; i < NUM_POINTS - 1 - kpt; i++){
        double x0 = X[i];
        double y0 = Y[i];
        double x1 = X[i+kpt+1];
        double y1 = Y[i+kpt+1];
        if(distance(x0,y0,x1,y1) > PARAMETERS.length1){
          return true;
        }
      }//for

        return false;
    }//lic7

    /*
     * There exists at least one set of three data points separated by exactly A PTS
     * and B PTS consecutive intervening points, respectively, that cannot be
     * contained within or on a circle of radius RADIUS1. The condition is not
     * met when NUMPOINTS < 5.
     * 1 ≤ A PTS, 1 ≤ B PTS
     * A PTS+B PTS ≤ (NUMPOINTS−3)
     */
    public static boolean LIC8() {
        //conditions
        if(NUM_POINTS < 5 || PARAMETERS.a_pts < 1 || PARAMETERS.b_pts < 1 || PARAMETERS.a_pts + PARAMETERS.b_pts > NUM_POINTS - 3){
            return false;
        }

        for(int i = 0; i < NUM_POINTS - 2 - PARAMETERS.a_pts - PARAMETERS.b_pts; i++){ //has to be three in a row (-2)
            double first_x = X[i];
            double first_y = Y[i];
            double second_x = X[i+1+PARAMETERS.a_pts];
            double second_y = Y[i+1+PARAMETERS.a_pts];
            double third_x = X[i+2+PARAMETERS.a_pts+PARAMETERS.b_pts];
            double third_y = Y[i+2+PARAMETERS.a_pts+PARAMETERS.b_pts];

            Point first = new Point(first_x, first_y);
            Point second = new Point(second_x, second_y);
            Point third = new Point(third_x, third_y);

            if (smallestCircle(new Point[]{first, second, third}) > PARAMETERS.radius1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if at least one set of three data points separated by exactly c_pts
     * and d_pts consecutive intervening points, respectively, that form an
     * angle such that: angle<(PI−epsilon) or angle>(PI+epsilon)
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

    /**
     * The LIC should be satisfied iff there exist a set of three consecutive points, separated by exactly E PTS and F PTS consecutive intervening points,
	 * respectively, which form a triangle whose area is greater than AREA1.
     * @return true when an area greater than area1 is found.
     */
    public static boolean LIC10() {
    	assert(PARAMETERS.area1 >= 0);
    	int e_pts = PARAMETERS.e_pts;
    	int f_pts = PARAMETERS.f_pts;
    	if(NUM_POINTS<5 || e_pts<1 || f_pts<1 || e_pts+f_pts>NUM_POINTS-3) {
    		return false;
    	}
    	for(int i=0; i < NUM_POINTS-(e_pts+f_pts+2); ++i) {
    		double area = computeTriangleArea(X[i], Y[i], X[i+1+e_pts], Y[i+1+e_pts], X[i+2+e_pts+f_pts], Y[i+2+e_pts+f_pts]);
    		if(area>PARAMETERS.area1) {
    			return true;
    		}
    	}
        return false;
    }

    /**
     * The LIC should be satisfied if there exists at least two consecutive data points (X[i],Y[i]) and (X[j],Y[j]),
	 * separated by exactly G PTS consecutive intervening points,
	 * such that X[j] - X[i] < 0. (where i = j-1)
     * @return true when the above condition is completed. False otherwise
     */
    public static boolean LIC11() {
    	int g_pts = PARAMETERS.g_pts;
    	if(NUM_POINTS<3 || g_pts<1 || g_pts>NUM_POINTS-2 ) {
    		return false;
    	}
    	for(int i=0; i < NUM_POINTS-(g_pts+1); ++i) {
    		if(X[i+g_pts+1]-X[i]<0) {
    			return true;
    		}
    	}
        return false;
    }

    /**
    * LIC met if;
    * - There are 2 points (at least) separated by exactly k_pts, which are a distance greater than length1 apart.
    * - There are 2 points (could be same as above, could be other points) separated by exactly k_pts, that are less than
    *   length2 apart.
    * Both parts have to be true.
    * Not met when NUM_POINTS < 3
    * length2 >= 0
    */
    public static boolean LIC12() {
        if(NUM_POINTS < 3){
            return false;
        }

        int kpt = PARAMETERS.k_pts;
        boolean l1 = false;
        boolean l2 = false;

        for(int i = 0; i < NUM_POINTS - 1 - kpt; i++){
            double x0 = X[i];
            double y0 = Y[i];
            double x1 = X[i+kpt+1];
            double y1 = Y[i+kpt+1];
            double distance = distance(x0,y0,x1,y1);
            if(distance > PARAMETERS.length1){
                l1 = true;
            }
            if(distance < PARAMETERS.length2){
                l2 = true;
            }
            if(l1 && l2){
                return true;
            }
        }//for
        return false;
    }//LIC12

    /**
     * - There exists a set of three points separated by APTS and BPTS consecutive intervening points,
     *   that CANNOT be contained within or on a circle of radius RADIUS1.
     * - There exists a set of three points separated by APTS and BPTS consecutive intervening points,
     *   that CAN be contained in or on a circle of radius RADIUS2.
     * Both parts must be true for the LIC to be true.
     * The condition is not met when NUMPOINTS < 5.
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

    /**
    * Returns true if there exists at least one set of three data points, separated
    * by exactly e_pts and f_pts consecutive intervening points, respectively, that
    * are the vertices of a triangle with area greater than AREA1 and if there
    * exist three data points (which can be the same or different from the three
    * data points just mentioned) separated by exactly e_pts and f_pts consecutive
    * intervening points, respectively, that are the vertices of a triangle with
    * area less than AREA2.
    *
    */
    public static boolean LIC14() {
        assert (PARAMETERS.area1 >= 0 && PARAMETERS.area2 >= 0);
        assert (PARAMETERS.e_pts >= 0 && PARAMETERS.f_pts >= 0);
        if (NUM_POINTS < 5) {
            return false;
        }
        boolean gtArea1 = false; //will be set to true if there is a triangle with area greater than area1
        boolean ltArea2 = false; //will be set to true if there is a triangle with area less than area2
        for (int i = 0; (i <= NUM_POINTS - PARAMETERS.e_pts - PARAMETERS.f_pts - 3); ++i) {
            // the three points a, b, c where: a and b have exactly e_pts points between
            // them and: b and c have exactly f_pts points between them
            // a,b,c are the vertex of a triangle
            double aX = X[i];
            double bX = X[i + PARAMETERS.e_pts + 1];
            double cX = X[i + PARAMETERS.e_pts + PARAMETERS.f_pts + 2];
            double aY = Y[i];
            double bY = Y[i + PARAMETERS.e_pts + 1];
            double cY = Y[i + PARAMETERS.e_pts + PARAMETERS.f_pts + 2];
            double area = computeTriangleArea(aX, aY, bX, bY, cX, cY);
            if (area > PARAMETERS.area1) {
                gtArea1 = true;
            }
            if (area < PARAMETERS.area2) {
                ltArea2 = true;
            }
        }

        return gtArea1 && ltArea2; //we return true if both condition are met.
    }

    /**
     * Returns the radius of the smallest circle containing all the points.
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
     * Returns the determinant of the given matrix:
     * a b c
     * d e f
     * g h i
    */
    public static double determinant(double a, double b, double c,
                                     double d, double e, double f,
                                     double g, double h, double i) {
        return a*(e*i-f*h) - b*(d*i-f*g) + c*(d*h-e*g);
    }

    /**
     * Returns the distance between two Point objects.
     */
    public static double distance(Point a, Point b) {
        return distance(a.x, a.y, b.x, b.y);
    }

    /**
     * Returns the distance between two points (x0,y0) and (x1, y1)
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

    /**
     * Returns the quadrant to which the point (x,y) belongs
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return an index between 0 and 3 included
     */
    public static int whichQuadrants(double x, double y) {
        if (y >= 0) {
            if (x >= 0) {
                return 0;
            } else { // x < 0
                return 1;
            }
        } else { // y < 0
            if (x <= 0) {
                return 2;
            } else { // x > 0
                return 3;
            }
        }
    }

    /**
     * Computes the angle between two lines defined by three points,
     * with the second point being the vertex.
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
        // law of cosines for triangles:
        //    c^2 = a^2 + b^2 − 2*a*b*cos(C)
        // => C = arccos((a^2 + b^2 - c^2)/(2*a*b))
        // where a,b,c are sides and C is the angle opposite c
        double A = distance(bX, bY, cX, cY);
        double B = distance(aX, aY, cX, cY);
        double C = distance(bX, bY, aX, aY);
        return Math.acos((C*C + A*A - B*B) / (2 * C * A));
    }

    /**
    * Computes the area of the triangle formed by the vertices (aX,aY), (bX,bY) and (cX,cY)
    *
    * @param aX the x coordinate of the first vertex
    * @param aY the y coordinate of the first vertex
    * @param bX the x coordinate of the second vertex
    * @param bY the y coordinate of the second vertex
    * @param cX the x coordinate of the third vertex
    * @param cY the y coordinate of the third vertex
    * @return the area of the triangle
    */
   public static double computeTriangleArea(double aX, double aY, double bX, double bY, double cX, double cY) {
       return Math.abs((aX * (bY - cY) + bX * (cY - aY) + cX * (aY - bY)) / 2);
   }
}
