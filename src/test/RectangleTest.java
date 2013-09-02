
package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shape.Point;
import shape.Rectangle;


public class RectangleTest {

	private Rectangle	rect;


	@Before
	public void init() {
		rect = new Rectangle(0, 0, 10, 10);
	}


	@After
	public void destroy() {
		rect = null;
	}


	@Test
	public void moveTest() {
		rect.move(4, 4);
		assertEquals(rect, new Rectangle(4, 4, 14, 14));
	}


	@Test
	public void moveToTest() {
		rect.moveTo(new Point(2, 2));
		assertEquals(rect, new Rectangle(-3, -3, 7, 7));
	}


	@Test
	public void scaleTest() {
		rect.scale(1.5);
		Rectangle rectScaled = new Rectangle(-2.5, -2.5, 12.5, 12.5);
		assertTrue(rect.equals(rectScaled));
	}


	@Test
	public void containsTest() {
		assertTrue(rect.contains(new Point(0.5, 0.5)));
		assertFalse(rect.contains(new Point(12, 12)));
	}


	@Test
	public void toStringTest() {
		assertTrue(rect.toString().contains(
			"Polygon.Rectangle {(0.0, 0.0), (10.0, 10.0), O(5.0, 5.0)}"));
	}


	@Test
	public void areaTest() {
		assertEquals(rect.getArea(), 100, 0);
	}


	@Test
	public void boundingBoxTest() {
		assertEquals(rect.boundingBox(), rect);
	}


	@Test
	public void rotationTest() {
		rect.rotate(Math.PI);
		assertTrue(rect.equals(new Rectangle(10, 10, 0, 0)));
	}
}
