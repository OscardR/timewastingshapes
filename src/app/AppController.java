package app;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shape.Point;
import ui.CircleUI;
import ui.Java2DCircleDrawer;
import ui.Java2DRectangleDrawer;
import ui.Java2DSegmentDrawer;
import ui.Java2DTriangleDrawer;
import ui.RectangleUI;
import ui.SegmentUI;
import ui.ShapeUI;
import ui.TriangleUI;
import event.MoveEvent;
import event.RotationEvent;
import event.ScalingEvent;
import event.SelectionEvent;
import event.ShapeObserver;

/**
 * Controlador de la aplicación. Coordina el input del usuario en los
 * componentes con la actualización de los datos en el modelo, y la consiguiente
 * actualización de la vista (panel de gráficos) para reflejar estos cambios.
 * 
 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
 */
public class AppController implements Controller {

	/**
	 * MVC bindings:
	 */
	private Model model;
	private View view;
	private Graphics graphics;
	private UserCommand lastCommand;

	/**
	 * El controlador ha de inicializarse con referencias al modelo, la vista de
	 * gráficos y la ventana principal de la aplicación, para que todo funcione
	 * como ha de ser.
	 * 
	 * @param theModel
	 *            referencia al modelo.
	 * @param theGraphics
	 *            referencia al componente de gráficos.
	 * @param theView
	 *            referencia a la ventana principal de la aplicación.
	 */
	public AppController(Model theModel, Graphics theGraphics, View theView) {

		this.graphics = theGraphics;
		this.view = theView;
		this.model = theModel;

		/*
		 * Inicializamos los listeners de la ventana principal.
		 */
		setViewListeners();
		setLastCommand(null);
	}

	@Override
	public void setViewListeners() {
		/*
		 * Seteamos los listeners para los comandos del menú archivo.
		 */
		view.addNewCanvasListener(new CommandListener(UserCommand.NEW_CANVAS));
		view.addExitListener(new CommandListener(UserCommand.EXIT));
		view.addSaveListener(new CommandListener(UserCommand.SAVE));
		view.addSaveAsListener(new CommandListener(UserCommand.SAVE_AS));
		view.addLoadListener(new CommandListener(UserCommand.LOAD));

		/*
		 * Seteamos listeners para los botones de creación de figuras.
		 */
		view.addNewCircleListener(new CommandListener(UserCommand.NEW_CIRCLE));
		view.addNewRectangleListener(new CommandListener(
				UserCommand.NEW_RECTANGLE));
		view.addNewTriangleListener(new CommandListener(
				UserCommand.NEW_TRIANGLE));
		view.addNewSegmentListener(new CommandListener(UserCommand.NEW_SEGMENT));

		/*
		 * Seteamos los listeners para los botones de la barra de herramientas.
		 */
		view.addUndoListener(new UndoListener());
		view.addDeleteListener(new DeletionListener());

		/*
		 * Seteamos listeners para los eventos de transformación de las figuras.
		 */
		view.addPositionListener(new SliderChangeListener());
		view.addRotationListener(new RotationSpinnerListener());
		view.addScalingListener(new ScalingSpinnerListener());

		/*
		 * El listener del panel de dibujo atiende sólo a eventos de ratón.
		 */
		graphics.addMouseListener(new SelectionListener());
		graphics.addMouseMotionListener(new MouseMovementListener());
		graphics.addMouseWheelListener(new MouseScrollListener());

		/*
		 * Inicializamos los listeners del selectionCenter para que pueda
		 * notificar de sus cambios a las figuras seleccionadas.
		 */
		model.getSelectionCenter().addMoveObserver(new ShapeChangeListener());
		model.getSelectionCenter().addScaleObserver(new ShapeChangeListener());
		model.getSelectionCenter().addRotationObserver(
				new ShapeChangeListener());
	}

	/**
	 * Enumerador de comandos para facilitar la clasificación de las acciones de
	 * los controles en la ventana principal.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public enum UserCommand {
		DELETE, EXIT, MOVE, NEW_CANVAS, NEW_CIRCLE, NEW_RECTANGLE, NEW_SEGMENT, NEW_TRIANGLE, ROTATE, LOAD, SAVE, SAVE_AS, SCALE, SELECT, UNDO, NEW_SHAPE;
	}

	/**
	 * Clase interna que atiende a eventos de ratón en el panel de dibujo
	 * (básicamente responde al click)
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class SelectionListener extends MouseAdapter implements
			MouseMotionListener {

		/**
		 * Con mouseClicked atenderemos a selecciones.
		 */
		@Override
		public void mouseClicked(MouseEvent m) {

			/*
			 * Comprobar si la tecla mayúsculas está pulsada para activar
			 * selección múltiple.
			 */
			boolean multipleSelection = false;
			if ((m.getModifiers() & ActionEvent.SHIFT_MASK) > 0) {
				multipleSelection = true;
			}

			/*
			 * Cogemos el punto del puntero del ratón.
			 */
			Point newSelectionPoint = new Point(m.getX(), m.getY());

			model.selectShape(newSelectionPoint, multipleSelection);
			setLastCommand(UserCommand.SELECT);
		}

		/**
		 * mousePressed guarda el punto origen del movimiento que se efectuará
		 * en las figuras seleccionadas.
		 */
		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			Point checkPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
			model.startSelection(checkPoint);
		}

		/**
		 * Al soltar el botón, se guarda el punto destino del movimiento, para
		 * poder deshacerlo con un comando UNDO.
		 */
		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			Point checkPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
			boolean multipleSelection = false;
			if ((mouseEvent.getModifiers() & ActionEvent.SHIFT_MASK) > 0) {
				multipleSelection = true;
			}
			if (model.finishSelection(checkPoint, multipleSelection))
				setLastCommand(UserCommand.SELECT);
			else
				setLastCommand(UserCommand.MOVE);
		}
	}

	/**
	 * Escuchador de ratón para mover las figuras y hacer selecciones múltiples
	 * mediante cuadro de selección.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class MouseMovementListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {

			Point thePoint = new Point(e.getX(), e.getY());
			model.dragShapes(thePoint);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Point mousePos = new Point(e.getX(), e.getY());
			if (model.pointIsInsideAnyShape(mousePos)) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				e.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	public class MouseScrollListener implements MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			int clicks = e.getWheelRotation();
			// System.out.println("Wheel!: " + clicks);
			model.scaleSelectedShapes(-clicks * 0.1f + 1);
		}

	}

	/**
	 * Clase que escucha al 'centro' de la selección actual y mueve las figuras
	 * en función del mismo.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class ShapeChangeListener implements ShapeObserver {

		@Override
		public void selectionPerformed(SelectionEvent s) {
		}

		@Override
		public void movementPerformed(MoveEvent m) {
			Point offset = new Point(m.getOffset());
			model.moveSelectedShapes(offset.getX(), offset.getY());
		}

		@Override
		public void scalingPerformed(ScalingEvent s) {
			model.scaleSelectedShapes(s.getFactorOffset());
		}

		@Override
		public void rotationPerformed(RotationEvent r) {
			model.rotateSelectedShapes(r.getRotationOffset());
		}
	}

	/**
	 * Escuchador de movimiento para los sliders de posición.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class SliderChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {

			Point destino = new Point(view.getHorizontalSliderValue()
					* graphics.getWidth() / 100, graphics.getHeight()
					- view.getVerticalSliderValue() * graphics.getHeight()
					/ 100);
			model.moveSelectionCenterTo(destino);
			setLastCommand(UserCommand.MOVE);
		}
	}

	/**
	 * Escuchador de escalado para el spinner de escalado.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class ScalingSpinnerListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			double currentScaleFactor = Double.parseDouble(((JSpinner) e
					.getSource()).getModel().getValue().toString());
			model.scaleSelectionCenter(currentScaleFactor);
			setLastCommand(UserCommand.SCALE);
		}
	}

	/**
	 * RotateListener: Escucha al spinner de rotación para saber cuando ha de
	 * rotar una figura.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class RotationSpinnerListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			int currentRotationValue = Integer.parseInt(((JSpinner) e
					.getSource()).getModel().getValue().toString());
			model.rotateSelectionCenter(currentRotationValue);
			setLastCommand(UserCommand.ROTATE);
		}
	}

	/**
	 * Clase interna para atender a eventos de borrado en la ventana principal.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class DeletionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent action) {
			model.deleteSelectedShapes();
			setLastCommand(UserCommand.DELETE);
		}

	}

	/**
	 * Clase interna que nos sirve para poder recibir comandos desde los
	 * componentes de la vista principal.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class CommandListener extends MouseAdapter implements ActionListener {

		UserCommand command;

		/**
		 * Siempre registraremos un escuchador con un comando determinado, para
		 * poder responder adecuadamente a cada acción.
		 * 
		 * @param aCommand
		 *            el comando para el que se registra el escuchador.
		 */
		public CommandListener(UserCommand aCommand) {
			this.command = aCommand;
		}

		@Override
		public void actionPerformed(ActionEvent action) {

			switch (command) {
			case NEW_CANVAS:
				model.reset();
				break;

			case NEW_CIRCLE:
				ShapeUI circle = new CircleUI(graphics.getWidth() / 2,
						graphics.getHeight() / 2, 20);
				circle.setDrawer(new Java2DCircleDrawer());
				model.addShape(circle);
				break;

			case NEW_RECTANGLE:
				RectangleUI rectangle = new RectangleUI(Math.random()
						* graphics.getWidth(), Math.random()
						* graphics.getHeight(), Math.random()
						* graphics.getWidth(), Math.random()
						* graphics.getHeight());
				rectangle.setDrawer(new Java2DRectangleDrawer());
				model.addShape(rectangle);
				break;

			case NEW_SEGMENT:
				SegmentUI segment = new SegmentUI(new Point(Math.random()
						* graphics.getWidth(), Math.random()
						* graphics.getHeight()), new Point(Math.random()
						* graphics.getWidth(), Math.random()
						* graphics.getHeight()));
				segment.setDrawer(new Java2DSegmentDrawer());
				model.addShape(segment);
				break;

			case NEW_TRIANGLE:
				TriangleUI triangle = new TriangleUI(new Point(Math.random()
						* graphics.getWidth(), Math.random()
						* graphics.getHeight()), new Point(Math.random()
						* graphics.getWidth(), Math.random()
						* graphics.getHeight()), new Point(Math.random()
						* graphics.getWidth(), Math.random()
						* graphics.getHeight()));
				triangle.setDrawer(new Java2DTriangleDrawer());
				model.addShape(triangle);
				break;

			case LOAD:
				try {
					model.loadState();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case SAVE:
				try {
					model.saveState();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case SAVE_AS:
				try {
					model.saveStateAs();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case EXIT:
				((JFrame) view).dispose();
				break;
			default:
				break;
			}
			setLastCommand(command);
		}
	}

	/**
	 * Clase interna que responde a eventos de tipo deshacer.
	 * 
	 * @author Óscar Gómez Alcañiz (oscar.gomez@uji.es)
	 */
	public class UndoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			switch (getLastCommand()) {

			case SELECT:
				model.undoSelect();
				break;

			case SCALE:
				model.undoScale();
				break;

			case ROTATE:
				model.undoRotate();
				break;

			case DELETE:
				model.undoDelete();
				break;

			case MOVE:
				model.undoMove();
				break;

			case NEW_TRIANGLE:
			case NEW_CIRCLE:
			case NEW_RECTANGLE:
			case NEW_SEGMENT:
				model.undoNewShape();
			default:
				break;
			}

			if (getLastCommand() != UserCommand.UNDO)
				model.updateStatus("Se ha deshecho el último comando <"
						+ getLastCommand() + ">");
			else
				model.updateStatus("Nada que deshacer");
			setLastCommand(UserCommand.UNDO);
		}
	}

	//
	// app.AppController SETTERS & GETTERS
	// ***********************************

	private UserCommand getLastCommand() {
		return this.lastCommand;
	}

	private void setLastCommand(UserCommand lastCommand) {
		this.lastCommand = lastCommand;
	}
}
