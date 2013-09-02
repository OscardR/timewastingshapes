
package ui;


import shape.Point;
import shape.Rectangle;


public class RectangleUI extends PolygonUI {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8573821566492799428L;


	public RectangleUI(double left, double top, double right, double bottom) {
		super();
		setShape(new Rectangle(left, top, right, bottom));
	}


	public RectangleUI(Point aPoint, double base, double height) {
		super();
		setShape(new Rectangle(aPoint, base, height));
	}


	public RectangleUI(Point topLeft, Point bottomRight) {
		super();
		setShape(new Rectangle(topLeft, bottomRight));
	}


	public RectangleUI(Rectangle aRect) {
		super();
		setShape(aRect);
	}


	/**
	 * <em><strong>nota:</strong> Método específico de Rectangle</em>
	 * <hr>
	 * 
	 * @return la base del rectángulo contenido.
	 */
	public double getBase() {
		return getRectangle().getBase();
	}


	/**
	 * <em><strong>nota:</strong> Método específico de Rectangle</em>
	 * <hr>
	 * 
	 * @return la altura del rectángulo contenido.
	 */
	public double getHeight() {
		return getRectangle().getHeight();
	}


	/**
	 * <em><strong>nota:</strong> Método específico de Rectangle</em>
	 * <hr>
	 * Retorna un casting a Rectangle del Shape interno.
	 * 
	 * @return the rectangle
	 */
	public Rectangle getRectangle() {
		return (Rectangle) getShape();
	}
}
