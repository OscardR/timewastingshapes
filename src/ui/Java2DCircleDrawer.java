
package ui;


import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import shape.Circle;


public class Java2DCircleDrawer extends Java2DDrawer {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6399814039151710125L;


	public Java2DCircleDrawer() {
		super();
	}


	private Circle getCircle() {
		return (Circle) getShapeUI().getShape();
	}


	@Override
	public void draw(Graphics2D g) {
		if (getShapeUI().isSelected()) {

			/*
			 * Dibujar la selección:
			 */
			g.setColor(Styles.SELECTION_COLOR);
			g.setStroke(Styles.SELECTION_STROKE);

			Rectangle2D rect = new Rectangle2D.Double(
				getCircle().getPos().getX() - getCircle().getRadius(),
				getCircle().getPos().getY() - getCircle().getRadius(),
				getCircle().getRadius() * 2,
				getCircle().getRadius() * 2);
			g.draw(rect);
		}

		/*
		 * Dibujar el círculo:
		 */

		Ellipse2D elipse = new Ellipse2D.Double(
			getCircle().getPos().getX() - getCircle().getRadius(),
			getCircle().getPos().getY() - getCircle().getRadius(),
			getCircle().getRadius() * 2,
			getCircle().getRadius() * 2);

		g.setStroke(getStroke() != null ? getStroke() : Styles.SHAPE_STROKE);
		g.setPaint(Styles.SHAPE_FILL);
		g.fill(elipse);
		g.setColor(getColor());
		g.draw(elipse);
	}
}
