
package app;


import java.awt.Component;
import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

import shape.*;
import ui.*;


public class AppModel extends ModelState implements Model {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6676940641589468551L;

	private LinkedList<ShapeUI>	shapes;
	private static Model		instance;

	/**
	 * MVC
	 */
	private View				mainView;
	private Graphics			graphicsView;


	/**
	 * El constructor es privado, para forzar al modelo a
	 * instanciarse tan solo una vez.
	 * 
	 * @see Singleton
	 */
	private AppModel() {
		shapes = new LinkedList<ShapeUI>();

		setLastRotationOffset(0);
		setLastRotationValue(0);
		setLastScaleValue(1);
		setLastShape(null);
		setLastSelectedShapes(new LinkedList<ShapeUI>());

		/*
		 * Inicializar selectionCenter, nuesto objeto dummy
		 * que gestiona transformaciones para grupos de
		 * formas seleccionadas.
		 */
		setSelectionCenter(new CircleUI());
		getSelectionCenter().setDrawer(new Java2DCircleDrawer());
		getSelectionCenter().getDrawer().setColor(Styles.DEBUG_COLOR);
		setLastCenterPoint(getSelectionCenter().getPos());
	}


	/**
	 * El modelo es un Singleton, por lo que no puede
	 * instanciarse sino a través de un método
	 * getInstance(), que devolverá una referencia a la
	 * única instancia permitida del modelo que poseerá la
	 * aplicación.
	 * 
	 * @return una referencia al modelo.
	 * @see Singleton
	 */
	public static Model getInstance() {
		if (instance == null) {
			instance = new AppModel();
		}
		return instance;
	}


	public void setView(AppView theView) {

		this.mainView = theView;

		/*
		 * Por defecto los controles de figura están
		 * desactivados.
		 */
		setShapeControlsEnable(false);
		setDeleteEnable(false);
		setSaveEnable(false);
		setUndoEnable(false);

	}


	public void setGraphics(Graphics theGraphics) {
		this.graphicsView = theGraphics;
	}


	//
	// Métodos del Modelo:
	// *******************

	@Override
	public ShapeUI addShape(ShapeUI aShape) {
		shapes.add(aShape);
		setLastShape(aShape);
		setSaveEnable(true);
		setUndoEnable(true);
		updateGraphics();
		return aShape;
	}


	@Override
	public boolean removeShape(ShapeUI aShape) {
		setDeleteEnable(false);
		return shapes.remove(aShape);
	}


	@Override
	public int selectShape(Point newSelectionPoint, boolean multipleSelection) {
		/*
		 * Le pedimos seleccionar con tal punto, con la
		 * opción de selección múltiple actual.
		 */
		setLastShape(selectShapeWithPoint(newSelectionPoint, multipleSelection));
		if (getLastShape() != null) {
			// newSelectionPoint = getLastShape().getPos();
			getLastSelectedShapes().add(getLastShape());
		}

		/*
		 * En función de si la selección es múltiple, vamos
		 * calculando el punto de selección que es el punto
		 * central de las figuras seleccionadas, para poner
		 * los sliders en esa posición.
		 */

		StringBuilder tracker = new StringBuilder();

		tracker.append("No hay punto anterior, ");
		if (getLastSelectionPoint() == null)
			tracker.append("(ponemos el recién clickado) ");
		setLastSelectionPoint(newSelectionPoint);

		if (getLastShape() != null) {
			tracker.append("hay una forma clickada, ");
			if (getLastShape().isSelected()) {
				tracker.append("ha sido seleccionada, ");
				if (multipleSelection) {
					tracker.append("la selección es múltiple ");
					if (getSelectedShapes().size() > 1) {
						tracker.append("...y hay más de una forma seleccionada:");
						setLastSelectionPoint(calculateCenterOfSelection());
						setLastScaleValue(1);
						setLastRotationValue(0);
					} else {
						tracker.append("y sólo hay una forma seleccionada:");
						setLastSelectionPoint(newSelectionPoint);
						setLastShape(getSelectedShapes().getLast());
						setLastScaleValue((float) (getLastShape().getScaling() * 10 / 10));
						setLastRotationValue((int) Math.rint(Math.toDegrees(getLastShape().getRotation())));
					}
				} else {
					tracker.append("y la selección es simple: ");
					setLastSelectionPoint(newSelectionPoint);
					setLastShape(getSelectedShapes().getLast());
					setLastScaleValue((float) (getLastShape().getScaling() * 10 / 10));
					setLastRotationValue((int) Math.rint(Math.toDegrees(getLastShape().getRotation())));
				}
				tracker.append("activamos los controles de modificación.");
				setShapeControlsEnable(true);
				setDeleteEnable(true);
				setUndoEnable(true);
			} else {
				tracker.append("ha sido deseleccionada (sólo se puede deseleccionar con selecciones múltiples) ");
				if (getSelectedShapes().size() > 1) {
					tracker.append("y hay más de una figura seleccionada.");
					setLastSelectionPoint(calculateCenterOfSelection());
					setLastScaleValue(1);
					setLastRotationValue(0);
				} else {
					if (!getSelectedShapes().isEmpty()) {
						tracker.append("y sólo hay una figura seleccionada.");
						setLastShape(getSelectedShapes().getFirst());
						setLastSelectionPoint(getLastShape().getPos());
						setLastScaleValue((float) (getLastShape().getScaling() * 10 / 10));
						setLastRotationValue((int) Math.rint(Math.toDegrees(getLastShape().getRotation())));
					} else {
						tracker.append("y no hay ninguna figura seleccionada.");
						setShapeControlsEnable(false);
						setDeleteEnable(false);
						setUndoEnable(true);
					}
				}
			}
		} else {
			tracker.append("Se ha hecho click fuera de figuras, ");
			if (!multipleSelection) {
				tracker.append("y la selección es simple.");
				setLastSelectionPoint(new Point(
					graphicsView.getWidth() / 2,
					graphicsView.getHeight() / 2));
				setLastScaleValue(1);
				setLastRotationValue(0);
				setShapeControlsEnable(false);
				setDeleteEnable(false);
				setUndoEnable(true);
			}
		}
		// System.out.println(tracker);

		/*
		 * Actualizamos nuestra figura 'dummy' que
		 * representa el centro de la selección.
		 */
		getSelectionCenter().setPos(getLastSelectionPoint());
		getSelectionCenter().setScaling(getLastScaleValue());
		getSelectionCenter().setRotation(Math.toRadians(getLastRotationValue()));

		setLastCenterPoint(getLastSelectionPoint());

		updateMainView();
		updateGraphics();
		return getSelectedShapes().size();
	}


	@Override
	public ShapeUI selectShapeWithPoint(Point point, boolean retainSelection) {
		ShapeUI shapeToReturn = null;
		for (ShapeUI shape : shapes) {
			if (shape.pointMakesSelection(point)) {
				// Punto en la figura...
				if (retainSelection) {
					// Hay selecciones múltiples
					if (shape.isSelected()) {
						// Ya estaba seleccionada:
						// Deseleccionar
						shape.setSelected(false);
						shape.setSelectionCriteria(new SelectionByShape());
						// Retornar la figura
						return shape;
					} else {
						// No lo estaba:
						// Seleccionar
						shape.setSelected(true);
						shape.setSelectionCriteria(new SelectionByBoundingBox());
						// Retornar la figura.
						return shape;
					}
				} else {
					// NO hay selección múltiple.
					if (shapeToReturn == null) {
						// Si no hemos encontrado una ya:
						// La seleccionamos
						shape.setSelected(true);
						shape.setSelectionCriteria(new SelectionByBoundingBox());
						// Se pone la figura para devolver
						shapeToReturn = shape;
					} else {
						// de lo contrario la
						// deseleccionamos.
						shape.setSelected(false);
						shape.setSelectionCriteria(new SelectionByShape());
					}
				}
			} else {
				// Punto fuera de la figura
				if (!retainSelection) {
					// NO hay selección múltiple:
					// La deseleccionamos
					shape.setSelected(false);
					shape.setSelectionCriteria(new SelectionByShape());
				}
				// Siguiente figura...
			}
		}
		return shapeToReturn;
	}


	@Override
	public LinkedList<ShapeUI> selectShapesWithRectangle(
		RectangleUI rectangle,
		boolean retainSelection) {
		LinkedList<ShapeUI> selectedShapes = new LinkedList<ShapeUI>();
		for (ShapeUI shape : shapes) {
			if (shape.boundingBox().getPos().getX()
					- shape.boundingBox().getBase() / 2 > rectangle.getPos().getX()
					- rectangle.getBase() / 2
					&& shape.boundingBox().getPos().getX()
							+ shape.boundingBox().getBase() / 2 < rectangle.getPos().getX()
							+ rectangle.getBase() / 2
					&& shape.boundingBox().getPos().getY()
							- shape.boundingBox().getHeight() / 2 > rectangle.getPos().getY()
							- rectangle.getHeight() / 2
					&& shape.boundingBox().getPos().getY()
							+ shape.boundingBox().getHeight() / 2 < rectangle.getPos().getY()
							+ rectangle.getHeight() / 2) {
				if (!retainSelection) {
					shape.setSelected(true);
					selectedShapes.add(shape);
				} else {
					shape.setSelected(!shape.isSelected());
					if (shape.isSelected())
						selectedShapes.add(shape);
					else selectedShapes.remove(shape);
				}
			} else {
				if (!retainSelection) {
					shape.setSelected(false);
					selectedShapes.remove(shape);
				}
			}
		}
		return selectedShapes;
	}


	@Override
	public boolean pointIsInsideAnyShape(Point aPoint) {
		for (ShapeUI shape : shapes)
			if (shape.pointMakesSelection(aPoint)) {
				return true;
			}
		return false;
	}


	//
	// 'Hooks' para el Controller:
	// ***************************

	@Override
	public void startSelection(Point checkPoint) {
		setSelectionOriginPoint(checkPoint);
		setLastMousePoint(new Point(getSelectionOriginPoint()));
		if (!pointIsInsideAnyShape(checkPoint)) {

			setSelecting(true);

			/*
			 * Inicializar el rectángulo de selección, que
			 * ayuda al usuario a hacer selecciones
			 * múltiples.
			 */
			setSelectionSegment(new SegmentUI());
			getSelectionSegment().setDrawer(new Java2DSegmentDrawer());
			getSelectionSegment().getDrawer().setColor(Styles.GUIDE_COLOR);
			getSelectionSegment().getDrawer().setStroke(Styles.SELECTION_STROKE);

			getSelectionSegment().setOrigin(getSelectionOriginPoint());
			getSelectionSegment().setEnd(getSelectionOriginPoint());
		}
		updateGraphics();
	}


	@Override
	public boolean finishSelection(Point checkPoint, boolean multipleSelection) {
		setSelectionEndPoint(checkPoint);
		boolean wasSelecting = false;
		if (isSelecting()) {
			setLastSelectedShapes(selectShapesWithRectangle(
				getSelectionRectangle(),
				multipleSelection));
			setSelecting(false);
			wasSelecting = true;
		}
		if (!getSelectedShapes().isEmpty()) {
			setShapeControlsEnable(true);
			setDeleteEnable(true);
			setUndoEnable(true);
		} else {
			setShapeControlsEnable(false);
			setDeleteEnable(false);
			setUndoEnable(true);
		}
		updateGraphics();
		return wasSelecting;
	}


	@Override
	public void dragShapes(Point e) {
		/*
		 * Si hay posición de ratón, es decir, estamos
		 * dentro del panel de gráficos.
		 */
		if (graphicsView.getMousePosition() != null
				&& getLastMousePoint() != null) {
			// Si el ratón está dentro de una figura:
			if (pointIsInsideAnyShape(getLastMousePoint())
					&& getShapeUnderPoint(getLastMousePoint()) != null
					&& getShapeUnderPoint(getLastMousePoint()).isSelected()) {
				if (!isSelecting()) {
					// Calculamos el desplazamiento
					Point mouseOffset = new Point(e.getX()
							- getLastMousePoint().getX(), e.getY()
							- getLastMousePoint().getY());
					// Movemos el centro de selección
					getSelectionCenter().moveTo(
						new Point(
							getLastCenterPoint().getX() + mouseOffset.getX(),
							getLastCenterPoint().getY() + mouseOffset.getY()));

					setLastCenterPoint(getSelectionCenter().getPos());
				}
			}

			setLastMousePoint(e);

			if (isSelecting())
				getSelectionSegment().setEnd(getLastMousePoint());
		}
		updateMainView();
		updateGraphics();
	}


	private ShapeUI getShapeUnderPoint(Point point) {
		ShapeUI shapeToReturn = null;
		for (ShapeUI shape : shapes) {
			if (shape.pointMakesSelection(point)) {
				// Punto en la figura...
				// NO hay selección múltiple.
				if (shapeToReturn == null) {
					// Se pone la figura para devolver
					shapeToReturn = shape;
				}
			}
		}
		return shapeToReturn;
	}


	@Override
	public void deleteSelectedShapes() {

		List<ShapeUI> shapesForDeletion = new LinkedList<ShapeUI>();

		for (ShapeUI aShape : getSelectedShapes())
			shapesForDeletion.add(aShape);

		for (ShapeUI aShape : shapesForDeletion)
			removeShape(aShape);

		setLastSelectedShapes(shapesForDeletion);

		if (!shapesForDeletion.isEmpty())
			updateStatus("Borrada la figura " + getLastShape());
		else updateStatus("Selecciona primero una o varias figuras");

		setShapeControlsEnable(false);
		setSaveEnable(true);
		setUndoEnable(true);
		updateGraphics();
	}


	@Override
	public void moveSelectedShapes(double x, double y) {
		for (ShapeUI aShape : getSelectedShapes())
			aShape.move(x, y);
		setSaveEnable(true);
		setUndoEnable(true);
		updateGraphics();
	}


	@Override
	public void rotateSelectedShapes(double rotationOffset) {
		for (ShapeUI aShape : getSelectedShapes()) {
			aShape.rotate(rotationOffset);
			updateStatus("Figura rotada a "
					+ Math.toDegrees(aShape.getRotation()) + "º");
		}
		setSaveEnable(true);
		setUndoEnable(true);
		updateGraphics();
	}


	@Override
	public void scaleSelectedShapes(double factorOffset) {
		for (ShapeUI aShape : getShapes())
			if (aShape.isSelected()) {
				aShape.scale(factorOffset);
				updateStatus("Figura escalada a "
						+ Math.rint(aShape.getScaling() * 100) + "%.");
			}
		setSaveEnable(true);
		setUndoEnable(true);
		updateMainView();
		updateGraphics();
	}


	@Override
	public void moveSelectionCenterTo(Point destino) {
		getSelectionCenter().moveTo(destino);
		setSaveEnable(true);
		setUndoEnable(true);
		updateGraphics();
	}


	@Override
	public void scaleSelectionCenter(double currentScaleFactor) {
		double currentScaleOffset = currentScaleFactor / getLastScaleValue();

		getSelectionCenter().scale(currentScaleOffset);
		updateStatus("Escalado: " + currentScaleFactor);

		setLastScaleValue((float) currentScaleFactor);
		setLastScaleOffset((float) currentScaleOffset);

		setSaveEnable(true);
		setUndoEnable(true);
		updateGraphics();
	}


	@Override
	public void rotateSelectionCenter(int currentRotationValue) {
		int currentRotationOffset = currentRotationValue
				- getLastRotationValue();

		double radiansOffset = Math.toRadians(currentRotationOffset);
		getSelectionCenter().rotate(radiansOffset);
		updateStatus("Rotación: " + currentRotationOffset + "º ("
				+ radiansOffset + " radianes.");

		setLastRotationValue(currentRotationValue);
		setLastRotationOffset(currentRotationOffset);

		setSaveEnable(true);
		setUndoEnable(true);
		updateGraphics();

	}


	@Override
	public void reset() {
		shapes.clear();
		setOpenFile(null);
		setLastShape(null);
		setShapeControlsEnable(false);
		setSaveEnable(false);
		setUndoEnable(false);
		updateGraphics();
	}


	@Override
	public void loadState() throws IOException {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(mainView.getComponent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			setOpenFile(chooser.getSelectedFile());
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(getOpenFile().getAbsolutePath());
				ois = new ObjectInputStream(fis);
				Object objeto = ois.readObject();
				ois.close();
				setShapes(objeto);
			} catch (FileNotFoundException e) {
				System.out.println("No existe el archivo!");
			} catch (IOException e) {
				if (ois != null) {
					e.printStackTrace();
					System.out.println("No se pudo leer o escribir!");
				}
				fis.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				setLastShape(null);
				setShapeControlsEnable(false);
				setSaveEnable(false);
				setUndoEnable(false);
				updateGraphics();
			}
		}
	}


	@Override
	public void saveState() throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		if (getOpenFile() != null) {
			try {
				fos = new FileOutputStream(getOpenFile().getAbsolutePath());
				oos = new ObjectOutputStream(fos);
				oos.writeObject(getShapes());
				oos.flush();
				oos.close();
			} catch (FileNotFoundException e) {
				System.out.println("No existe el archivo!");
			} catch (IOException e) {
				if (oos != null)
					System.out.println("No se pudo leer o escribir!");
				fos.close();
			}
		} else {
			saveStateAs();
		}
	}


	@Override
	public void saveStateAs() throws IOException {
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(mainView.getComponent());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			setOpenFile(chooser.getSelectedFile());
			getOpenFile().createNewFile();
			getOpenFile().setWritable(true);
			saveState();
		}
	}


	@Override
	public void undoNewShape() {
		removeShape(getLastShape());
		setLastShape(null);
		if (getSelectedShapes().isEmpty())
			setShapeControlsEnable(false);
		setSaveEnable(true);
		setUndoEnable(false);
		updateGraphics();
	}


	@Override
	public void undoDelete() {
		if (!getLastSelectedShapes().isEmpty())
			for (ShapeUI aShape : getLastSelectedShapes())
				addShape(aShape);
		getLastSelectedShapes().clear();
		setDeleteEnable(true);
		setSaveEnable(true);
		setUndoEnable(false);
		updateGraphics();
	}


	@Override
	public void undoSelect() {
		if (!getLastSelectedShapes().isEmpty())
			for (ShapeUI aShape : getLastSelectedShapes())
				aShape.setSelected(false);
		setLastShape(null);
		setLastSelectedShapes(null);
		if (getSelectedShapes().isEmpty())
			setShapeControlsEnable(false);
		setSaveEnable(true);
		setUndoEnable(false);
		updateGraphics();
	}


	@Override
	public void undoMove() {
		double offsetX = getSelectionOriginPoint().getX()
				- getSelectionEndPoint().getX();
		double offsetY = getSelectionOriginPoint().getY()
				- getSelectionEndPoint().getY();
		if (pointIsInsideAnyShape(getLastMousePoint())) {
			for (ShapeUI aShape : getSelectedShapes()) {
				aShape.move(offsetX, offsetY);
			}
		}
		setSaveEnable(true);
		setUndoEnable(false);
		updateGraphics();
	}


	@Override
	public void undoRotate() {
		mainView.updateRotation(getLastRotationValue()
				- getLastRotationOffset());
		setLastRotationOffset(0);
		updateMainView();
		updateGraphics();
	}


	@Override
	public void undoScale() {
		mainView.updateScale(getLastScaleValue() / getLastScaleOffset());
		setLastScaleOffset(1);
		updateMainView();
		updateGraphics();
	}


	@Override
	public LinkedList<ShapeUI> getShapes() {
		return shapes;
	}


	@SuppressWarnings("unchecked")
	private void setShapes(Object readObject) {
		this.shapes = (LinkedList<ShapeUI>) readObject;
	}


	@Override
	public LinkedList<ShapeUI> getSelectedShapes() {
		LinkedList<ShapeUI> selectedShapes = new LinkedList<ShapeUI>();
		for (ShapeUI shape : shapes)
			if (shape.isSelected())
				selectedShapes.add(shape);
		return selectedShapes;
	}


	@Override
	public Point calculateCenterOfSelection() {
		if (getSelectedShapes().isEmpty())
			return null;
		Point p = getSelectedShapes().getLast().getPos();
		for (ShapeUI s : getSelectedShapes())
			p = p.mean(s.getPos());
		return p;
	}


	@Override
	public RectangleUI getSelectionRectangle() {
		RectangleUI rect = new RectangleUI(
			getSelectionSegment().getVertex(0),
			getSelectionSegment().getVertex(1));
		rect.setDrawer(new Java2DRectangleDrawer());
		rect.getDrawer().setPaint(getSelectionSegment().getDrawer().getPaint());
		rect.getDrawer().setColor(getSelectionSegment().getDrawer().getColor());
		rect.getDrawer().setStroke(
			getSelectionSegment().getDrawer().getStroke());
		return rect;
	}


	//
	// 'Hooks' para la Vista.
	// **********************

	@Override
	public void setShapeControlsEnable(boolean enabled) {
		mainView.setPositionEnable(enabled);
		mainView.setTransformEnable(enabled);
	}


	@Override
	public void setUndoEnable(boolean enabled) {
		mainView.setUndoEnable(enabled);
	}


	@Override
	public void setDeleteEnable(boolean enabled) {
		mainView.setDeleteEnable(enabled);
	}


	@Override
	public void setSaveEnable(boolean enabled) {
		mainView.setSaveEnable(enabled);
	}


	@Override
	public void updateMainView() {
		int h = (int) Math.rint(getSelectionCenter().getPos().getX() * 100
				/ graphicsView.getWidth());
		int v = (int) Math.rint(100 - getSelectionCenter().getPos().getY()
				* 100 / graphicsView.getHeight());
		mainView.updatePosition(h, v);
		mainView.updateScale((float) getSelectionCenter().getScaling() * 10 / 10);
		mainView.updateRotation((int) Math.rint(Math.toDegrees(getSelectionCenter().getRotation())));

		((Component) mainView).repaint();
	}


	@Override
	public void updateStatus(String string) {
		mainView.updateStatus(string);
	}


	@Override
	public void setView(View mainWindowView) {
		this.mainView = mainWindowView;

	}


	@Override
	public void updateGraphics() {
		graphicsView.repaint();
	}
}
