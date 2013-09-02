
package ui;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;


/**
 * Clase dibujadora para la librería Java2D. Sabe dibujar
 * formas en un objeto Graphics2D de dicha librería.
 * 
 * @author Óscar
 */
public abstract class Java2DDrawer implements ShapeDrawer {

	private static final long	serialVersionUID	= -3769813076160483688L;

	private Color				drawingColor;
	private transient Stroke	drawingStroke;
	private Color				paintColor;
	private ShapeUI				shape;
	private Drawable			drawable;


	public Java2DDrawer() {
		setColor(Styles.SHAPE_COLOR);
		setStroke(Styles.SHAPE_STROKE);
		setPaint(Styles.SHAPE_FILL);
	}


	@Override
	public Color getColor() {
		return this.drawingColor;
	}


	@Override
	public Color getPaint() {
		return paintColor;
	}


	@Override
	public Stroke getStroke() {
		return this.drawingStroke;
	}


	@Override
	public ShapeUI getShapeUI() {
		return this.shape;
	}


	@Override
	public Drawable getDrawable() {
		return this.drawable;
	}


	@Override
	public void setColor(Object anObject) {
		setColor((Color) anObject);
	}


	public void setColor(Color aColor) {
		this.drawingColor = aColor;
	}


	@Override
	public void setStroke(Object aStroke) {
		setStroke((Stroke) aStroke);
	}


	public void setStroke(Stroke aStroke) {
		this.drawingStroke = aStroke;
	}


	@Override
	public void setPaint(Object aColor) {
		setPaint((Color) aColor);
	}


	public void setPaint(Color aColor) {
		this.paintColor = aColor;
	}


	@Override
	public void setDrawable(Drawable theShape) {
		this.drawable = theShape;
		this.shape = (ShapeUI) theShape;
	}


	@Override
	public void draw(Object canvas) {
		draw((Graphics2D) canvas);
	}


	/**
	 * Método para dibujar la figura. Cada figura ha de
	 * implementar su manera de dibujarse en un JComponent
	 * mediante el uso de las primitivas de la colección
	 * Java2D.
	 * 
	 * @param g el contenedor de gráficos del componente en
	 *        el cual vamos a dibujar.
	 */
	public abstract void draw(Graphics2D g);

}
