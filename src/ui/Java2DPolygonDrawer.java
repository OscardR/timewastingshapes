
package ui;


import java.awt.Graphics2D;

import shape.Point;
import shape.Polygon;


public class Java2DPolygonDrawer extends Java2DDrawer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -501545831826026518L;


	public Java2DPolygonDrawer() {
		super();
	}


	private Polygon getPolygon() {
		return ((PolygonUI) getShapeUI()).getPolygon();
	}


	@Override
	public void draw(Graphics2D g) {
		g.setPaint(getPaint());
		int numVertices = getPolygon().getSize();
		int verticesX[] = new int[numVertices];
		int verticesY[] = new int[numVertices];
		int i = 0;
		for (Point p : getPolygon().getVertices()) {
			verticesX[i] = (int) Math.rint(p.getX());
			verticesY[i] = (int) Math.rint(p.getY());
			i++;
		}
		g.fillPolygon(verticesX, verticesY, numVertices);

		g.setColor(getColor());
		g.setStroke(getStroke() != null ? getStroke() : Styles.SHAPE_STROKE);
		g.drawPolygon(verticesX, verticesY, numVertices);
	}
}
