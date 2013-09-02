
package ui;


public interface Drawable {

	/**
	 * Obtener el dibujador de este objeto dibujable.
	 * 
	 * @return
	 */
	ShapeDrawer getDrawer();


	/**
	 * Setear el dibujador de este objeto dibujable.
	 * 
	 * @param drawer
	 */
	void setDrawer(ShapeDrawer drawer);
}
