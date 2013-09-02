
package app;


public interface ModelForView {

	/**
	 * Deshabilitar los controles relacionados con figuras
	 * en la vista principal. Se usa cuando no hay figuras
	 * seleccionadas.
	 */
	void setShapeControlsEnable(boolean enable);


	/**
	 * Actualizar la línea de status en la vista principal.
	 * 
	 * @param string el mensaje de status que se mostrará.
	 */
	void updateStatus(String string);


	/**
	 * Actualizar los controles en la vista principal.
	 */
	void updateMainView();


	/**
	 * Redibujar la vista de gráficos (panel de dibujo)
	 */
	void updateGraphics();


	/**
	 * Deshabilita los controles relacionados con deshacer
	 * en la vista principal. Se usa cuando no se puede
	 * deshacer porque ya se ha deshecho la última acción.
	 * 
	 * @param enabled
	 */
	void setUndoEnable(boolean enabled);


	/**
	 * Deshabilita los controles relacionados con borrar en
	 * la vista principal. Se usa cuando no hay una
	 * selección y por lo tanto no puede borrarse nada.
	 * 
	 * @param enabled
	 */
	void setDeleteEnable(boolean enabled);


	/**
	 * Deshabilita los controles relacionados con salvar el
	 * estado en la vista principal. Se usa cuando no hay
	 * figuras y por lo tanto no se puede guardar, o cuando
	 * se acaba de guardar y no han habido cambios.
	 * 
	 * @param enabled
	 */
	void setSaveEnable(boolean enabled);
}
