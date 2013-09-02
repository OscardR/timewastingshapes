
package app;


import java.awt.*;

import javax.swing.UIManager;

import ui.Drawable;
import ui.Styles;


public class AppGraphics extends Graphics {

	private static final long	serialVersionUID	= 2458809887439329680L;
	private Model				model;


	public AppGraphics() {

		/*
		 * Obtenemos el modelo.
		 */
		model = AppModel.getInstance();

		// Preparamos el componente para que los gráficos se
		// vean bien majos.
		setLayout(null);
		setBackground(Color.WHITE);
		setBorder(UIManager.getBorder("InternalFrame.paletteBorder"));
	}


	@Override
	public void paintComponent(java.awt.Graphics g) {

		// Obligatorio para que funcionen los seteos
		// respectivos al fondo.
		super.paintComponent(g);

		// Obtener los gráficos del componente y setear el
		// antialiasing:
		Graphics2D graphics = (Graphics2D) g;

		graphics.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

		// Cada vez que pintamos el componente hemos de
		// dibujar las figuras. Recorrer el modelo en busca
		// de formas:
		for (Drawable aShape : model.getShapes())
			aShape.getDrawer().draw(graphics);

		if (model.isSelecting())
			model.getSelectionRectangle().getDrawer().draw(graphics);

		// Dibujar el marco con el estilo del marco:
		graphics.setStroke(Styles.FRAME_STOKE);
		graphics.setColor(Styles.FRAME_COLOR);
	}
}
