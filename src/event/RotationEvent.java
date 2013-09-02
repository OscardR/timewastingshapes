
package event;


import ui.ShapeUI;


public class RotationEvent implements Event {

	private String	description;
	private ShapeUI	theShape;
	private double	rotation;
	private double	rotationOffset;


	public RotationEvent(
		String aDescription,
		ShapeUI aShape,
		double theRotationOffset) {
		this.description = aDescription;
		this.theShape = aShape;
		this.rotation = aShape.getRotation();
		this.rotationOffset = theRotationOffset;
	}


	@Override
	public String getDescription() {
		return "RotationEvent: " + this.description;
	}


	/**
	 * @return la figura que ha sido rotada.
	 */
	public ShapeUI getShape() {
		return theShape;
	}


	/**
	 * @return la rotación actual de la figura, en radianes.
	 */
	public double getRotation() {
		return this.rotation;
	}


	/**
	 * @return el incremento de rotación, en radianes.
	 */
	public double getRotationOffset() {
		return rotationOffset;
	}
}
