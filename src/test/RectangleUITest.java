
package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shape.Point;
import ui.RectangleUI;


public class RectangleUITest {

	private RectangleUI	rect;


	@Before
	public void init() {
		rect = new RectangleUI(0, 0, 10, 10);
	}


	@After
	public void destroy() {
		rect = null;
	}


	@Test
	public void moveByTest() {
		rect.move(4, 4);
		assertTrue(rect.equals(new RectangleUI(4, 4, 14, 14)));
	}


	@Test
	public void moveToTest() {
		rect.moveTo(new Point(7, 7));
		RectangleUI rect2 = new RectangleUI(2, 2, 12, 12);
		assertTrue(rect.equals(rect2));
	}


	@Test
	public void scaleTest() {
		rect.scale(1.5);
		RectangleUI rect2 = new RectangleUI(-2.5, -2.5, 12.5, 12.5);
		assertTrue(rect.equals(rect2));
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
		assertEquals(rect.boundingBox(), rect.getRectangle());
	}


	@Test
	public void rotationTest() {
		rect.rotate(Math.PI);
		assertTrue(rect.equals(new RectangleUI(10, 10, 0, 0)));
	}
}
