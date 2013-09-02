
package ui;


import shape.Point;
import shape.Polygon;


public abstract class PolygonUI extends ShapeUI {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6742895464041136226L;


	public PolygonUI() {
		super();
	}


	public Polygon getPolygon() {
		return (Polygon) getShape();
	}


	public Point getVertex(int v) {
		return getPolygon().getVertex(v);
	}
}
