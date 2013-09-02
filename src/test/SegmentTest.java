
package test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shape.Point;
import shape.Segment;


public class SegmentTest {

	private Segment	seg;


	@Before
	public void init() {
		seg = new Segment(new Point(-1, 0), new Point(1, 0));
	}


	@After
	public void destroy() {
		seg = null;
	}


	@Test
	public void containsTest() {
		assertTrue(seg.contains(new Point(0, 0)));
	}


	@Test
	public void rotateTest() {
		seg.rotate(Math.PI / 2);
		assertTrue(seg.equals(new Segment(new Point(0, -1), new Point(0, 1))));
		seg.rotate(Math.PI / 2);
		assertTrue(seg.equals(new Segment(new Point(1, 0), new Point(-1, 0))));
	}


	@Test
	public void getSizeTest() {
		System.out.println(seg.getArea() + "\n" + seg.getSize() + "\n"
				+ seg.boundingBox());
	}


	@Test
	public void getLengthTest() {
		assertEquals(seg.getLength(), 2, 0.0);
	}
}
