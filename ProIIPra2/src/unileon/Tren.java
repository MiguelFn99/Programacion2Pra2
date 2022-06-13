package unileon;

import java.util.ArrayList;

public class Tren {

	// Variables de la clase tren
	private char direccion;
	private int longitud;
	private int fila;
	private int filaTablero;
	private int columna;
	private boolean esCorrecto;
	private boolean estaCaido;
	private int tamTablero;
	private int tamTableroFila;
	private int tamTableroCol;

	// Contructor
	public Tren(String linea, int tamFil, int tamCol) {
		this.esCorrecto = true;
		this.estaCaido = false;
		String[] lineaVector = linea.split(" ");
		this.direccion = lineaVector[0].charAt(0);
		this.longitud = Integer.parseInt(lineaVector[1]);
		this.fila = Integer.parseInt(lineaVector[3]);
		this.columna = Integer.parseInt(lineaVector[2]);
		this.filaTablero = tamFil - fila - 1;
		this.tamTableroFila = tamFil;
		this.tamTableroCol = tamCol;

//		System.out.println(direccion + " " + longitud + " " + 
//				columna + " " + fila);

		// Linea tenga 4 caracteres
		comprueboLinea(lineaVector);
		// Compruebo dirección correcta
		comprueboDireccion(direccion);
		// Compruebo longitud correcta
		comprueboLongitud(longitud);
		// Compruebo coordenadas dentro tablero
		comprueboCoordenadas(fila, columna);
		// Compruebo que los trenes estén dentro del tablero
		comprueboTrenDentroTablero(direccion, longitud, filaTablero, columna);

		// Compruebo la variable esCorrecto
		// comprueboEsCorrecto();

	}

	// Constructor trenes aux, para cortar los trenes
	public Tren(int fil, int col, int longitudTrenAux, char direc) {
		this.direccion = direc;
		this.longitud = longitudTrenAux;
		this.columna = col;
		this.filaTablero = fil;
		this.esCorrecto = true;
	}

	// Crear los trenes entorno gráfico
	public Tren(char direccion, int fil, int col, int longitud, int tamFilasTablero) {
		this.direccion = direccion;
		this.longitud = longitud;
		this.columna = col;
		this.fila = fil;
		this.filaTablero = tamFilasTablero - fila - 1;
		this.esCorrecto = true;

		// Compruebo que los trenes estén dentro del tablero
		comprueboTrenDentroTablero(direccion, longitud, filaTablero, columna);

	}

	public Tren(Tren tren) {
		this.direccion = tren.direccion;
		this.longitud = tren.longitud;
		this.filaTablero = tren.filaTablero;
		this.columna = tren.columna;
		this.fila = tren.fila;
		this.esCorrecto = true;
	}

	private void comprueboTrenDentroTablero(char dir, int lon, int fil, int col) {
		switch (dir) {
		case 'D':
			if (col - lon < -1) {
				esCorrecto = false;
				System.out.println("Tren fuera tablero D");
			}
			break;
		case 'I':
			if (col + lon > tamTableroCol) {
				esCorrecto = false;
				System.out.println("Tren fuera tablero I");
			}
			break;
		case 'A':
			if (fil + lon > tamTableroFila) {
				esCorrecto = false;
				System.out.println("Tren fuera tablero A");
			}
			break;
		case 'B':
			if (fil - lon < -1) {
				esCorrecto = false;
				System.out.println("Tren fuera tablero B");
			}
			break;
		default:
			break;
		}
	}

	private void comprueboEsCorrecto() {
		if (esCorrecto == false) {
			System.out.println("Conjunto de trenes incorrecto");
			System.exit(0);
		}
	}

	private void comprueboCoordenadas(int fil, int col) {
		if (fil < 0 || fil > 29) {
			esCorrecto = false;
			System.out.println("Compruebo coordenadas");
		}

		if (col < 0 || col > 29) {
			esCorrecto = false;
			System.out.println("Compruebo coordenadas");
		}
	}

	private void comprueboLongitud(int lon) {
		if (lon < 1 || lon > 30) {
			System.out.println("Compruebo longitud");
			esCorrecto = false;
		}
	}

	private void comprueboDireccion(char direc) {
		if (direc != 'D' && direc != 'I' && direc != 'A' && direc != 'B') {
			System.out.println("Compruebo dirección");
			esCorrecto = false;
		}
	}

	private void comprueboLinea(String[] linea) {

		if (linea.length != 4) {
			esCorrecto = false;
		}
	}

	public int pasarFilasTableroAFilas(int tamFilasTablero) {
		return tamFilasTablero - filaTablero - 1;
	}

	public int pasarFilasAFilasTablero(int tamFilasTablero) {
		return tamFilasTablero - fila - 1;
	}

	public char getDireccion() {
		return direccion;
	}

	public void setDireccion(char direccion) {
		this.direccion = direccion;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean isEsCorrecto() {
		return esCorrecto;
	}

	public void setEsCorrecto(boolean esCorrecto) {
		this.esCorrecto = esCorrecto;
	}

	public boolean isEstaCaido() {
		return estaCaido;
	}

	public void setEstaCaido(boolean estaCaido) {
		this.estaCaido = estaCaido;
	}

	public int getFilaTablero() {
		return filaTablero;
	}

	public void setFilaTablero(int filaTablero) {
		this.filaTablero = filaTablero;
	}

	public int getTamTablero() {
		return tamTablero;
	}

	public void setTamTablero(int tamTablero) {
		this.tamTablero = tamTablero;
	}

	// Muevo el tren en la posición indicada
	public void muevoTren(char casilla, Tren tren, ArrayList<Tren> trenes) {

		if (casilla == 'X' || casilla == 'E') {
			if (tren.getLongitud() > 0) {
				longitud--;
			}
		} else {
			switch (direccion) {
			case 'A':
				filaTablero--;
				break;
			case 'B':
				filaTablero++;
				break;
			case 'I':
				columna--;
				break;
			case 'D':
				columna++;
				break;
			}
		}

//		char direcTren = this.getDireccion();
//		if (casilla == '.') {
//			switch (direcTren) {
//			case 'A':
//				this.filaTablero = this.getFilaTablero() - 1;
//				break;
//			case 'B':
//				this.filaTablero = this.getFilaTablero() + 1;
//				break;
//			case 'D':
//				this.columna = this.getColumna() + 1;
//				break;
//			case 'I':
//				this.columna = this.getColumna() - 1;
//				break;
//			}
//		} else if (casilla == 'E' || casilla == 'X') {
//			this.longitud = this.getLongitud() - 1;
//		} else {
//			switch (direccion) {
//			case 'A':
//				filaTablero--;
//				break;
//			case 'B':
//				filaTablero++;
//				break;
//			case 'D':
//				columna++;
//				break;
//			case 'I':
//				columna--;
//				break;
//			}
//		}
	}

	public boolean trenColisionado(int fil, int col) {
		if (direccion == 'A' && columna == col) {
			if (fil >= filaTablero && fil < (filaTablero + longitud))
				return true;
		} else if (direccion == 'B' && columna == col) {
			if (fil <= filaTablero && fil > (filaTablero - longitud))
				return true;
		} else if (direccion == 'D' && filaTablero == fil) {
			if (col <= columna && col > (columna - longitud))
				return true;
		} else if (direccion == 'I' && filaTablero == fil) {
			if (col >= columna && col < (columna + longitud))
				return true;
		}
		// System.out.println("no debería estar aquí");
		return false;
	}

	public Tren cortoTren(int fil, int col) {
		Tren trenAux = null;
		int aux = longitud;

		// Choque en la cabeza
		if (filaTablero == fil && columna == col) {

		} else {
			// Choque cola y centro
			switch (direccion) {
			case 'A':
				trenAux = choqueDirA(fil, col, trenAux, aux);
				break;
			case 'B':
				trenAux = choqueDirB(fil, col, trenAux);
				break;
			case 'D':
				trenAux = choqueDirD(fil, col, trenAux, aux);
				break;
			case 'I':
				trenAux = choqueDirI(fil, col, trenAux, aux);
				break;
			default:
				break;
			}
		}
		return trenAux;
	}

	private Tren choqueDirI(int fil, int col, Tren trenAux, int aux) {
		//int aux = longitud;
		if (columna + longitud - 1 == col) {
			longitud--;
		} else {
			longitud = col-columna;
			int longitudTrenAux = aux - longitud;
			trenAux = new Tren(fil, col, longitudTrenAux, 'I');
		}
		return trenAux;
	}

	private Tren choqueDirD(int fil, int col, Tren trenAux, int aux) {
		if (columna - longitud + 1 == col) {
			longitud--;
		} else {
			longitud = columna - col;
			int longitudTrenAux = aux - longitud;
			trenAux = new Tren(fil, col, longitudTrenAux, 'D');
		}
		return trenAux;
	}

	private Tren choqueDirB(int fil, int col, Tren trenAux) {
		if (filaTablero - longitud + 1 == fil) {
			longitud--;
		} else {
			int longitudTrenAux = fil - longitud;
			longitud = longitud - longitudTrenAux;
			trenAux = new Tren(fil, col, longitudTrenAux, 'B');
		}
		return trenAux;
	}

	private Tren choqueDirA(int fil, int col, Tren trenAux, int aux) {
		if (fil == filaTablero + longitud - 1) {
			longitud--;
		} else {
			longitud = fil - filaTablero;
			int longitudTrenAux = aux - longitud;
			trenAux = new Tren(fil, col, longitudTrenAux, 'A');
		}
		return trenAux;
	}

	@Override
	public String toString() {
		return "Tren direccion=" + direccion + ", longitud=" + longitud + ", filaTablero=" + filaTablero + ", columna="
				+ columna;
	}

	public String info() {
		return (direccion + " " + longitud + " " + columna + " " + fila);
	}

}
