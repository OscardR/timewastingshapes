
package ui;


import java.io.Serializable;


public interface ShapeDrawer extends Serializable {

	/**
	 * Dibujar el objeto dibujable contenido en un objeto
	 * que hace de canvas.
	 * 
	 * @param canvas
	 */
	public void draw(Object canvas);


	public Object getColor();


	public Object getStroke();


	public Object getPaint();


	public ShapeUI getShapeUI();


	public Drawable getDrawable();


	public void setColor(Object color);


	public void setStroke(Object stroke);


	public void setPaint(Object color);


	public void setDrawable(Drawable theShape);
}
