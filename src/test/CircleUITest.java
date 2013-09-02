/**
 * 
 */

package test;


import static org.junit.Assert.*;

import org.junit.*;

import shape.*;
import ui.CircleUI;
import ui.SelectionByBoundingBox;


/**
 * @author Óscar
 */
public class CircleUITest {

	private CircleUI	circle;


	@Before
	public void init() {
		circle = new CircleUI();
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#CircleUI(double, double, double)}.
	 */
	@Test
	public void testCircleUIDoubleDoubleDouble() {
		CircleUI nuevo = new CircleUI(0, 0, 0);
		assertTrue(circle.equals(nuevo));
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#CircleUI(shape.Point, double)}.
	 */
	@Test
	public void testCircleUIPointDouble() {
		CircleUI nuevo = new CircleUI(new Point(0, 0), 0);
		assertTrue(circle.equals(nuevo));
	}


	/**
	 * Test method for {@link ui.CircleUI#CircleUI()}.
	 */
	@Test
	public void testCircleUI() {
		CircleUI nuevo = new CircleUI(0, 0, 0);
		assertTrue(circle.equals(nuevo));
	}


	/**
	 * Test method for {@link ui.CircleUI#boundingBox()}.
	 */
	@Test
	public void testBoundingBox() {
		circle.setRadius(1);
		Rectangle boundingBox = new Rectangle(-1, -1, 1, 1);
		assertTrue(circle.boundingBox().equals(boundingBox));
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#contains(shape.Point)}.
	 */
	@Test
	public void testContains() {
		circle.setRadius(1);
		assertTrue(circle.contains(new Point(0.5, 0.5)));
	}


	/**
	 * Test method for {@link ui.CircleUI#getArea()}.
	 */
	@Test
	public void testGetArea() {
		assertTrue(circle.getArea() == 0);
		circle.setRadius(1);
		assertTrue(circle.getArea() == Math.PI);
	}


	/**
	 * Test method for {@link ui.CircleUI#getPos()}.
	 */
	@Test
	public void testGetPos() {
		assertTrue(circle.getPos().equals(new Point(0, 0)));
	}


	/**
	 * Test method for {@link ui.CircleUI#getRadius()}.
	 */
	@Test
	public void testGetRadius() {
		assertTrue("Radio: " + circle.getRadius(), circle.getRadius() == 0.0d);
		circle.setRadius(1d);
		assertTrue("Radio: " + circle.getRadius(), circle.getRadius() == 1.0d);
		circle.setRadius(-1d);
		assertTrue("Radio: " + circle.getRadius(), circle.getRadius() == 1.0d);
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#selectWithPoint(shape.Point)}.
	 */
	@Test
	public void testSelectWithPoint() {
		assertTrue(circle.selectWithPoint(new Point(0, 0)));
	}


	/**
	 * Test method for {@link ui.CircleUI#isSelected()}.
	 */
	@Test
	public void testIsSelected() {
		if (circle.selectWithPoint(new Point(0, 0))) circle.setSelected(true);
		assertTrue(circle.isSelected());
		if (!circle.selectWithPoint(new Point(0, 1))) circle.setSelected(false);
		assertFalse(circle.isSelected());
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#setSelected(boolean)}.
	 */
	@Test
	public void testSetSelected() {
		circle.setSelected(true);
		assertTrue(circle.isSelected());
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#setSelectionCriteria(ui.SelectionCriteria)}
	 * .
	 */
	@Test
	public void testSetSelectionCriteria() {
		Point point = new Point(8, 8);
		circle.setRadius(9);
		assertFalse(circle.selectWithPoint(point));
		circle.setSelectionCriteria(new SelectionByBoundingBox());
		assertTrue(circle.selectWithPoint(point));
	}


	/**
	 * Test method for {@link ui.CircleUI#setRadius(double)}
	 * .
	 */
	@Test
	public void testSetRadius() {
		circle.setRadius(10);
		assertEquals(circle.getRadius(), 10, 0);
		circle.setRadius(-10);
		assertEquals(circle.getRadius(), 10, 0);
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#move(double, double)}.
	 */
	@Test
	public void testMove() {
		circle.move(4, 4);
		assertTrue(circle.toString(), circle.equals(new CircleUI(4, 4, 0)));
		circle.move(-4, -4);
		assertTrue(circle.toString(), circle.equals(new CircleUI(0, 0, 0)));
	}


	/**
	 * Test method for
	 * {@link ui.CircleUI#moveTo(shape.Point)}.
	 */
	@Test
	public void testMoveTo() {
		circle.moveTo(new Point(4, 4));
		assertTrue(circle.toString(), circle.equals(new CircleUI(4, 4, 0)));
		circle.moveTo(new Point(-4, -4));
		assertTrue(circle.toString(), circle.equals(new CircleUI(-4, -4, 0)));
	}


	/**
	 * Test method for {@link ui.CircleUI#rotate(double)}.
	 */
	@Test
	public void testRotate() {
		circle.rotate(Math.PI);
		assertEquals(circle.getRotation(), Math.PI, 0);
		circle.rotate(Math.PI);
		assertEquals(circle.getRotation(), 0, 0);
	}


	/**
	 * Test method for {@link ui.CircleUI#scale(double)}.
	 */
	@Test
	public void testScale() {
		circle.setRadius(1);
		circle.scale(2);
		assertTrue(circle.equals(new CircleUI(0, 0, 2)));
		circle.scale(.5);
		assertTrue(circle.equals(new CircleUI(0, 0, 1)));
	}

}
