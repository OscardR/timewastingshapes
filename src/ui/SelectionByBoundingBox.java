
package ui;


import shape.Point;
import shape.Shape;


public class SelectionByBoundingBox implements SelectionCriteria {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5670117271225289447L;


	public boolean select(Point punto, Shape s) {
		Point p = (Point) punto;
		return s.boundingBox().contains(p);
	}
}
