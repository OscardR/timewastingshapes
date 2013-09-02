
package app;


import java.io.Serializable;
import java.util.LinkedList;

import shape.Point;
import ui.RectangleUI;
import ui.ShapeUI;


public interface Model extends Serializable, ModelForView, ModelForController {

	/**
	 * El modelo necesita conocer a la vista.
	 * 
	 * @param mainWindowView
	 */
	public void setView(View mainWindowView);


	/**
	 * El modelo necesita conocer a los gráficos.
	 * 
	 * @param graphicsView
	 */
	public void setGraphics(Graphics graphicsView);


	/**
	 * Método para añadir una forma al modelo.
	 * 
	 * @param circle la forma a añadir. Ha de implementar la
	 *        interfaz Shape, por lo que puede ser cualquier
	 *        figura, bien de tipo básico o bien las que
	 *        tienen el sufijo UI, que poseen métodos para
	 *        observar y ser observadas.
	 * @return la forma que se añade, de forma que podemos
	 *         manipularla a posteriori si la creamos al
	 *         mismo tiempo que la añadimos.
	 */
	public ShapeUI addShape(ShapeUI circle);


	/**
	 * Método para eliminar una forma del modelo.
	 * 
	 * @param aShape la forma a eliminar.
	 * @return true si se encuentra en la lista.
	 */
	public boolean removeShape(ShapeUI aShape);


	/**
	 * Comprobar si un punto está dentro de alguna forma.
	 * 
	 * @param aPoint el punto a comprobar.
	 * @return true si lo está, false de lo contrario.
	 */
	public boolean pointIsInsideAnyShape(Point aPoint);


	/**
	 * Seleccionar formas con un punto determinado. Actúa de
	 * manera distinta dependiendo de si es una selección
	 * múltiple o una normal.
	 * 
	 * @param point el punto a usar para seleccionar.
	 * @param retainSelection true si se está haciendo una
	 *        selección múltiple.
	 * @return la última forma seleccionada.
	 */
	public ShapeUI selectShapeWithPoint(Point point, boolean retainSelection);


	/**
	 * Seleccionar formas con un rectángulo de selección.
	 * Calcula si las formas están contenidas dentro del
	 * área del rectángulo pasado como parámetro.
	 * 
	 * @param selectionRectangle el rectángulo que cubre las
	 *        formas.
	 * @return una lista con las formas seleccionadas.
	 */
	public LinkedList<ShapeUI> selectShapesWithRectangle(
		RectangleUI selectionRectangle,
		boolean retainSelection);


	//
	// app.Controller METHODS
	// **********************
	// Métodos para el controlador

	/**
	 * Comprobar si estamos en mitad de una selección.
	 * 
	 * @return true si se está trazando un rectángulo de
	 *         selección.
	 */
	public boolean isSelecting();


	/**
	 * Obtener el rectángulo de selección.
	 * 
	 * @return un rectángulo que cubre la selección actual
	 */
	public RectangleUI getSelectionRectangle();
}
