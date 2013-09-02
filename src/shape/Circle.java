
package shape;


public class Circle implements Shape {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3837471762349148766L;


	public Circle(double x, double y, double r) {
		setPos(new Point(x, y));
		setRadius(r);
	}


	public Circle(Point p, double r) {
		setPos(p);
		setRadius(r);
	}


	public Circle() {
		setPos(new Point(0, 0));
		setRadius(0);
	}


	@Override
	public Rectangle boundingBox() {
		Point topLeft = new Point(getPos().x - getRadius(), getPos().y
				- getRadius());
		Point bottomRight = new Point(getPos().x + getRadius(), getPos().y
				+ getRadius());
		return new Rectangle(topLeft, bottomRight);
	}


	@Override
	public boolean contains(Point aPoint) {
		if (aPoint.distanceTo(getPos()) <= getRadius())
			return true;
		return false;
	}


	@Override
	public double getArea() {
		return Math.PI * getRadius() * getRadius();
	}


	@Override
	public Point getPos() {
		return new Point(this.pos);
	}


	/**
	 * @param aPoint the pos to set
	 */
	public void setPos(Point aPoint) {
		this.pos = aPoint;
	}


	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}


	/**
	 * Para radios negativos, coge el valor absoluto.
	 * 
	 * @param aRadius the radius to set
	 */
	public void setRadius(double aRadius) {
		this.radius = Math.abs(aRadius);
	}


	@Override
	public void move(double h, double v) {
		Point currentPos = getPos();
		currentPos.x += h;
		currentPos.y += v;
		setPos(currentPos);
	}


	@Override
	public void moveTo(Point aPoint) {
		setPos(new Point(aPoint));
	}


	@Override
	public void rotate(double angleOffset) {
		// NOTA: Cuando rotas un círculo no pasa nada.
	}


	@Override
	public void scale(double d) {
		setRadius(getRadius() * d);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (other.getPos().equals(this.getPos())
				&& other.getRadius() == this.getRadius())
			return true;
		return false;
	}


	public String toString() {
		return "Circle at (" + getPos().x + "," + getPos().y + ") with radius "
				+ getRadius();
	}


	private double	radius;
	private Point	pos;
}
