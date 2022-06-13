package unileon;

import java.util.ArrayList;

public class Tablero {

	private char tablero[][];

	public Tablero(int tamFil, int tamCol) {
		this.tablero = new char[tamFil][tamCol];
		inicializoTablero();
	}

	public char[][] getTablero() {
		return tablero;
	}

	public void inicializoTablero() {
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int col = 0; col < tablero[0].length; col++) {
				if (tablero[fila][col] != 'X')
					tablero[fila][col] = '.';
			}
		}
	}

	public void dibujoTablero() {
		System.out.println("  0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2");
		System.out.println("  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");
		for (int fila = 0; fila < tablero.length; fila++) {
			if (tablero.length - fila - 1 < 10) {
				System.out.print("0");
			}
			System.out.print(tablero.length - fila - 1 + " ");
			for (int col = 0; col < tablero[0].length; col++) {
				if (col < tablero[fila].length - 1) {
					System.out.print(tablero[fila][col] + " ");
				} else {
					System.out.print(tablero[fila][col]);
				}
			}
			System.out.println();
		}
	}

	public void colocoTren(Tren tren) {
		char direccion = tren.getDireccion();

		switch (direccion) {
		case 'A':
			colocoArriba(tren);
			break;
		case 'I':
			colocoIzquierda(tren);
			break;
		case 'B':
			colocoAbajo(tren);
			break;
		case 'D':
			colocoDerecha(tren);
			break;
		default:
			break;
		}

	}

	private void colocoDerecha(Tren tren) {
		int fil = tren.getFilaTablero();
		int col = tren.getColumna();
		for (int i = 0; i < tren.getLongitud(); i++) {
			if (tablero[fil][col] == '.') {
				tablero[fil][col] = 'D';
			} else {
				tablero[fil][col] = 'X';
			}
			col--;
		}
	}

	private void colocoAbajo(Tren tren) {
		int fil = tren.getFilaTablero();
		int col = tren.getColumna();
		for (int i = 0; i < tren.getLongitud(); i++) {
			if (tablero[fil][col] == '.') {
				tablero[fil][col] = 'B';
			} else {
				tablero[fil][col] = 'X';
			}
			fil--;
		}
	}

	private void colocoArriba(Tren tren) {
		int fil = tren.getFilaTablero();
		int col = tren.getColumna();
		for (int i = 0; i < tren.getLongitud(); i++) {
			if (tablero[fil][col] == '.') {
				tablero[fil][col] = 'A';
			} else {
				tablero[fil][col] = 'X';
			}
			fil++;
		}
	}

	private void colocoIzquierda(Tren tren) {
		int fil = tren.getFilaTablero();
		int col = tren.getColumna();
		for (int i = 0; i < tren.getLongitud(); i++) {
			if (tablero[fil][col] == '.') {
				tablero[fil][col] = 'I';
			} else {
				tablero[fil][col] = 'X';
			}
			col++;
		}
	}

	public void muevoTren(Tren tren, ArrayList<Tren> trenes) {

		char casilla = comprueboCasillaOcupada(tren);
		if (tablero[tren.getFilaTablero()][tren.getColumna()] == 'X') {
			borroTren(tren);
			tren.muevoTren('X', tren, trenes);
			colocoTren(tren);
		} else if (casilla == 'E' || casilla == 'X' || casilla == '.') {
			borroTren(tren);
			tren.muevoTren(casilla, tren, trenes);
			colocoTren(tren);
		} else {
			borroTren(tren);
			tren.muevoTren(casilla, tren, trenes);
			tablero[tren.getFilaTablero()][tren.getColumna()] = 'X';
			colocoTren(tren);
			buscoTrenColision(casilla, trenes, tren.getFilaTablero(), tren.getColumna());
		}


	}

	private void buscoTrenColision(char casilla, ArrayList<Tren> trenes, int fila, int col) {
		for (int i = 0; i < trenes.size(); i++) {
			if (trenes.get(i).getDireccion() == casilla) {
				if (trenes.get(i).trenColisionado(fila, col)) {
					Tren trenAux = trenes.get(i).cortoTren(fila, col);
					if (trenAux != null) {
						trenes.add(i, trenAux);// Lo añado detrás del que he cortado
						break;
					}
				}
			}
		}
	}

	private char comprueboCasillaOcupada(Tren tren) {
		char direccion = tren.getDireccion();
		int fila = tren.getFilaTablero();
		int col = tren.getColumna();
		int lon = tablero.length;

		if ((direccion == 'A') && (fila - 1 >= 0))
			return tablero[fila - 1][col];
		else if ((direccion == 'B') && (fila + 1 < lon))
			return tablero[fila + 1][col];
		else if ((direccion == 'D') && (col + 1 < lon))
			return tablero[fila][col + 1];
		else if ((direccion == 'I') && (col - 1 >= 0))
			return tablero[fila][col - 1];
		else
			return 'E';
	}

	public boolean comprueboTablero() {
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int col = 0; col < tablero[fila].length; col++) {
				if (tablero[fila][col] == 'A' || tablero[fila][col] == 'B' || tablero[fila][col] == 'D'
						|| tablero[fila][col] == 'I')
					return true;
			}
		}
		return false;
	}

	public Tren buscoTren(char casilla, ArrayList<Tren> trenes, int fila, int col) {
		for (int i = 0; i < trenes.size(); i++) {
			if (trenes.get(i).getDireccion() == casilla) {
				if (trenes.get(i).trenColisionado(fila, col)) {
					return trenes.get(i);
				}
			}
		}
		return null;
	}

	public void pongoColisionesTablero(ArrayList<String> lineasColision) {
		for (int i = 0; i < lineasColision.size(); i++) {
			String[] lineaVector = lineasColision.get(i).split(" ");
			int fila = tablero.length - Integer.parseInt(lineaVector[0]) - 1;
			int columna = Integer.parseInt(lineaVector[1]);
			tablero[fila][columna] = 'X';
		}
	}

	public int numColisiones() {
		int numColisiones = 0;

		for (int fil = 0; fil < tablero.length; fil++) {
			for (int col = 0; col < tablero[fil].length; col++) {
				if (tablero[fil][col] == 'X') {
					numColisiones++;
				}
			}
		}

		return numColisiones;
	}

	public void borroTren(Tren tren) {

		int fil = tren.getFilaTablero();
		int col = tren.getColumna();
		char dir = tren.getDireccion();

		for (int i = 0; i < tren.getLongitud(); i++) {
			switch (dir) {
			case 'A':
				// System.out.println(fil + " " + col);
				if (tablero[fil][col] != 'X') {
					tablero[fil][col] = '.';
				}
				fil++;
				break;
			case 'B':
				// System.out.println(fil + " " + col);
				if (tablero[fil][col] != 'X') {
					tablero[fil][col] = '.';
				}
				fil--;
				break;
			case 'I':
				// System.out.println(fil + " " + col);
				if (tablero[fil][col] != 'X') {
					tablero[fil][col] = '.';
				}
				col++;
				break;
			case 'D':
				// System.out.println(fil + " " + col);
				if (tablero[fil][col] != 'X') {
					tablero[fil][col] = '.';
				}
				col--;
				break;
			default:
				break;
			}

		}

	}

}
