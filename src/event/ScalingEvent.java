
package event;


import ui.ShapeUI;


public class ScalingEvent implements Event {

	private String	description;
	private ShapeUI	shape;
	private double	factor;
	private double	factorOffset;


	public ScalingEvent(String aDescription, ShapeUI aShape, double factorOffset) {
		this.description = aDescription;
		this.shape = aShape;
		this.factor = aShape.getScaling();
		setFactorOffset(factorOffset);
	}


	@Override
	public String getDescription() {
		return "ScalingEvent: " + this.description;
	}


	public double getFactor() {
		return factor;
	}


	/**
	 * @return the shape
	 */
	public ShapeUI getShape() {
		return shape;
	}


	/**
	 * @return the factorOffset
	 */
	public double getFactorOffset() {
		return factorOffset;
	}


	/**
	 * @param factorOffset the factorOffset to set
	 */
	private void setFactorOffset(double factorOffset) {
		this.factorOffset = factorOffset;
	}

}
