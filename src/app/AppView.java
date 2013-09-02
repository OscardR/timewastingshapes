
package app;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import javax.swing.event.ChangeListener;

import ui.Styles;


/**
 * Ventana principal de la aplicación. Contiene los
 * componentes de la GUI. Tiene métodos para asignar
 * listeners a los componentes que generan cambios en el
 * modelo, de forma que éstos puedan mandar comandos al
 * controlador.
 * 
 * @author oscar
 */
public class AppView extends JFrame implements View {

	private static final long	serialVersionUID	= -662663682976278434L;
	private Graphics			graphicsView;
	private Controller			controller;


	/**
	 * Crea la ventana principal.
	 */
	public AppView(Graphics graphics) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
			AppView.class.getResource("/icons/stiches/star_full.png")));
		/*
		 * Usamos el siguiente bloque para elegir un buen
		 * Look'n'Feel para nuestra ventana principal.
		 * Nimbus emula el aspecto de Mac OS X. Si lo
		 * encontramos en el sistema, se escogerá.
		 */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			System.out.println("Mala suerte. No tenemos Nimbus Look'n'Feel...");
		}

		/*
		 * Necesitamos inicializar nuestro panel de
		 * gráficos.
		 */
		this.graphicsView = graphics;

		/*
		 * Inicializamos la interfaz gráfica. Primero los
		 * menús, que van fuera del marco principal de la
		 * ventana (es decir, directamente en MainView), y
		 * luego los elementos del marco principal, llamado
		 * panelPrincipal.
		 */
		initMenus();
		initGUI();
	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}


	/*
	 * Variables usadas para los menús. Son accedidas por
	 * métodos para asignarles listeners y así poder
	 * ejecutar comandos a través del controlador.
	 */
	private JMenuBar	menuBar;
	private JMenu		mnArchivo;
	private JMenuItem	mntmNuevo;
	private JMenuItem	mntmAbrir;
	private JMenuItem	mntmGuardar;
	private JMenuItem	mntmGuardarComo;
	private JMenuItem	mntmSalir;


	/**
	 * Helper que inicializa los menus, y los pone en la
	 * ventana principal.
	 */
	private void initMenus() {

		menuBar = new JMenuBar();
		mnArchivo = new JMenu("Archivo");
		mntmNuevo = new JMenuItem("Nuevo");
		mntmAbrir = new JMenuItem("Abrir...");
		mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.setEnabled(false);
		mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmSalir = new JMenuItem("Salir");

		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);

		menuBar.add(mnArchivo);

		mntmNuevo.setIcon(new ImageIcon(
			"/usr/share/icons/gnome/24x24/actions/document-new.png"));
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_N,
			InputEvent.CTRL_DOWN_MASK));

		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_O,
			InputEvent.CTRL_DOWN_MASK));

		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_S,
			InputEvent.CTRL_DOWN_MASK));

		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_S,
			InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));

		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_Q,
			InputEvent.CTRL_DOWN_MASK));

		mnArchivo.add(mntmNuevo);
		mnArchivo.add(mntmAbrir);
		mnArchivo.add(mntmGuardar);
		mnArchivo.add(mntmGuardarComo);
		mnArchivo.add(mntmSalir);

		mnEdicion = new JMenu("Edición");
		menuBar.add(mnEdicion);

		mntmDeshacer = new JMenuItem("Deshacer");
		mntmDeshacer.setEnabled(false);
		mntmDeshacer.setAccelerator(KeyStroke.getKeyStroke(
			KeyEvent.VK_Z,
			InputEvent.CTRL_MASK));
		mnEdicion.add(mntmDeshacer);

		mntmBorrar = new JMenuItem("Borrar");
		mntmBorrar.setEnabled(false);
		mntmBorrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnEdicion.add(mntmBorrar);
	}


	/*
	 * Variables usadas para los botones y controles.
	 * También han de ser accedidas más tarde para poder
	 * asignarles listeners y por tanto poder ser capaces de
	 * ejecutar comandos en el controlador.
	 */
	private JPanel		panelPrincipal;
	private JLabel		lblStatus;
	private JSlider		sliderPosY;
	private JSlider		sliderPosX;
	private JButton		btnRec;
	private JButton		btnCir;
	private JButton		btnTri;
	private JButton		btnSeg;
	private JSpinner	spinEscalado;
	private JSpinner	spinRotacion;
	private JToolBar	toolBar;
	private JButton		btnUndo;
	private JButton		btnDelete;
	private JPanel		panelCentral;
	private JButton		btnNewCanvas;
	private JButton		btnSave;
	private JButton		btnOpen;
	private JMenu		mnEdicion;
	private JMenuItem	mntmDeshacer;
	private JMenuItem	mntmBorrar;
	private Component	vertStrutSouth;
	private Component	vertStrutNorth;


	/**
	 * Helper que inicializa todos los componentes y los
	 * sitúa en la ventana principal.
	 */
	private void initGUI() {

		/*
		 * Inicializamos todos los componentes.
		 */
		panelPrincipal = new JPanel();
		sliderPosY = new JSlider();
		sliderPosY.setToolTipText("Posición vertical (y)");
		sliderPosY.setEnabled(false);
		btnRec = new JButton();
		btnRec.setToolTipText("Nuevo Rectángulo");
		btnCir = new JButton();
		btnCir.setToolTipText("Nuevo Círculo");
		btnTri = new JButton();
		btnTri.setToolTipText("Nuevo Triángulo");
		btnSeg = new JButton();
		btnSeg.setToolTipText("Nuevo Segmento");
		spinEscalado = new JSpinner();
		spinEscalado.setToolTipText("Escalar figuras (0.1 ... 10.0)");
		spinRotacion = new JSpinner();
		lblStatus = new JLabel("<Selecciona tu figura>");
		lblStatus.setToolTipText("Status. Muestra información sobre la acción actual.");
		toolBar = new JToolBar();
		btnNewCanvas = new JButton();
		btnNewCanvas.setToolTipText("Crear nuevo lienzo (Ctrl+N)");
		btnOpen = new JButton();
		btnOpen.setToolTipText("Abrir archivo... (Ctrl+O)");
		btnSave = new JButton();
		btnSave.setToolTipText("Guardar (Ctrl+S)");
		btnSave.setEnabled(false);
		btnUndo = new JButton();
		btnUndo.setToolTipText("Deshacer (Ctrl+Z)");
		btnUndo.setEnabled(false);
		btnDelete = new JButton();
		btnDelete.setToolTipText("Borrar (Supr.)");
		btnDelete.setEnabled(false);

		/*
		 * Inicializamos los paneles.
		 */
		JPanel panelSuperior = new JPanel();
		JPanel panelVertical = new JPanel();
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(192, 192, 192));
		panelBotones.setBorder(new EmptyBorder(6, 6, 6, 6));

		/*
		 * Parámetros de la aplicación.
		 */
		setTitle("Time.Wasting.Shapes");
		setSize(Styles.WINDOW_WIDTH, Styles.WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Añadimos el panel principal y ponemos un layout y
		 * un borde.
		 */
		getContentPane().add(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(10, 10));
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

		/*
		 * Panel izquierdo con la posición vertical.
		 */
		panelPrincipal.add(panelVertical, BorderLayout.WEST);
		panelVertical.setLayout(new BoxLayout(panelVertical, BoxLayout.Y_AXIS));

		/*
		 * Mero icono que expresa 'posición'.
		 */
		JLabel lblMoveIcon = new JLabel("");
		lblMoveIcon.setToolTipText("Posición de las figuras");
		lblMoveIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoveIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMoveIcon.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/stiches/target.png")));
		panelVertical.add(lblMoveIcon);

		/*
		 * Espaciado vertical
		 */
		Component verticalStrut;
		verticalStrut = Box.createVerticalStrut(10);
		panelVertical.add(verticalStrut);

		/*
		 * Slider Vertical
		 */
		sliderPosY.setPaintTicks(true);
		sliderPosY.setMinorTickSpacing(5);
		sliderPosY.setMajorTickSpacing(20);
		sliderPosY.setOrientation(SwingConstants.VERTICAL);
		panelVertical.add(sliderPosY);

		/*
		 * Panel superior con la barra de herramientas.
		 */
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(0, 1, 0, 0));

		toolBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelSuperior.add(toolBar);

		btnNewCanvas.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/stiches/page.png")));
		toolBar.add(btnNewCanvas);

		btnOpen.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/stiches/folder.png")));
		toolBar.add(btnOpen);

		btnSave.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/stiches/save.png")));
		toolBar.add(btnSave);

		btnUndo.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/stiches/refresh.png")));
		toolBar.add(btnUndo);

		btnDelete.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/stiches/delete.png")));
		toolBar.add(btnDelete);

		/*
		 * Panel con los botones.
		 */
		panelPrincipal.add(panelBotones, BorderLayout.EAST);
		panelBotones.setLayout(new GridLayout(9, 1, 10, 10));

		JLabel lblShapeIcon = new JLabel("");
		lblShapeIcon.setToolTipText("Herramientas de dibujo");
		lblShapeIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblShapeIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblShapeIcon.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/stiches/pencil.png")));

		panelBotones.add(lblShapeIcon);

		btnRec.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/buttons/square.png")));
		panelBotones.add(btnRec);
		btnCir.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/buttons/circle.png")));
		panelBotones.add(btnCir);
		btnTri.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/buttons/triangle.png")));
		panelBotones.add(btnTri);
		btnSeg.setIcon(new ImageIcon(
			AppView.class.getResource("/icons/buttons/segment.png")));
		panelBotones.add(btnSeg);

		/*
		 * Controles para el escalado.
		 */
		JLabel lblEscalado = new JLabel("Escalado:");
		lblEscalado.setVerticalAlignment(SwingConstants.BOTTOM);
		panelBotones.add(lblEscalado);

		spinEscalado.setBackground(new Color(255, 255, 204));
		spinRotacion.setToolTipText("Rotación de las figuras (0º ... 359º)");
		spinEscalado.setModel(new SpinnerNumberModel(1.0, 0.1, 10.0, 0.1));
		panelBotones.add(spinEscalado);

		/*
		 * Controles para la rotación.
		 */
		JLabel lblRotacion = new JLabel("Rotación:");
		lblRotacion.setVerticalAlignment(SwingConstants.BOTTOM);
		panelBotones.add(lblRotacion);

		spinRotacion.setBackground(new Color(255, 255, 204));
		spinRotacion.setToolTipText("Rotación de la figura, en grados");
		spinRotacion.setModel(new SpinnerNumberModel(0, 0, 359, 1));
		panelBotones.add(spinRotacion);

		/*
		 * Panel central.
		 */
		panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

		/*
		 * Slider Horizontal.
		 */

		vertStrutNorth = Box.createVerticalStrut(10);
		panelCentral.add(vertStrutNorth);

		sliderPosX = new JSlider();
		sliderPosX.setToolTipText("Posición horizontal (x)");
		sliderPosX.setEnabled(false);
		sliderPosX.setPaintTicks(true);
		sliderPosX.setMinorTickSpacing(5);
		sliderPosX.setMajorTickSpacing(20);
		panelCentral.add(sliderPosX);

		vertStrutSouth = Box.createVerticalStrut(10);
		panelCentral.add(vertStrutSouth);

		/*
		 * Añadimos el panel de gráficos al centro de la
		 * ventana.
		 */
		panelCentral.add(graphicsView, BorderLayout.CENTER);

		/*
		 * Añadimos el JLabel con el status de la operación
		 * actual.
		 */
		panelPrincipal.add(lblStatus, BorderLayout.SOUTH);
	}


	/**
	 * getter para el Slider de posición vertical.
	 * 
	 * @return the sliderPosY
	 */
	public int getVerticalSliderValue() {
		return this.sliderPosY.getValue();
	}


	/**
	 * getter para el Slider de posición horizontal.
	 * 
	 * @return the sliderPosX
	 */
	public int getHorizontalSliderValue() {
		return this.sliderPosX.getValue();
	}


	//
	// 'Hooks' para el controlador
	// ***************************

	@Override
	public void addNewCanvasListener(ActionListener listener) {
		mntmNuevo.addActionListener(listener);
		btnNewCanvas.addActionListener(listener);
	}


	@Override
	public void addNewCircleListener(ActionListener listener) {
		btnCir.addActionListener(listener);
	}


	@Override
	public void addNewRectangleListener(ActionListener listener) {
		btnRec.addActionListener(listener);
	}


	@Override
	public void addNewSegmentListener(ActionListener listener) {
		btnSeg.addActionListener(listener);
	}


	@Override
	public void addNewTriangleListener(ActionListener listener) {
		btnTri.addActionListener(listener);
	}


	@Override
	public void addDeleteListener(ActionListener listener) {
		btnDelete.addActionListener(listener);
		mntmBorrar.addActionListener(listener);
	}


	@Override
	public void addExitListener(ActionListener listener) {
		mntmSalir.addActionListener(listener);
	}


	@Override
	public void addLoadListener(ActionListener loadListener) {
		btnOpen.addActionListener(loadListener);
		mntmAbrir.addActionListener(loadListener);
	}


	@Override
	public void addSaveListener(ActionListener saveListener) {
		btnSave.addActionListener(saveListener);
		mntmGuardar.addActionListener(saveListener);
	}


	@Override
	public void addSaveAsListener(ActionListener saveAsListener) {
		mntmGuardarComo.addActionListener(saveAsListener);
	}


	@Override
	public void addPositionListener(ChangeListener listener) {
		sliderPosX.addChangeListener(listener);
		sliderPosY.addChangeListener(listener);
	}


	@Override
	public void addRotationListener(ChangeListener listener) {
		spinRotacion.addChangeListener(listener);
	}


	@Override
	public void addUndoListener(ActionListener listener) {
		btnUndo.addActionListener(listener);
		mntmDeshacer.addActionListener(listener);
	}


	@Override
	public void addScalingListener(ChangeListener scalingListener) {
		spinEscalado.addChangeListener(scalingListener);
	}


	//
	// 'Hooks' para el Modelo.
	// ***********************

	@Override
	public void setDeleteEnable(boolean enabled) {
		btnDelete.setEnabled(enabled);
		mntmBorrar.setEnabled(enabled);
	}


	@Override
	public void setPositionEnable(boolean enabled) {
		sliderPosX.setEnabled(enabled);
		sliderPosY.setEnabled(enabled);
	}


	@Override
	public void setSaveEnable(boolean enabled) {
		btnSave.setEnabled(enabled);
		mntmGuardar.setEnabled(enabled);
		mntmGuardarComo.setEnabled(enabled);
	}


	@Override
	public void setTransformEnable(boolean enabled) {
		spinEscalado.setEnabled(enabled);
		spinRotacion.setEnabled(enabled);
	}


	@Override
	public void setUndoEnable(boolean enabled) {
		btnUndo.setEnabled(enabled);
		mntmDeshacer.setEnabled(enabled);
	}


	@Override
	public void updatePosition(int horizontalPosition, int verticalPosition) {
		sliderPosX.getModel().setValue(horizontalPosition);
		sliderPosY.getModel().setValue(verticalPosition);
	}


	@Override
	public void updateRotation(int theAngle) {
		spinRotacion.setValue(theAngle);
	}


	@Override
	public void updateScale(double theFactor) {
		spinEscalado.setValue(theFactor);
	}


	@Override
	public void updateStatus(String string) {
		lblStatus.setText(string);
		lblStatus.repaint();
	}


	@Override
	public JComponent getComponent() {
		return (JComponent) this.getContentPane();
	}
}
