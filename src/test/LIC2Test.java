import static org.junit.Assert.*;

import org.junit.*;
import java.lang.Math;

import decide.*;

public class LIC2Test {

	@Before
	public void before() {
		Decide.PARAMETERS = new Parameters();
	}

	/**
	The LIC should be satisfied if three consecutive points form an angle which fulfills
	angle < (PI−EPSILON)
	or
	angle > (PI+EPSILON)
	 */
	@Test
	public void test_angle() {
		Decide.NUM_POINTS = 5;
		Decide.PARAMETERS.epsilon = 0;
		Decide.X = new double[]{0, 1, 2, 3, 4};
		Decide.Y = new double[]{0, 1, 2, 3, 4};
		assertEquals(Decide.LIC2(), false);

		Decide.X = new double[]{0, 1, 2, 1, 0};
		Decide.Y = new double[]{0, 1, 2, 3, 4};
		assertEquals(Decide.LIC2(), true);

		Decide.PARAMETERS.epsilon = 3*Math.PI/4;
		assertEquals(Decide.LIC2(), false);
	}

	/**
	If either of the first or the last point coincide with the vertex,
	the LIC should not satisfied for those points.
	 */
	@Test
	public void test_undefined_angle() {
		Decide.PARAMETERS.epsilon = 0;
		Decide.NUM_POINTS = 3;
		Decide.X = new double[]{0, 1, 1};
		Decide.Y = new double[]{0, 0, 0};
		assertEquals(Decide.LIC2(), false);

		Decide.X = new double[]{1, 1, -2};
		Decide.Y = new double[]{2, 2, 1};
		assertEquals(Decide.LIC2(), false);

		Decide.NUM_POINTS = 6;
		Decide.X = new double[]{0, 1, 2, 2, 2, 2};
		Decide.Y = new double[]{0, 0, 0, 0, 1, 2};
		assertEquals(Decide.LIC2(), false);
	}
}
