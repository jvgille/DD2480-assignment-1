package main;

import static org.junit.Assert.*;

import org.junit.*;
import java.lang.Math;

public class UtilityTest {

	@Before
	public void before() {
		Decide.PARAMETERS = new Parameters();
	}

    /**
     * Test the utility "computeTriangleArea" function. It should be true that:
     * computeTriangleArea(A,B,C) ==
     * computeTriangleArea(A,C,B) and so one i.e computeTriangleArea is commutative
     */
    @Test
    public void test_triangle_area_1() {
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
    public void test_triangle_area_2() {// Test the utility computeTriangleArea with basic and corner cases
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
    public void test_distance() { // test the distance method for some points
        double[] x = { 0, 1, 0, 0, 0, 1 }; // coordinates of our points
        double[] y = { 0, 0, 0, 0, 0, 1 };
        double[] realDistances = { 1, 0, 1.414 }; // we compute the distance between (x[0],y[0]) and (x[1],y[1])
                                                  // then between (x[2],y[2]) and (x[3],y[3])...
        double[] distances = new double[3];
        boolean test = true;
        for (int i = 0; i < 3; ++i) {
            distances[i] = Decide.distance(x[2 * i], y[2 * i], x[2 * i + 1], y[2 * i + 1]);
            test = test && (Math.abs(distances[i] - realDistances[i]) < 0.001); // verifying that the computed distance
                                                                                // is the same as the real one
            test = test && (distances[i] == Decide.distance(x[2 * i + 1], y[2 * i + 1], x[2 * i], y[2 * i]));// verifying
                                                                                                                    // that
                                                                                                                    // the
                                                                                                                    // dist(A,B)
                                                                                                                    // ==
                                                                                                                    // dist(B,A)
        }

        assertTrue(test);
    }

    @Test
    public void test_angle() { // test the angle method for some points
        double[] x = { 1, 0, 0, 1, 0, 1, -1, 0, 1 }; // coordinates of our points
        double[] y = { 0, 0, 1, 1, 0, 0, 0, 0, 0 };
        double[] realAngles = { Math.PI / 2, Math.PI / 4, Math.PI }; // we compute the angle function using (x[0],y[0]),
        // (x[1],y[1]) and (x[2],y[2])
        // then using (x[3],y[3]), (x[4],y[4]) and (x[6],y[6]) ...
        double[] angles = new double[3];
        boolean test = true;
        for (int i = 0; i < 3; ++i) {
            angles[i] = Decide.computeAngle(x[3 * i], y[3 * i], x[3 * i + 1], y[3 * i + 1], x[3 * i + 2], y[3 * i + 2]);
            test = test && (Math.abs(angles[i] - realAngles[i]) < 0.001);
        }

        assertTrue(test);
    }

    @Test
    public void test_quadrant() { // Test the whichQuadrant function for corner and normal cases
        double[] x = { 0, -1, 0, 0, 1, 10, 10, -10, -10 }; // coordinates of our points
        double[] y = { 0, 0, -1, 1, 0, 10, -10, 10, -10 };
        int[] realIndexes = { 0, 1, 2, 0, 0, 0, 3, 1, 2 }; // quadrants in which they should belong
        int[] indexes = new int[x.length];
        boolean test = true;
        for (int i = 0; i < x.length; ++i) {
            indexes[i] = Decide.whichQuadrants(x[i], y[i]);
            test = test && (indexes[i] == realIndexes[i]); // verifying that our index is the same as the real one
        }

        assertTrue(test);
    }

    /**
    Decide.determinant() should calculate the determinant of the given matrix.
    */
	@Test
	public void test_determinant() {
        double epsilon = 0.00001;
		assertEquals(0, Decide.determinant(1,2,3,4,5,6,7,8,9), epsilon);
		assertEquals(-208, Decide.determinant(2,9,1,8,4,5,6,3,7), epsilon);
		assertEquals(237804, Decide.determinant(23,91,13,87,42,54,64,63,5), epsilon);
	}

    /**
    Decide.smallestCircle() should return the radius of the smallest circle which contains all the points.
    */
	@Test
	public void test_smallest_circle() {
        double epsilon = 0.00001;

        // one point inside circle
        Point[] points = new Point[]{new Point(0,0), new Point(1.1,0.9), new Point(2,2)};
		assertEquals(Math.sqrt(2), Decide.smallestCircle(points), epsilon);

        // all points on perimeter
        points = new Point[]{new Point(0,0), new Point(1,0), new Point(0.5, 1.2071)};
        assertEquals(1.0/Math.sqrt(2), Decide.smallestCircle(points), epsilon);
	}
}
