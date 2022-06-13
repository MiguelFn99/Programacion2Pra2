package unileon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class MainGrafico extends JFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 * Este programa lo hizo el Michuelas
	 * 
	 */
	JPanel panelPrincipal = new JPanel();
	JPanel panelCentro = new JPanel();
	JPanel panelTablero = new JPanel();
	JPanel panelArriba = new JPanel();
	JPanel panelIzq = new JPanel();
	JPanel panelAbajo = new JPanel();

	private JMenuBar barraMenu = new JMenuBar();

	private JMenu menuArchivo = new JMenu("Archivo");
	private JMenu menuEditar = new JMenu("Editar");
	private JMenu menuSimulacion = new JMenu("Realizar Simulación");
	private JMenu menuAyuda = new JMenu("Ayuda");

	private JMenuItem menuNuevaSimulacion = new JMenuItem("Crear nueva simulación");
	private JMenuItem menuCargarSimulacion = new JMenuItem("Cargar nueva simulación");
	private JMenuItem menuArchivoGuardar = new JMenuItem("Guardar");
	private JMenuItem menuArchivoGuardarComo = new JMenuItem("Guardar como");
	private JMenuItem menuSalir = new JMenuItem("Salir");
	private JMenuItem menuRehacer = new JMenuItem("Rehacer");
	private JMenuItem menuDeshacer = new JMenuItem("Deshacer");
	private JMenuItem menuAnadirTren = new JMenuItem("Añadir tren");
	private JMenuItem menuEliminarTren = new JMenuItem("Eliminar tren");
	private JMenuItem menuComenzarSimu = new JMenuItem("Comenzar Simulación");
	private JMenuItem menuEliminarTablero = new JMenuItem("Eliminar tablero");
	private JMenuItem menuAyuda2 = new JMenuItem("Ayuda");

	// Variable programa
	private JButton tableroJuego[][];
	private int filasTableroJuego;
	private int columnasTableroJuego;
	private Tablero oTablero;
	private ArrayList<Tren> trenes = new ArrayList<Tren>();
	private JLabel etiquetaRaton = new JLabel("    ");
	private JButton botonMoverUnTren = new JButton("Mover paso a paso");
	private boolean escucharTeclado = false;
	private Tren oTrenSeleccionado;
	private String nombreArchivo = "";
	private Movimientos oMovimientos = new Movimientos();
	private Timer timer;
	private int contMover = 0;

	public static void main(String[] args) {
		MainGrafico oVentana = new MainGrafico();
		oVentana.setVisible(true);
	}

	public MainGrafico() {
		this.setTitle("Trenes 2020");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		inicializarMenus();
		add(panelPrincipal);
		cargoPaneles();
	}

	private void cargoPaneles() {
		panelPrincipal.setVisible(true);
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBackground(Color.BLACK);

		panelPrincipal.add(panelCentro, BorderLayout.CENTER);
		panelPrincipal.add(panelIzq, BorderLayout.WEST);

		panelIzq.setBackground(Color.YELLOW);
		panelIzq.setVisible(true);

		cargoPanelCentroInicial();

	}

	private void cargoPanelCentroInicial() {
		panelCentro.setLayout(new BorderLayout());
		panelCentro.setVisible(true);
		panelCentro.add(panelTablero, BorderLayout.CENTER);
		panelCentro.add(panelArriba, BorderLayout.NORTH);
		panelArriba.setVisible(true);
		panelArriba.setBackground(Color.YELLOW);
		panelCentro.add(panelAbajo, BorderLayout.SOUTH);
		panelAbajo.setVisible(true);
	}

	private void inicializarMenus() {
		setJMenuBar(barraMenu);

		barraMenu.add(menuArchivo);
		barraMenu.add(menuEditar);
		barraMenu.add(menuSimulacion);
		barraMenu.add(menuAyuda);

		menuArchivo.add(menuNuevaSimulacion);
		menuArchivo.addSeparator();
		menuArchivo.add(menuCargarSimulacion);
		menuArchivo.addSeparator();
		menuArchivo.add(menuArchivoGuardar);
		menuArchivo.addSeparator();
		menuArchivo.add(menuArchivoGuardarComo);
		menuArchivo.addSeparator();
		menuArchivo.add(menuSalir);

		menuArchivoGuardar.setEnabled(false);
		menuArchivoGuardarComo.setEnabled(false);

		menuEditar.add(menuRehacer);
		menuEditar.addSeparator();
		menuEditar.add(menuDeshacer);
		menuEditar.addSeparator();
		menuEditar.add(menuAnadirTren);
		menuEditar.addSeparator();
		menuEditar.add(menuEliminarTren);
		menuEditar.addSeparator();
		menuEditar.add(menuEliminarTablero);
		
		menuRehacer.setEnabled(false);
		menuDeshacer.setEnabled(false);

		menuSimulacion.add(menuComenzarSimu);

		menuAyuda.add(menuAyuda2);

		// Menus Desactivados
		menuAnadirTren.setEnabled(false);
		menuEliminarTren.setEnabled(false);

		menuNuevaSimulacion.addActionListener(this);
		menuCargarSimulacion.addActionListener(this);
		menuArchivoGuardar.addActionListener(this);
		menuArchivoGuardarComo.addActionListener(this);
		menuSalir.addActionListener(this);

		menuRehacer.addActionListener(this);
		menuDeshacer.addActionListener(this);
		menuAnadirTren.addActionListener(this);
		menuEliminarTren.addActionListener(this);

		menuComenzarSimu.addActionListener(this);
		menuEliminarTablero.addActionListener(this);

		menuAyuda2.addActionListener(this);

		botonMoverUnTren.addActionListener(this);

	}

	private void cargoPanelesCrearYArchivo() {
		cargoPanelConTablero();
		cargoPanelArriba();
		cargoPanelIzq();
		cargoPanelAbajo();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent eventoRaton) {

	}

	@Override
	public void mouseEntered(MouseEvent eventoRaton) {
		for (int fila = 0; fila < tableroJuego.length; fila++) {
			for (int columna = 0; columna < tableroJuego[fila].length; columna++) {
				if (eventoRaton.getSource() == tableroJuego[fila][columna]) {
					String coordenadas = Integer.toString(tableroJuego.length - fila - 1) + ","
							+ Integer.toString(columna);
					etiquetaRaton.setText("El ratón están en la casilla: " + coordenadas);
					etiquetaRaton.setForeground(Color.BLACK);
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuSalir) {
			JOptionPane.showMessageDialog(null, "Has pulsado menú salir.");
			dispose();
		}
		if (e.getSource() == menuAyuda2) {
			JOptionPane.showMessageDialog(null,
					"Programa 'trenes 2020'\n"
					+ "Colores: Ariba: AZUL , Abajo: VERDE, Izquierda: CYAN, Derecha: MAGNETA y Colisión:ROJO\n"
					+ "Puede crear un tablero en el menú crear simulación o en el cargar simulación.\n"
					+ "Puede guardar utilizando los menús de guardar y guardar Como.\n"
					+ "Puede salir utilizando el menú salir o en la cruz de arriba a la derecha.\n"
					+ "Puede añadir trenes en el menú añadir tren y poniendo los datos del tren.\n"
					+ "Si hace clic en un tren y luego le da al menú de eliminar tren, lo elimina.\n"
					+ "Si hace clic en un tren y después a la misma flecha de dirección que la dirección del tren, este se desplaza.\n"
					+ "Puede deshacer y rehacer dándole en el menú editar y luego haciendo clic sobre ellos.\n"
					+ "Si le da a realizar simulación se realiza la simulación de forma automática.");
		}
		if (e.getSource() == menuNuevaSimulacion) {
			eliminarTablero();
			pedirDatosTablero();
		}
		if (e.getSource() == menuAnadirTren) {
			pedirDatosTren();
			menuDeshacer.setEnabled(true);
			menuRehacer.setEnabled(true);
		}
		if (oTablero != null) {
			comprobarCasillaClic(e);
		}
		if (e.getSource() == menuCargarSimulacion) {
			eliminarTablero();
			Lectura oLectura = new Lectura();
			nombreArchivo = oLectura.getNombreArchivo();
			if (nombreArchivo != "") {
				tableroJuego = new JButton[oLectura.getNumFilas()][oLectura.getNumColumnas()];
				oTablero = new Tablero(oLectura.getNumFilas(), oLectura.getNumColumnas());

				trenes = ordenarTrenes(oLectura.getLineas());

				// Compruebo que todos los trenes sean correctos
				boolean correctos = true;
				for (int i = 0; i < trenes.size(); i++) {
					if (trenes.get(i).isEsCorrecto() == false) {
						correctos = false;
					}
				}
				if (correctos == true) {
					for (int i = 0; i < trenes.size(); i++) {
						oTablero.colocoTren(trenes.get(i));
					}
					oTablero.pongoColisionesTablero(oLectura.getLineasColision());
					oTablero.dibujoTablero();
					filasTableroJuego = oLectura.getNumFilas();
					columnasTableroJuego = oLectura.getNumColumnas();
					cargoPanelesCrearYArchivo();
					menuArchivoGuardarComo.setEnabled(true);
					menuArchivoGuardar.setEnabled(true);
					menuAnadirTren.setEnabled(true);
					menuEliminarTren.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Algún tren es incorrecto.");
					eliminarTablero();
				}
			}
		}
		if (e.getSource() == menuArchivoGuardar) {
			eliminoTrenesMenorCero();
			Guardar oGuardar = new Guardar(nombreArchivo);
			oGuardar.guardar(trenes, oTablero);
		}
		if (e.getSource() == menuArchivoGuardarComo) {
			eliminoTrenesMenorCero();
			Guardar oGuardar = new Guardar(nombreArchivo);
			oGuardar.guardarComo(trenes, oTablero);
			menuArchivoGuardar.setEnabled(true);
		}
		if (e.getSource() == menuDeshacer) {
			trenes = oMovimientos.buscoUltimoMovimientoRealizado();
			if (trenes != null) {
				oTablero.inicializoTablero();
				for (int i = 0; i < trenes.size(); i++) {
					oTablero.colocoTren(trenes.get(i));
				}
				cargoPanelConTablero();
			}
		}
		if (e.getSource() == menuRehacer) {
			trenes = oMovimientos.buscoUltimoMovimientoDeshecho();
			if (trenes != null) {
				oTablero.inicializoTablero();
				for (int i = 0; i < trenes.size(); i++) {
					oTablero.colocoTren(trenes.get(i));
				}
				cargoPanelConTablero();
			}
		}
		if (e.getSource() == menuEliminarTablero) {
			eliminarTablero();
		}
		if (e.getSource() == menuComenzarSimu) {
			realizarSimulacion();
		}
		if (e.getSource() == menuEliminarTren) {
			eliminarTren();
			menuDeshacer.setEnabled(true);
			menuRehacer.setEnabled(true);
		}
		/*if (e.getSource() == botonMoverUnTren) {
			moverPasoPaso();
			eliminoTrenesMenorCero();
			cargoPanelConTablero();
		}*/
	}

	private void eliminarTren() {
		oTablero.borroTren(oTrenSeleccionado);
		trenes.remove(oTrenSeleccionado);
		oMovimientos.addMovimientoRealizado(trenes);
		cargoPanelConTablero();
		JOptionPane.showMessageDialog(null, "Tren eliminado.");
	}

	private void moverPasoPaso() {
		oTablero.muevoTren(trenes.get(contMover), trenes);
		contMover++;
		if (contMover == trenes.size()) {
			contMover = 0;
		}
		oTablero.dibujoTablero();
	}

	private void eliminoTrenesMenorCero() {
		for (int i = 0; i < trenes.size(); i++) {
			if (trenes.get(i).getLongitud() < 1) {
				trenes.remove(i);
				i--;
			}
		}
	}

	private void realizarSimulacion() {
		timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < trenes.size(); i++) {
					oTablero.muevoTren(trenes.get(i), trenes);
				}
				if (oTablero.comprueboTablero() == false) {
					timer.stop();
				}
				oTablero.dibujoTablero();
				cargoPanelConTablero();
			}
		});
		timer.start();

//		while (oTablero.comprueboTablero() == true) {
//			for (int i = 0; i < trenes.size(); i++) {
//				oTablero.muevoTren(trenes.get(i), trenes);
//			}
//		}
//		cargoPanelConTablero();
	}

	private void eliminarTablero() {
		trenes.clear();
		panelPrincipal.removeAll();
		panelPrincipal.setVisible(false);
		panelCentro.removeAll();
		panelCentro.setVisible(false);
		panelIzq.removeAll();
		panelIzq.setVisible(false);
		panelTablero.removeAll();
		panelTablero.setVisible(false);
		panelArriba.removeAll();
		panelArriba.setVisible(false);
		panelAbajo.removeAll();
		panelAbajo.setVisible(false);
		cargoPaneles();
	}

//	private void cargoTrenesPantalla() {
//		ArrayList<Tren> lineasTrenesOrdenadas = oPrincipalTrenes.ordenoTrenes(oLectura.getLineas());
//		oPrincipalTrenes.creoTrenes(lineasTrenesOrdenadas, oLectura)
//	}

	private void comprobarCasillaClic(ActionEvent e) {
		for (int f = 0; f < tableroJuego.length; f++) {
			for (int c = 0; c < tableroJuego[f].length; c++) {
				if (e.getSource() == tableroJuego[f][c]) {
					escucharTeclado = true;
					char casillaPulsada = tableroJuego[f][c].getText().charAt(0);
					if (casillaPulsada == '.' || casillaPulsada == 'X') {
						JOptionPane.showMessageDialog(null, "La casilla no que ha pulsado no tiene tren.");
						escucharTeclado = false;
					} else {
						oTrenSeleccionado = oTablero.buscoTren(casillaPulsada, trenes, f, c);
					}
				}
			}
		}
	}

	private void pedirDatosTren() {
		boolean dimensionesCorrectas = true;
		int fila = 0;
		String numeroFilasString = JOptionPane.showInputDialog("Introduzca en que fila está la cabeza.");
		int col = 0;
		String numeroColumnasString = JOptionPane.showInputDialog("Introduzca en que columna está la cabeza.");
		int longitud = 0;
		String longitudString = JOptionPane.showInputDialog("Introduzca la longitud del tren.");
		try {
			fila = Integer.parseInt(numeroFilasString);
			col = Integer.parseInt(numeroColumnasString);
			longitud = Integer.parseInt(longitudString);
		} catch (Exception e) {
			dimensionesCorrectas = false;
		}
		String direccionTren = JOptionPane.showInputDialog("Introduzca la dirección del tren\nA, B, D o I.");
		char direccionChar = direccionTren.charAt(0);
		if (dimensionesCorrectas) {
			Tren oTren = new Tren(direccionChar, fila, col, longitud, oTablero.getTablero().length);
			trenes.add(oTren);
			oTablero.colocoTren(oTren);
			oMovimientos.addMovimientoRealizado(trenes);
			cargoPanelesCrearYArchivo();
		} else {
			JOptionPane.showMessageDialog(null, "Has introducido mal el tren.");
		}
	}

	private void pedirDatosTablero() {
		boolean dimensionesCorrectas = true;
		String numeroFilasString = JOptionPane.showInputDialog("Introduzca un número de filas.");
		try {
			filasTableroJuego = Integer.parseInt(numeroFilasString);
		} catch (Exception e1) {
			dimensionesCorrectas = false;
		}
		String numeroColumnasString = JOptionPane.showInputDialog("Introduzca un número de columnas.");
		try {
			columnasTableroJuego = Integer.parseInt(numeroColumnasString);
		} catch (Exception e1) {
			dimensionesCorrectas = false;
		}
		if (filasTableroJuego < 10 || filasTableroJuego > 30 || columnasTableroJuego < 10
				|| columnasTableroJuego > 30) {
			dimensionesCorrectas = false;
		}
		if (dimensionesCorrectas) {
			tableroJuego = new JButton[filasTableroJuego][columnasTableroJuego];
			oTablero = new Tablero(filasTableroJuego, columnasTableroJuego);
			cargoPanelesCrearYArchivo();
			menuArchivoGuardarComo.setEnabled(true);
			menuAnadirTren.setEnabled(true);
			menuEliminarTren.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(null, "Has introducido datos erroneos.");
		}
	}

	private void cargoPanelAbajo() {
		panelAbajo.removeAll();
		panelAbajo.setVisible(false);
		panelAbajo.setBackground(Color.YELLOW);
		panelAbajo.add(etiquetaRaton);
		//panelAbajo.add(botonMoverUnTren);
		panelAbajo.setVisible(true);
	}

	private void cargoPanelIzq() {

		panelIzq.removeAll();
		panelIzq.setVisible(false);

		JPanel panelIzqVacio = new JPanel();
		JPanel panelIzqNumeros = new JPanel();
		panelIzq.setLayout(new BorderLayout());

		panelIzq.add(panelIzqVacio, BorderLayout.NORTH);
		panelIzqVacio.setBackground(Color.YELLOW);
		// panelIzqVacio.add(etiquetaRaton);

		panelIzq.add(panelIzqNumeros, BorderLayout.CENTER);
		panelIzqNumeros.setBackground(Color.YELLOW);

		panelIzqNumeros.setLayout(new GridLayout(filasTableroJuego, 1, 2, 2));
		for (int f = 0; f < tableroJuego.length; f++) {
			int numeracionInversa = tableroJuego.length - f - 1;
			String numeroString = Integer.toString(numeracionInversa);
			Font fuente = new Font("Arial", Font.BOLD, 6);
			JButton oBoton = new JButton(numeroString);
			oBoton.setFont(fuente);
			oBoton.setBackground(new Color(255, 87, 51));
			panelIzqNumeros.add(oBoton);
		}

		panelIzq.setVisible(true);
		panelIzqVacio.setVisible(true);
		panelIzqNumeros.setVisible(true);
	}

	private void cargoPanelArriba() {
		panelArriba.removeAll();
		panelArriba.setVisible(false);
		panelArriba.setLayout(new GridLayout(1, columnasTableroJuego, 2, 2));
		for (int c = 0; c < tableroJuego[0].length; c++) {
			String numeroString = Integer.toString(c);
			Font fuente = new Font("Arial", Font.BOLD, 6);
			JButton oBoton = new JButton(numeroString);
			oBoton.setFont(fuente);
			oBoton.setBackground(new Color(255, 87, 51));
			panelArriba.add(oBoton);
		}
		panelArriba.setVisible(true);
	}

	private void cargoPanelConTablero() {
		panelIzq.removeAll();
		cargoPanelIzq();

		panelCentro.removeAll();
		panelCentro.setVisible(false);
		cargoPaneles();
		// cargoPanelCentroInicial();

		panelTablero.removeAll();
		panelTablero.setVisible(false);
		panelTablero.setBackground(Color.WHITE);
		panelTablero.setLayout(new GridLayout(filasTableroJuego, columnasTableroJuego, 2, 2));

		dibujoTablero();
		panelCentro.setVisible(true);
		panelTablero.setVisible(true);
		panelAbajo.setVisible(true);
	}

	private void dibujoTablero() {
		for (int f = 0; f < tableroJuego.length; f++) {
			for (int c = 0; c < tableroJuego[f].length; c++) {
				JButton oBoton = new JButton(Character.toString(oTablero.getTablero()[f][c]));
				tableroJuego[f][c] = oBoton;
				tableroJuego[f][c].addActionListener(this);
				tableroJuego[f][c].addMouseListener(this);
				tableroJuego[f][c].addKeyListener(this);
				panelTablero.add(tableroJuego[f][c]);

				if (tableroJuego[f][c].getText().charAt(0) == 'A') {
					tableroJuego[f][c].setBackground(Color.BLUE);
				} else if (tableroJuego[f][c].getText().charAt(0) == 'B') {
					tableroJuego[f][c].setBackground(Color.GREEN);
				} else if (tableroJuego[f][c].getText().charAt(0) == 'I') {
					tableroJuego[f][c].setBackground(Color.CYAN);
				} else if (tableroJuego[f][c].getText().charAt(0) == 'D') {
					tableroJuego[f][c].setBackground(Color.MAGENTA);
				} else if (tableroJuego[f][c].getText().charAt(0) == 'X') {
					tableroJuego[f][c].setBackground(Color.RED);
				}

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (escucharTeclado) {
			String mensaje1 = "El tren se mueve una casilla";
			String mensaje2 = "Tecla incorrecta";
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				muevoUtilizandoTeclas(mensaje1, mensaje2, 'I');
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				muevoUtilizandoTeclas(mensaje1, mensaje2, 'D');
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				muevoUtilizandoTeclas(mensaje1, mensaje2, 'A');
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				muevoUtilizandoTeclas(mensaje1, mensaje2, 'B');
			}
		}
	}

	private void muevoUtilizandoTeclas(String mensaje1, String mensaje2, char direccion) {
		if (oTrenSeleccionado.getDireccion() == direccion) {
			// JOptionPane.showMessageDialog(null, mensaje1);
			oTablero.muevoTren(oTrenSeleccionado, trenes);
			oMovimientos.addMovimientoRealizado(trenes);
			cargoPanelConTablero();
			oTablero.dibujoTablero();
			menuDeshacer.setEnabled(true);
			menuRehacer.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(null, mensaje2);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	private ArrayList<Tren> ordenarTrenes(ArrayList<String> lineas) {
		// ArrayList<Tren> trenes = new ArrayList<Tren>();
		for (int i = 0; i < lineas.size(); i++) {
			Tren oTren = new Tren(lineas.get(i), tableroJuego.length, tableroJuego[0].length);
			trenes.add(oTren);
		}

		ArrayList<Tren> trenesOrdenados = new ArrayList<Tren>();

		bucleOrdenar('B', trenesOrdenados, trenes);
		bucleOrdenar('A', trenesOrdenados, trenes);
		bucleOrdenar('I', trenesOrdenados, trenes);
		bucleOrdenar('D', trenesOrdenados, trenes);

		return trenesOrdenados;
	}

	private static void bucleOrdenar(char c, ArrayList<Tren> trenesOrdenados, ArrayList<Tren> trenes) {
		for (int i = 0; i < trenes.size(); i++) {
			if (trenes.get(i).getDireccion() == c) {
				trenesOrdenados.add(trenes.get(i));
			}
		}
	}

}
