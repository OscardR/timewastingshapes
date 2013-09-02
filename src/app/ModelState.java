
package app;


import java.io.File;
import java.util.List;

import shape.Point;
import ui.SegmentUI;
import ui.ShapeUI;


/**
 * Superclase para el modelo. Se usa para tener declaradas
 * las variables de estado y sus getters/setters, de forma
 * que aligeramos el cuerpo del modelo. La he hecho
 * abstracta para no poder instanciarla, pues por sí misma
 * es inútil.
 * 
 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
 */
public abstract class ModelState {

	/**
	 * Atributos de estado:
	 */
	private ShapeUI			lastShape;
	private List<ShapeUI>	lastSelectedShapes;
	private float			lastScaleValue;
	private double			lastScaleOffset;
	private int				lastRotationValue;
	private int				lastRotationOffset;
	private Point			lastSelectionPoint;
	private Point			lastMousePoint;
	private Point			selectionEndPoint;
	private Point			selectionOriginPoint;
	private File			openFile;

	/**
	 * Centro de selecciones. Necesario para operar con
	 * selecciones múltiples.
	 */
	private ShapeUI			selectionCenter;
	private Point			lastCenterPoint;

	/**
	 * Rectángulo de selección. Necesario para realizar
	 * selecciones múltiples mediante arrastre de ratón.
	 */
	private SegmentUI		selectionRectangle;
	private boolean			selecting;


	//
	// app.Model SETTERS & GETTERS
	// ***********************************

	protected ShapeUI getLastShape() {
		return this.lastShape;
	}


	protected void setLastShape(ShapeUI lastShape) {
		this.lastShape = lastShape;
	}


	protected List<ShapeUI> getLastSelectedShapes() {
		return this.lastSelectedShapes;
	}


	protected void setLastSelectedShapes(List<ShapeUI> lastSelectedShapes) {
		this.lastSelectedShapes = lastSelectedShapes;
	}


	protected float getLastScaleValue() {
		return this.lastScaleValue;
	}


	protected void setLastScaleValue(float lastScaleValue) {
		this.lastScaleValue = lastScaleValue;
	}


	protected double getLastScaleOffset() {
		return this.lastScaleOffset;
	}


	protected void setLastScaleOffset(double lastScaleOffset) {
		this.lastScaleOffset = lastScaleOffset;
	}


	protected int getLastRotationValue() {
		return this.lastRotationValue;
	}


	protected void setLastRotationValue(int lastRotationValue) {
		this.lastRotationValue = lastRotationValue;
	}


	protected int getLastRotationOffset() {
		return this.lastRotationOffset;
	}


	protected void setLastRotationOffset(int lastRotationOffset) {
		this.lastRotationOffset = lastRotationOffset;
	}


	protected Point getLastSelectionPoint() {
		return this.lastSelectionPoint;
	}


	protected void setLastSelectionPoint(Point lastSelectionPoint) {
		this.lastSelectionPoint = lastSelectionPoint;
	}


	protected Point getSelectionOriginPoint() {
		return this.selectionOriginPoint;
	}


	protected void setSelectionOriginPoint(Point selectionOriginPoint) {
		this.selectionOriginPoint = selectionOriginPoint;
	}


	protected Point getSelectionEndPoint() {
		return this.selectionEndPoint;
	}


	protected void setSelectionEndPoint(Point selectionEndPoint) {
		this.selectionEndPoint = selectionEndPoint;
	}


	protected Point getLastMousePoint() {
		return this.lastMousePoint;
	}


	protected void setLastMousePoint(Point lastMousePoint) {
		this.lastMousePoint = lastMousePoint;
	}


	protected Point getLastCenterPoint() {
		return this.lastCenterPoint;
	}


	protected void setLastCenterPoint(Point lastCenterPoint) {
		this.lastCenterPoint = lastCenterPoint;
	}


	protected SegmentUI getSelectionSegment() {
		return selectionRectangle;
	}


	protected void setSelectionSegment(SegmentUI selectionRectangle) {
		this.selectionRectangle = selectionRectangle;
	}


	public ShapeUI getSelectionCenter() {
		return this.selectionCenter;
	}


	public void setSelectionCenter(ShapeUI selectionCenter) {
		this.selectionCenter = selectionCenter;
	}


	public boolean isSelecting() {
		return selecting;
	}


	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
	}


	public File getOpenFile() {
		return openFile;
	}


	public void setOpenFile(File openFile) {
		this.openFile = openFile;
	}
}
