
package event;


import ui.ShapeUI;


public class SelectionEvent implements Event {

	private String	description;
	private ShapeUI	theShape;


	public SelectionEvent(String aDescription, ShapeUI aShape) {
		this.description = aDescription;
		this.theShape = aShape;
	}


	@Override
	public String getDescription() {
		return "SelectionEvent: " + this.description;
	}


	public ShapeUI getShape() {
		return theShape;
	}

}
