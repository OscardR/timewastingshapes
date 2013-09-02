
package test;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shape.Point;


public class PointTest {

	private Point	punto;


	@Before
	public void init() {
		punto = new Point(0, 0);
	}


	@After
	public void destroy() {
		punto = null;
	}


	@Test
	public void constructorTest() {
		punto = new Point(-8.881784197001252E-16, -8.881784197001252E-16);
		assertEquals(new Point(), punto);
	}


	@Test
	public void gettersTest() {
		punto.setX(punto.getX() + 4);
		punto.setY(punto.getY() + 4);
		assertEquals(punto, new Point(4, 4));
	}


	@Test
	public void angleToTest() {
		assertEquals(Math.PI / 4, punto.angleTo(new Point(2, 2)), 0);
	}


	@Test
	public void distanceToTest() {
		assertEquals(Math.sqrt(2), punto.distanceTo(new Point(1, 1)), 0);
	}


	@Test
	public void meanTest() {
		assertEquals(new Point(1, 1), punto.mean(new Point(2, 2)));
	}
}
