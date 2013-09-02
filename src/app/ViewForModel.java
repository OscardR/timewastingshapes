package app;

public interface ViewForModel {

	/**
	 * Actualiza el status en la ventana.
	 * 
	 * @param string
	 *            el mensaje a mostrar.
	 */
	public void updateStatus(String string);

	/**
	 * Actualizar la posición.
	 * 
	 * @param horizontalPosition
	 *            la posición horizontal.
	 * @param verticalPosition
	 *            la posición vertical.
	 */
	public void updatePosition(int horizontalPosition, int verticalPosition);

	/**
	 * Actualizar la escala.
	 * 
	 * @param theFactor
	 *            el factor de escala actual.
	 */
	public void updateScale(double theFactor);

	/**
	 * Actualizar la rotación.
	 * 
	 * @param theAngle
	 *            el ángulo que poner en la vista.
	 */
	public void updateRotation(int theAngle);

	/**
	 * Activar/desactivar los controles de posición.
	 * 
	 * @param enabled
	 */
	public void setPositionEnable(boolean enabled);

	/**
	 * Activar/desactivar los controles de transformación.
	 * 
	 * @param enabled
	 */
	public void setTransformEnable(boolean enabled);

	/**
	 * Activar/desactivar los controles de deshacer.
	 * 
	 * @param enabled
	 */
	public void setUndoEnable(boolean enabled);

	/**
	 * Activar/desactivar los controles de borrado.
	 * 
	 * @param enabled
	 */
	public void setDeleteEnable(boolean enabled);

	/**
	 * Activar/desactivar los controles de salvado.
	 * 
	 * @param enabled
	 */
	public void setSaveEnable(boolean enabled);

}
