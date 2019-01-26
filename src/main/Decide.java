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
              for (int j = 0; i < 15; ++j) {
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

          assert(isSymmetric(PUM));

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

    /**
    * computes the area of the triangle formed by the vertices (aX,aY), (bX,bY) and
    * (cX,cY)
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

       if ((aX == cX && aY == cY) || (aX == bX && aY == bY) || (cX == bX && cY == bY)) {
           return 0; // two vertices are at the same point so the are should be 0
       }
       return Math.abs((aX * (bY - cY) + bX * (cY - aY) + cX * (aY - bY)) / 2);
   }
}
