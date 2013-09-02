
package ui;


import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import shape.Triangle;
import shape.Triangle.CenterType;


public class Java2DTriangleDrawer extends Java2DPolygonDrawer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8539198159874514705L;


	public Java2DTriangleDrawer() {
		super();
	}


	public Triangle getTriangle() {
		return (Triangle) getShapeUI().getShape();
	}


	@Override
	public void draw(Graphics2D g) {
		/*
		 * Dibuja el polígono que representa el triángulo.
		 */
		super.draw(g);

		/*
		 * Dibujar las líneas de selección
		 */
		if (getShapeUI().isSelected()) {
			g.setColor(Styles.SELECTION_COLOR);
			g.setStroke(Styles.SELECTION_STROKE);

			if (getShapeUI().getSelectionCriteria() instanceof SelectionByBoundingBox) {

				/*
				 * Con rectángulo:
				 */
				Rectangle2D selectionRectangle = new Rectangle2D.Double(
					getTriangle().boundingBox().getVertex(0).getX(),
					getTriangle().boundingBox().getVertex(0).getY(),
					getTriangle().boundingBox().getBase(),
					getTriangle().boundingBox().getHeight());
				g.draw(selectionRectangle);
			} else {

				/*
				 * Con elipse:
				 */
				Ellipse2D selectionEllipse = new Ellipse2D.Double(
					getTriangle().getCenter(CenterType.CIRCUMCENTER).getX()
							- getTriangle().getCircumRadius(),
					getTriangle().getCenter(CenterType.CIRCUMCENTER).getY()
							- getTriangle().getCircumRadius(),
					getTriangle().getCircumRadius() * 2,
					getTriangle().getCircumRadius() * 2);
				g.draw(selectionEllipse);

				/*
				 * Dibujamos las guías:
				 */
				g.setColor(Styles.GUIDE_COLOR);

				Line2D fromOtoA = new Line2D.Double(
					getTriangle().getPos().getX(),
					getTriangle().getPos().getY(),
					((PolygonUI) getShapeUI()).getPolygon().getVertex(0).getX(),
					((PolygonUI) getShapeUI()).getPolygon().getVertex(0).getY());
				Line2D fromOtoB = new Line2D.Double(
					getTriangle().getPos().getX(),
					getTriangle().getPos().getY(),
					((PolygonUI) getShapeUI()).getPolygon().getVertex(1).getX(),
					((PolygonUI) getShapeUI()).getPolygon().getVertex(1).getY());
				Line2D fromOtoC = new Line2D.Double(
					getTriangle().getPos().getX(),
					getTriangle().getPos().getY(),
					((PolygonUI) getShapeUI()).getPolygon().getVertex(2).getX(),
					((PolygonUI) getShapeUI()).getPolygon().getVertex(2).getY());
				g.draw(fromOtoA);
				g.draw(fromOtoB);
				g.draw(fromOtoC);
			}

		}
	}
}
