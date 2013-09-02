
package ui;


import shape.*;
import shape.Point;


public class TriangleUI extends PolygonUI {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7378839454630716957L;


	public TriangleUI(Point a, Point b, Point c) {
		super();
		setShape(new Triangle(a, b, c));
	}


	public TriangleUI(Point aPoint, double base, double height) {
		super();
		setShape(new Triangle(aPoint, base, height));
	}


	public TriangleUI(Point aPoint, double side) {
		super();
		setShape(new Triangle(aPoint, side));
	}


	public Triangle getTriangle() {
		return (Triangle) getPolygon();
	}
}
