
package ui;


import shape.Point;
import shape.Segment;


public class SegmentUI extends PolygonUI {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4758865093204458487L;


	public SegmentUI(Point pointA, Point pointB) {
		super();
		setShape(new Segment(pointA, pointB));
		setSelectionCriteria(new SelectionByBoundingBox());
	}


	public SegmentUI() {
		super();
		setShape(new Segment());
		setSelectionCriteria(new SelectionByBoundingBox());
	}


	public Segment getSegment() {
		return (Segment) getShape();
	}


	public void setOrigin(Point o) {
		getSegment().setOrigin(o);
	}


	public void setEnd(Point e) {
		getSegment().setEnd(e);
	}
}
