package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shape.Circle;
import shape.Point;
import shape.Rectangle;

public class CircleTest {

	private Circle círculo;

	@Before
	public void init() {
		círculo = new Circle(0, 0, 1);
	}

	@After
	public void destroy() {
		círculo = null;
	}

	@Test
	public void constructorTest() {
		assertEquals(círculo, new Circle(new Point(0, 0), 1));
	}

	@Test
	public void moveByTest() {
		círculo.move(4, 4);
		assertEquals(círculo, new Circle(4, 4, 1));
	}

	@Test
	public void moveToTest() {
		círculo.moveTo(new Point(2, 2));
		assertEquals(círculo, new Circle(2, 2, 1));
	}

	@Test
	public void getPosTest() {
		assertEquals(círculo.getPos(), new Point(0, 0));
	}

	@Test
	public void scaleTest() {
		círculo.scale(1.5);
		assertEquals(círculo, new Circle(0, 0, 1.5));
	}

	@Test
	public void containsTest() {
		assertTrue(círculo.contains(new Point(0.5, 0.5)));
		assertFalse(círculo.contains(new Point(1.2, 1.2)));
	}

	@Test
	public void getAreaTest() {
		assertEquals(círculo.getArea(), Math.PI, 0.0d);
	}

	@Test
	public void boundingBoxTest() {
		assertEquals(círculo.boundingBox(), new Rectangle(-1, -1, 1, 1));
	}
}
