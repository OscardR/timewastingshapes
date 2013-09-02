
package ui;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;


public class Styles {

	/**
	 * Estilos de color:
	 */
	public static final Color	BACKGROUND_COLOR	= Color.WHITE;
	public static final Color	FRAME_COLOR			= Color.GRAY;

	public static final Color	SHAPE_COLOR			= Color.BLUE;
	public static final Color	SELECTION_COLOR		= Color.getHSBColor(
														212.f / 360,
														50.f / 100,
														85.f / 100);
	public static final Color	SHAPE_FILL			= new Color(
														0xCE,
														0xDF,
														0xF2,
														0x80);
	public static final Color	SELECTION_FILL		= new Color(
														0xCE,
														0xF2,
														0xDF,
														0x80);
	public static final Color	GUIDE_COLOR			= Color.CYAN;

	/**
	 * Estilos de trazo:
	 */
	public static final Stroke	FRAME_STOKE			= new BasicStroke(2.f);
	public static final Stroke	SHAPE_STROKE		= new BasicStroke(1.25f);
	public static final Stroke	SELECTION_STROKE	= new BasicStroke(
														1,
														BasicStroke.CAP_BUTT,
														BasicStroke.JOIN_MITER,
														1,
														new float[] { 8f, 4f },
														0.f);
	public static final Color	DEBUG_COLOR			= Color.LIGHT_GRAY;
	public static final int		WINDOW_WIDTH		= 1024;
	public static final int		WINDOW_HEIGHT		= 768;
}
