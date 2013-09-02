
package ui;


import java.io.Serializable;

import event.MoveEvent;
import event.RotationEvent;
import event.ScalingEvent;
import event.SelectionEvent;
import event.ShapeObservable;

import shape.*;


public abstract class ShapeUI extends ShapeObservable implements Shape,
	Drawable, Selectable, Serializable {

	/**
	 * Es necesario un serialVersionUID para poder
	 * serializar las formas y guardarlas:
	 */
	private static final long	serialVersionUID	= -5833010334279860618L;

	protected Shape				shape;
	protected SelectionCriteria	selectionCriteria;
	protected ShapeDrawer		drawer;

	protected boolean			selected;
	protected double			rotation;
	protected double			scale;


	public ShapeUI() {
		super();
		setSelectionCriteria(new SelectionByShape());
		setShape(null);
		setSelected(false);
		setScaling(1);
		setRotation(0);
	}


	//
	// ui.ShapeUI METHODS
	// ******************

	/**
	 * Obtener la rotación, en radianes.
	 * 
	 * @return la rotación actual de la figura.
	 */
	public double getRotation() {
		return rotation;
	}


	/**
	 * Setear la rotación, en radianes.
	 * 
	 * @param theRotation la rotación a setear.
	 */
	public void setRotation(double theRotation) {
		this.rotation = theRotation % (Math.PI * 2);
	}


	public double getScaling() {
		return this.scale;
	}


	public void setScaling(double scaleFactor) {
		this.scale = scaleFactor;
	}


	public Shape getShape() {
		return this.shape;
	}


	protected void setShape(Shape shape) {
		this.shape = shape;
	}


	//
	// ui.Selectable METHODS
	// *********************

	@Override
	public SelectionCriteria getSelectionCriteria() {
		return this.selectionCriteria;
	}


	@Override
	public void setSelectionCriteria(SelectionCriteria selectionCriteria) {
		this.selectionCriteria = selectionCriteria;
	}


	@Override
	public boolean pointMakesSelection(Point aPoint) {
		return selectionCriteria.select(aPoint, getShape());
	}


	@Override
	public boolean selectWithPoint(Point p) {
		if (pointMakesSelection(p)) {
			setSelected(true);
			return true;
		}
		setSelected(false);
		return false;
	}


	@Override
	public boolean isSelected() {
		return selected;
	}


	@Override
	public void setSelected(boolean isSelected) {
		if (isSelected)
			notifySelectObservers(new SelectionEvent(getShape().toString()
					+ " ha sido seleccionado", this));
		this.selected = isSelected;
	}


	//
	// shape.Shape METHODS
	// *******************

	@Override
	public Point getPos() {
		return getShape().getPos();
	}


	@Override
	public void setPos(Point newPos) {
		getShape().setPos(newPos);
	}


	@Override
	public double getArea() {
		return getShape().getArea();
	}


	@Override
	public Rectangle boundingBox() {
		return getShape().boundingBox();
	}


	@Override
	public boolean contains(Point p) {
		return getShape().contains(p);
	}


	@Override
	public void move(double x, double y) {
		getShape().move(x, y);
		notifyMoveObservers(new MoveEvent("La figura " + getShape().toString()
				+ " ha sido movida " + new Point(x, y), this, new Point(x, y)));
	}


	@Override
	public void moveTo(Point aPoint) {
		Point movementOffset = new Point(
			aPoint.getX() - getPos().getX(),
			aPoint.getY() - getPos().getY());
		getShape().moveTo(aPoint);
		notifyMoveObservers(new MoveEvent("La figura " + getShape().toString()
				+ " ha sido movida " + aPoint, this, movementOffset));
	}


	@Override
	public void rotate(double angleOffset) {
		getShape().rotate(angleOffset);
		setRotation(getRotation() + angleOffset);
		notifyRotationObservers(new RotationEvent("La figura "
				+ getShape().toString() + " se ha rotado en un incremento de "
				+ angleOffset + " radianes.", this, angleOffset));
	}


	@Override
	public void scale(double factorOffset) {
		getShape().scale(factorOffset);
		setScaling(factorOffset * getScaling());
		notifyScaleObservers(new ScalingEvent(
			"La figura " + getShape().toString()
					+ " se ha escalado en un factor " + factorOffset + "("
					+ factorOffset * 100 + "%)",
			this,
			factorOffset));
	}


	//
	// java.lang.Object METHODS
	// ************************

	@Override
	public boolean equals(Object anObject) {
		if (anObject == this)
			return true;
		else if (anObject == null)
			return true;
		else if (anObject.getClass() != getClass())
			return false;
		return getShape().equals(((ShapeUI) anObject).getShape());
	}


	@Override
	public String toString() {
		return "<UI>" + getShape().toString();
	}


	//
	// ui.Drawable METHODS
	// *******************

	@Override
	public ShapeDrawer getDrawer() {
		return this.drawer;
	}


	@Override
	public void setDrawer(ShapeDrawer theDrawer) {
		this.drawer = theDrawer;
		this.drawer.setDrawable(this);
	}
}
