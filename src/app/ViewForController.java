package app;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

public interface ViewForController {

	/**
	 * Un listener para mandar el comando 'nuevo lienzo' al controlador.
	 * 
	 * @param listener
	 *            un listener que obedezca al comando NEW_CANVAS.
	 */
	public void addNewCanvasListener(ActionListener listener);

	/**
	 * Un listener para mandar el comando 'nuevo círculo' al controlador.
	 * 
	 * @param listener
	 *            un listener que obedezca al comando NEW_CIRCLE.
	 */
	public void addNewCircleListener(ActionListener listener);

	/**
	 * Un listener para mandar el comando 'nuevo rectángulo' al controlador.
	 * 
	 * @param listener
	 *            un listener que obedezca al comando NEW_RECTANGLE.
	 */
	public void addNewRectangleListener(ActionListener listener);

	/**
	 * Un listener para mandar el comando 'nuevo triángulo' al controlador.
	 * 
	 * @param listener
	 *            un listener que obedezca al comando NEW_TRIANGLE.
	 */
	public void addNewTriangleListener(ActionListener listener);

	/**
	 * Un listener para mandar el comando 'nuevo segmento' al controlador.
	 * 
	 * @param listener
	 *            un listener que obedezca al comando NEW_SEGMENT.
	 */
	public void addNewSegmentListener(ActionListener listener);

	/**
	 * Un listener para mandar el comando 'salir' al controlador.
	 * 
	 * @param listener
	 *            un listener que obedezca al comando EXIT.
	 */
	public void addExitListener(ActionListener listener);

	/**
	 * Un listener para mandar el comando 'deshacer' al controlador.
	 * 
	 * @param listener
	 *            un listener que obedezca al comando UNDO.
	 */
	public void addUndoListener(ActionListener listener);

	/**
	 * Un listener para mandar el comando 'borrar' al controlador.
	 * 
	 * @param listener
	 *            un listener que produzca el comando DELETE.
	 */
	public void addDeleteListener(ActionListener listener);

	/**
	 * Un listener para mandar cambios de rotación al controlador.
	 * 
	 * @param listener
	 *            un listener que produzca el comando ROTATE.
	 */
	public void addRotationListener(ChangeListener listener);

	/**
	 * Un listener para avisar de cambios de posición al controlador.
	 * 
	 * @param listener
	 *            un listener que responda a los cambios en la vista.
	 */
	public void addPositionListener(ChangeListener listener);

	/**
	 * Un listener para avisar de cambios de escala al controlador.
	 * 
	 * @param scalingListener
	 *            un listener que responda a los cambios en la vista.
	 */
	public void addScalingListener(ChangeListener scalingListener);

	public void addLoadListener(ActionListener commandListener);

	public void addSaveListener(ActionListener commandListener);

	public void addSaveAsListener(ActionListener commandListener);
}
