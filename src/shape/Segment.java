
package shape;


/**
 * @author Óscar Gómez Alcañiz
 */
public class Segment extends Polygon {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4946201537651047496L;


	/**
	 * Un Polígono bastante pocho para comprobar la
	 * funcionalidad de los polígonos.
	 * 
	 * @param origin punto de origen.
	 * @param end punto de destino.
	 */
	public Segment(Point origin, Point end) {
		setPos(origin.mean(end));
		addVertex(origin);
		addVertex(end);
	}


	public Segment() {
		Point p = new Point(0, 0);
		setPos(p);
		addVertex(new Point(p));
		addVertex(new Point(p));
	}


	public double getLength() {
		return getVertex(0).distanceTo(getVertex(1));
	}


	public void setOrigin(Point o) {
		getVertex(0).setX(o.getX());
		getVertex(0).setY(o.getY());
	}


	public void setEnd(Point e) {
		getVertex(1).setX(e.getX());
		getVertex(1).setY(e.getY());
	}


	@Override
	public double getArea() {
		return 0;
	}


	@Override
	public String toString() {
		return super.toString() + ".Segment {" + getVertex(0) + ", "
				+ getVertex(1) + ", O" + getPos() + "}";

	}
}
