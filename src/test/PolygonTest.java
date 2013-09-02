/**
 * 
 */

package test;


import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import shape.*;


/**
 * @author oscar
 */
public class PolygonTest {

	Polygon	rect, tri, seg;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rect = new Rectangle(new Point(0, 0), new Point(10, 10));
		tri = new Triangle(new Point(0, 0), new Point(0, 10), new Point(10, 10));
		seg = new Segment(new Point(0, 0), new Point(10, 10));
	}


	/**
	 * Test method for {@link shape.Polygon#boundingBox()}.
	 */
	@Test
	public void testBoundingBox() {
		assertTrue(rect.boundingBox().equals(tri.boundingBox()));
		assertTrue(tri.boundingBox().equals(seg.boundingBox()));
	}


	/**
	 * Test method for
	 * {@link shape.Polygon#move(double, double)}.
	 */
	@Test
	public void testMove() {
		rect.move(4, 4);
		tri.move(4, 4);
		seg.move(4, 4);
		assertTrue(rect.equals(new Rectangle(4, 4, 14, 14)));
		assertTrue(tri.equals(new Triangle(
			new Point(4, 4),
			new Point(4, 14),
			new Point(14, 14))));
		assertTrue(seg.equals(new Segment(new Point(4, 4), new Point(14, 14))));
	}


	/**
	 * Test method for
	 * {@link shape.Polygon#moveTo(shape.Point)}.
	 */
	@Test
	public void testMoveTo() {
		rect.moveTo(new Point(4, 4));
		tri.moveTo(new Point(3, 3));
		seg.moveTo(new Point(5, 5));

		assertTrue(rect.equals(new Rectangle(-1, -1, 9, 9)));

		assertTrue(tri.equals(new Triangle(
			new Point(-2, -2),
			new Point(-2, 8),
			new Point(8, 8))));

		assertFalse(seg.equals(new Segment(new Point(4, 4), new Point(14, 14))));

	}


	/**
	 * Test method for {@link shape.Polygon#scale(double)}.
	 */
	@Test
	public void testScale() {
		rect.scale(1.2);
		assertTrue(rect.equals(new Rectangle(-1, -1, 11, 11)));
	}


	/**
	 * Test method for {@link shape.Polygon#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.println(rect);
		System.out.println(tri);
		System.out.println(seg);
	}


	/**
	 * Test method for
	 * {@link shape.Polygon#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		/*
		 * Un rectángulo siempre es igual a un nuevo
		 * rectángulo con sus mismas dimensiones.
		 */
		assertTrue(rect.equals(new Rectangle(0, 0, 10, 10)));

		/*
		 * Creamos un polígono idéntico al rectángulo.
		 */
		Polygon polygon = new Polygon();
		polygon.addVertex(new Point(0, 0));
		polygon.addVertex(new Point(10, 0));
		polygon.addVertex(new Point(10, 10));
		polygon.addVertex(new Point(0, 10));

		/*
		 * Los vértices coinciden:
		 */
		assertEquals(rect.getVertices(), polygon.getVertices());

		/*
		 * Pero no es el mismo objeto:
		 */
		assertFalse(rect.equals(polygon));
	}


	/**
	 * Test method for {@link shape.Polygon#rotate(double)}.
	 */
	@Test
	public void testRotate() {
		rect.rotate(Math.PI);
		assertTrue(rect.equals(new Rectangle(new Point(10, 10), new Point(0, 0))));
	}


	/**
	 * Test method for {@link shape.Polygon#getSize()}.
	 */
	@Test
	public void testGetSize() {
		assertTrue(rect.getSize() == 4);
		assertTrue(tri.getSize() == 3);
		assertTrue(seg.getSize() == 2);
	}


	/**
	 * Test method for
	 * {@link shape.Polygon#addVertex(shape.Point)}.
	 */
	@Test
	public void testAddVertex() {
		Point newPoint = new Point(5, 5);
		rect.addVertex(newPoint);
		assertTrue(rect.getSize() == 5);
		assertTrue(rect.toString().contains("Additional Vertices"));
	}


	/**
	 * Test method for {@link shape.Polygon#getVertex(int)}.
	 */
	@Test
	public void testGetVertex() {
		assertTrue(rect.getVertex(0).equals(new Point(0, 0)));
		assertTrue(rect.getVertex(1).equals(new Point(10, 0)));
	}


	/**
	 * Test method for {@link shape.Polygon#getVertices()}.
	 */
	@Test
	public void testGetVertices() {
		assertTrue(rect.getVertices().size() == 4);
		rect.getVertices().clear();
		assertTrue(rect.getVertices().isEmpty());
	}


	/**
	 * Test method for
	 * {@link shape.Polygon#setVertices(java.util.List)}.
	 */
	@Test
	public void testSetVertices() {
		LinkedList<Point> puntos = new LinkedList<Point>();
		puntos.add(new Point(2, 2));
		puntos.add(new Point(12, 2));
		puntos.add(new Point(12, 12));
		puntos.add(new Point(2, 12));
		rect.setVertices(puntos);
		assertTrue(rect.equals(new Rectangle(2, 2, 12, 12)));
	}


	/**
	 * Test method for {@link shape.Polygon#getPos()}.
	 */
	@Test
	public void testGetPos() {
		assertEquals(rect.getPos(), new Point(5, 5));
	}


	/**
	 * Test method for
	 * {@link shape.Polygon#setPos(shape.Point)}.
	 */
	@Test
	public void testSetPos() {
		rect.setPos(new Point(6, 6));
		assertTrue(rect.equals(new Rectangle(0, 0, 10, 10)));
		assertFalse(rect.getPos().equals(new Rectangle(0, 0, 10, 10).getPos()));
	}

}
