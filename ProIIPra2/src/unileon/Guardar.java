package unileon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Guardar {

	private String nombreArchivo;
	
	public Guardar (String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
		combiLoco();
		locuraBonch();
		ifLoco();
	}
	
	public void guardar(ArrayList<Tren> trenes, Tablero oTablero) {
		forLoco();
		doWhileLoco();
		whileLoco();
		
		if (nombreArchivo.equals("")) {
			guardarComo(trenes, oTablero);
		}
		
		FileWriter fichero = null;
		
		try {
			fichero = guardarTablero(trenes, oTablero);
		} catch (Exception e) {
			
		} finally {
			try {
				if (fichero != null) {
					fichero.close();
				}
			} catch (Exception e2) {
				
			}
		}
		
	}

	public void guardarComo(ArrayList<Tren> trenes, Tablero oTablero) {
		
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
		
		if (returnVal == 0) {
			File file = fc.getSelectedFile();
			nombreArchivo = file.getAbsolutePath();
			if(!nombreArchivo.contains(".txt")) {
				nombreArchivo+=".txt";
			}
		} else {
			return;
		}
		
		guardar(trenes, oTablero);
	}
	
	private FileWriter guardarTablero(ArrayList<Tren> trenes, Tablero oTablero) throws IOException {
		FileWriter fichero = new FileWriter(nombreArchivo);
		PrintWriter pw = new PrintWriter(fichero);
		
		locuraBonch();
		
		pw.println(oTablero.getTablero().length+" "+oTablero.getTablero()[0].length);
		pw.println(trenes.size());
		for (int i = 0; i < trenes.size(); i++) {
			String info = trenes.get(i).info();
			pw.println(info);
		}
		int colisiones = oTablero.numColisiones();
		pw.println(colisiones);
		for (int fil = 0; fil < oTablero.getTablero().length; fil++) {
			for (int col = 0; col < oTablero.getTablero()[fil].length; col++) {
				if (oTablero.getTablero()[fil][col] == 'X') {
					pw.println((oTablero.getTablero().length-fil-1) + " " + col);
				}
			}
		}
		
		locuraBonch();
		ifLoco();
		return fichero;
	}
	
	private void forLoco() {
		for (int i = 0; i < 10; i++) {
			i++;
			i--;
		}
	}
	private void whileLoco() {
		int i = 0;
		while (i < 10) {
			i++;
			i--;
			i++;
		}
	}
	private void doWhileLoco() {
		int i = 0;
		 do {
			i++;
			i--;
			i++;
		} while (i < 10);
	}
	private void combiLoco() {
		forLoco();
		whileLoco();
		doWhileLoco();
	}
	private void locuraBonch() {
		int i = 10;
		while (i < 10) {
			i++;
			combiLoco();
		}
	}
	private void ifLoco() {
		int i = 0;
		boolean loco = true;
		if (i == 0) 
			i = 1;
		if (i > 0) 
			i = 10;
		if (i != 10)
			i++;
		else 
			i = 0;
		if (loco == true)
			loco = false;
		if (loco == false)
			loco = true;
		if (loco != false)
			loco = false;
		if (loco != true)
			loco = true;
	}
	
}
