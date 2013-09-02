
package shape;


public class Rectangle extends Polygon {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7864111711024865485L;

	private double				base;
	private double				height;


	/**
	 * Constructor con los límites verticales y horizontales
	 * del rectángulo.
	 * 
	 * @param left valor x del lado izquierdo.
	 * @param top valor y del lado superior.
	 * @param right valor x del lado derecho.
	 * @param bottom valor y del lado inferior.
	 */
	public Rectangle(double left, double top, double right, double bottom) {

		Point topLeft = new Point(left, top);
		Point bottomRight = new Point(right, bottom);

		setPos(topLeft.mean(bottomRight));
		setBase(left - right);
		setHeight(top - bottom);

		addVertex(topLeft);
		addVertex(new Point(right, top));
		addVertex(bottomRight);
		addVertex(new Point(left, bottom));
	}


	/**
	 * Constructor con los vértices superior izquierdo e
	 * inferior derecho.
	 * 
	 * @param topLeft el vértice superior izquierdo.
	 * @param bottomRight el vértice inferior derecho.
	 */
	public Rectangle(Point topLeft, Point bottomRight) {

		setPos(topLeft.mean(bottomRight));
		setBase(topLeft.getX() - bottomRight.getX());
		setHeight(topLeft.getY() - bottomRight.getY());

		addVertex(topLeft);
		addVertex(new Point(bottomRight.getX(), topLeft.getY()));
		addVertex(bottomRight);
		addVertex(new Point(topLeft.getX(), bottomRight.getY()));
	}


	/**
	 * Constructor con la posición del rectángulo, la base y
	 * la altura.
	 * 
	 * @param pos el punto central del rectángulo.
	 * @param base dimensiones de la base.
	 * @param height dimensiones de la altura.
	 */
	public Rectangle(Point pos, double base, double height) {

		setPos(pos);
		setBase(base);
		setHeight(height);

		addVertex(new Point(getPos().getX() - getBase() / 2, getPos().getY()
				- getHeight() / 2));
		addVertex(new Point(getPos().getX() + getBase() / 2, getPos().getY()
				- getHeight() / 2));
		addVertex(new Point(getPos().getX() + getBase() / 2, getPos().getY()
				+ getHeight() / 2));
		addVertex(new Point(getPos().getX() - getBase() / 2, getPos().getY()
				+ getHeight() / 2));
	}


	@Override
	public double getArea() {
		return getBase() * getHeight();
	}


	@Override
	public void scale(double factor) {
		super.scale(factor);
	}


	/**
	 * @return the base
	 */
	public double getBase() {
		return base;
	}


	/**
	 * @param base the base to set
	 */
	public void setBase(double base) {
		this.base = Math.abs(base);
	}


	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = Math.abs(height);
	}


	@Override
	public String toString() {
		StringBuffer additionalVertices = new StringBuffer();
		if (getSize() > 4) {
			additionalVertices.append("\n\t- Additional Vertices: {");
			for (int i = 4; i < getSize(); i++) {
				additionalVertices.append(getVertex(i));
				if (i < getSize() - 1)
					additionalVertices.append(", ");
			}
			additionalVertices.append("}");
		}
		return super.toString() + ".Rectangle {" + getVertex(0) + ", "
				+ getVertex(2) + ", O" + getPos() + "} "
				+ additionalVertices.toString();
	}
}
