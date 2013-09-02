package app;

import javax.swing.JFrame;

/**
 * Hilo principal de la aplicación.Ata los tres pilares de la aplicación
 * (Modelo, Vista y Controlador).
 * 
 * @author oscar
 */
public class AppMain {

	/*
	 * Los tres pilares de la aplicación (Modelo - Vistas(2) - Controlador).
	 */
	private Model model;
	private Controller controller;
	private Graphics graphicsView;
	private View mainWindowView;

	/**
	 * Constructor principal. Instancia la aplicación. Es llamado desde main()
	 * para comenzar el montaje de los componentes.
	 */
	public AppMain() {

		/*
		 * Inicializamos la vista y el modelo, totalmente vacíos. Model es un
		 * Singleton.
		 */
		this.model = AppModel.getInstance();
		this.graphicsView = new AppGraphics();
		this.mainWindowView = new AppView(graphicsView);
		model.setView(mainWindowView);
		model.setGraphics(graphicsView);

		/*
		 * Una vez los componentes están inicializados, montamos el tinglado MVC
		 * con el controlador.
		 */
		controller = new AppController(model, graphicsView, mainWindowView);
		mainWindowView.setController(controller);
	}

	/**
	 * Función principal. Arranca la aplicación, guardando una referencia a su
	 * ventana principal, y poniéndola visible. Se usa un hilo para ello.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				/*
				 * Podemos instanciar tantas instancias de la aplicación como
				 * queramos pero sólo existe un modelo.
				 */
				AppMain app = new AppMain();
				((JFrame) app.mainWindowView).setVisible(true);
				((JFrame) app.mainWindowView).setLocationRelativeTo(null);
			}
		});
	}
}
