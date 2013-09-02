
package ui;


import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import shape.Rectangle;


public class Java2DRectangleDrawer extends Java2DPolygonDrawer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6064032717102653915L;


	public Java2DRectangleDrawer() {
		super();
	}


	public Rectangle getRectangle() {
		return (Rectangle) getShapeUI().getShape();
	}


	@Override
	public void draw(Graphics2D g) {
		/*
		 * Dibujamos el polígono que define el rectángulo:
		 */
		super.draw(g);

		if (getShapeUI().isSelected()) {

			/*
			 * Dibujamos la selección:
			 */
			g.setColor(Styles.SELECTION_COLOR);
			g.setStroke(Styles.SELECTION_STROKE);

			int radius = (int) Math.round(getRectangle().getVertex(0).distanceTo(
				getRectangle().getPos()));
			if (getShapeUI().getSelectionCriteria() instanceof SelectionByShape) {
				Ellipse2D ellipse = new Ellipse2D.Double(
					getRectangle().getPos().getX() - radius,
					getRectangle().getPos().getY() - radius,
					radius * 2,
					radius * 2);
				g.draw(ellipse);
			} else {
				Rectangle2D rect = new Rectangle2D.Double(
					getRectangle().boundingBox().getVertex(0).getX(),
					getRectangle().boundingBox().getVertex(0).getY(),
					getRectangle().boundingBox().getBase(),
					getRectangle().boundingBox().getHeight());
				g.draw(rect);
			}
		}
	}
}
