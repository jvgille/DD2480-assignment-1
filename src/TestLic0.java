import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class TestLic0 {
	
	Decide d = new Decide();
	Parameters p;
	@Before
	public void prepare(){
		p = new Parameters();
		d.PARAMETERS = p;
		double[] x = {1.0,2.0,3.0};
		double[] y = {0.0,0.0,0.0};
		d.X = x;
		d.Y = y;
	}

	@Test
	public void test() {
		p.length1 = 1.0;
		assertFalse(d.LIC0());
	}
	
	@Test
	public void test2() {
		p.length1 = 1.000001;
		assertFalse(d.LIC0());
	}
	
	@Test
	public void test3() {
		p.length1 = 0.5;
		assertTrue(d.LIC0());
	}
}
