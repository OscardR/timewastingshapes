
package ui;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import shape.Segment;


public class Java2DSegmentDrawer extends Java2DPolygonDrawer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1969463408294055733L;


	public Java2DSegmentDrawer() {
		super();
	}


	private Segment getSegment() {
		return (Segment) getShapeUI().getShape();
	}


	@Override
	public void draw(Graphics2D g) {

		super.draw(g);

		if (getShapeUI().isSelected()) {

			g.setColor(Styles.SELECTION_COLOR);
			g.setStroke(Styles.SELECTION_STROKE);

			Rectangle2D rect = new Rectangle2D.Double(
				getSegment().boundingBox().getVertex(0).getX(),
				getSegment().boundingBox().getVertex(0).getY(),
				getSegment().boundingBox().getBase(),
				getSegment().boundingBox().getHeight());
			g.draw(rect);
		}
	}
}
