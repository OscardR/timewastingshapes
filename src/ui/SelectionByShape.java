
package ui;


import shape.Point;
import shape.Shape;


public class SelectionByShape implements SelectionCriteria {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5270751263217068452L;


	public boolean select(Point p, Shape s) {
		return s.contains(p);
	}
}
