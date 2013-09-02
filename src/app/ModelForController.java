
package app;


import java.io.IOException;
import java.util.LinkedList;

import shape.Point;
import ui.ShapeUI;


public interface ModelForController {

	/**
	 * Comenzar una selección.
	 * 
	 * @param checkPoint punto a partir del cual crear el
	 *        rectángulo de selección.
	 */
	public void startSelection(Point checkPoint);


	/**
	 * Finalizar una selección.
	 * 
	 * @param checkPoint punto que cierra el rectángulo de
	 *        selección.
	 * @param multipleSelection determina si se usa el modo
	 *        de selección múltiple.
	 * @return 
	 */
	public boolean finishSelection(Point checkPoint, boolean multipleSelection);


	/**
	 * Mover las figuras con el arrastre del ratón.
	 * 
	 * @param mousePosition el punto donde se encuentra el
	 *        ratón.
	 */
	public void dragShapes(Point mousePosition);


	/**
	 * Borrar las figuras seleccionadas.
	 */
	public void deleteSelectedShapes();


	/**
	 * Mover las figuras seleccionadas.
	 * 
	 * @param xOffset incremento en x
	 * @param yOffset incremento en y
	 */
	public void moveSelectedShapes(double xOffset, double yOffset);


	/**
	 * Escalar las figuras seleccionadas.
	 * 
	 * @param factorOffset factor de escalado.
	 */
	public void scaleSelectedShapes(double factorOffset);


	/**
	 * Rotar las figuras seleccionadas.
	 * 
	 * @param rotationOffset un incremento angular (en
	 *        radianes).
	 */
	public void rotateSelectedShapes(double rotationOffset);


	/**
	 * Mover el centro de selecciones.
	 * 
	 * @param thePoint el punto al que desplazar el centro
	 *        de selecciones.
	 */
	public void moveSelectionCenterTo(Point thePoint);


	/**
	 * Escalar el centro de selecciones.
	 * 
	 * @param theFactor el factor al cual escalar el centro
	 *        de selecciones.
	 */
	public void scaleSelectionCenter(double theFactor);


	/**
	 * Rotar el centro de selecciones.
	 * 
	 * @param theAngle el ángulo al cual rotar el centro de
	 *        selecciones.
	 */
	public void rotateSelectionCenter(int theAngle);


	/**
	 * Resetear el modelo, efectivamente borrando todas las
	 * formas.
	 */
	public void reset();


	/**
	 * Cargar estado.
	 * 
	 * @throws IOException
	 */
	void loadState() throws IOException;


	/**
	 * Guardar estado. Si ha sido guardado alguna vez, el
	 * fichero se actualiza con el nuevo estado del modelo,
	 * mediante serialización de objetos.
	 * 
	 * @throws IOException
	 */
	public void saveState() throws IOException;


	/**
	 * Guardar estado en nuevo archivo. Una ventana de
	 * selección de fichero aparece y nos permite elegir un
	 * nuevo fichero donde guardar el estado del modelo.
	 * 
	 * @throws IOException
	 */
	public void saveStateAs() throws IOException;


	/**
	 * Deshacer la última creación de figura.
	 */
	public void undoNewShape();


	/**
	 * Deshacer el último borrado de figuras.
	 */
	public void undoDelete();


	/**
	 * Deshacer la última selección.
	 */
	public void undoSelect();


	/**
	 * Deshacer el último movimiento.
	 */
	public void undoMove();


	/**
	 * Deshacer la última rotación.
	 */
	public void undoRotate();


	/**
	 * Deshacer el último escalado.
	 */
	public void undoScale();


	//
	// app.Model GETTERS & SETTERS
	// ***************************
	// Métodos para el controlador.

	int selectShape(Point newSelectionPoint, boolean multipleSelection);


	/**
	 * Getter para obtener la lista con las formas.
	 * 
	 * @return un List de Shapes.
	 */
	public LinkedList<ShapeUI> getShapes();


	/**
	 * Obtener una lista de las figuras seleccionadas.
	 * 
	 * @return la lista de figuras cuyo estado es
	 *         seleccionada.
	 */
	public LinkedList<ShapeUI> getSelectedShapes();


	/**
	 * Obtener el centro de la selección actual. Es el
	 * centro geométrico del polígono formado por los
	 * centros de las figuras seleccionadas.
	 * 
	 * @return
	 */
	public Point calculateCenterOfSelection();


	/**
	 * Obtiene el centro de selección.
	 * 
	 * @return la figura que hace de dummy para las
	 *         selecciones.
	 */
	ShapeUI getSelectionCenter();


	/**
	 * Setea el centro de selección.
	 * 
	 * @param selectionCenter la figura que hará de dummy
	 *        para las selecciones.
	 */
	void setSelectionCenter(ShapeUI selectionCenter);
}
