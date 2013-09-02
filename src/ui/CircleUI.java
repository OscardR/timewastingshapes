
package ui;


import shape.Circle;
import shape.Point;


public class CircleUI extends ShapeUI {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2333133760301302095L;


	public CircleUI(double x, double y, double r) {
		super();
		setShape(new Circle(x, y, r));
	}


	public CircleUI(Point p, double r) {
		super();
		setShape(new Circle(p, r));
	}


	public CircleUI() {
		super();
		setShape(new Circle());
	}


	public CircleUI(Circle aCircle) {
		super();
		setShape(aCircle);
	}


	/**
	 * <em>Método específico de Circle</em>
	 * <hr>
	 * Hacemos un casting de Circle al Shape contenido por
	 * esta figura GUI.
	 * 
	 * @return the circle
	 */
	public Circle getCircle() {
		return (Circle) getShape();
	}


	public double getRadius() {
		return getCircle().getRadius();
	}


	public void setRadius(double d) {
		getCircle().setRadius(d);
	}
}
