
package app;


/**
 * Interfaz para el controlador. Necesitamos algunos métodos
 * públicos para poder manejar la aplicación desde la vista.
 * 
 * @author Óscar
 */
public interface Controller {

	/**
	 * Añadir todos los listeners para poder procesar las
	 * acciones asociadas a sus respectivos comandos.
	 */
	void setViewListeners();
}
