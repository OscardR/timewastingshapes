
package ui;


import shape.Point;


public interface Selectable {

	/**
	 * Chequea si una figura quedaría seleccionada con el método de selección actual usando el punto pasado como parámetro.
	 * @param aPoint un punto a comprobar
	 * @return true si el punto selecciona la figura, false de lo contrario.
	 */
	public boolean pointMakesSelection(Point aPoint);


	/**
	 * Seleccionar una figura con un punto. Si el punto está
	 * contenido en la figura, esta ha de quedar
	 * seleccionada.
	 * 
	 * @param p un punto para seleccionar la figura.
	 * @return true si se selecciona, false de lo contrario.
	 */
	public boolean selectWithPoint(Point p);


	/**
	 * Getter para el estado de selección de la figura.
	 * 
	 * @return el valor de <em>selected</em>.
	 */
	public boolean isSelected();


	/**
	 * Setter para el estado de selección de la figura.
	 * 
	 * @param isSelected el valor para <em>selected</em>.
	 */
	void setSelected(boolean isSelected);


	/**
	 * Cualquier figura seleccionable ha de poder elegir un método de selección proporcionado por la interfaz SelectionCriteria.
	 */
	public void setSelectionCriteria(SelectionCriteria criteria);


	/**
	 * Cualquier figura seleccionable ha de hacer visible el método de selección actualmente en uso.
	 * @return el criterio de selección actualmente en uso.
	 */
	SelectionCriteria getSelectionCriteria();
}
