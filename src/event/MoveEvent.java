
package event;


import shape.Point;
import ui.ShapeUI;


public class MoveEvent implements Event {

	private String	description;
	private Point	offset;
	private ShapeUI	shape;


	public MoveEvent(String aDescription, ShapeUI aShape, Point offset) {
		this.description = aDescription;
		this.shape = aShape;
		this.offset = offset;
	}


	@Override
	public String getDescription() {
		return "MoveEvent: " + this.description;
	}


	public Point getOffset() {
		return this.offset;
	}


	public ShapeUI getShape() {
		return shape;
	}

}
