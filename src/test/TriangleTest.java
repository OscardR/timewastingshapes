
package test;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shape.Point;
import shape.Shape;
import shape.Triangle;


public class TriangleTest {

	private Shape	triángulo;


	@Before
	public void init() {
		triángulo = new Triangle(new Point(0, 0), 6);
	}


	@After
	public void destroy() {
		triángulo = null;
	}


	@Test
	public void equalsTest() {
		Shape triángulo2 = new Triangle(new Point(0, 0), 6.005);
		assertTrue(triángulo.equals(triángulo2));
	}


	@Test
	public void constructoresTest() {

		Shape triánguloEq = new Triangle(new Point(0, 0), 6);
		Shape triánguloIso = new Triangle(new Point(0, 0), 6, 5.196d);
		Shape triánguloEsc = new Triangle(new Point(-3, -1.732d), new Point(
			3,
			-1.732d), new Point(0, 3.464d));

		assertTrue(triánguloEq.equals(triánguloIso));
		assertTrue(triánguloIso.equals(triánguloEsc));

	}

}
