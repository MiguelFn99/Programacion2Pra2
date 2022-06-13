package unileon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Lectura {

	private String nombreArchivo = "";
	private ArrayList<String> lineas = new ArrayList<String>();
	private ArrayList<String> lineasColision = new ArrayList<String>();
	private int numFilas;
	private int numColumnas;
	
	public String getNombreArchivo() {
		forLoco();
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public ArrayList<String> getLineas() {
		whileLoco();
		return lineas;
	}

	public void setLineas(ArrayList<String> lineas) {
		this.lineas = lineas;
	}

	public ArrayList<String> getLineasColision() {
		doWhileLoco();
		return lineasColision;
	}

	public void setLineasColision(ArrayList<String> lineasColision) {
		this.lineasColision = lineasColision;
	}

	public int getNumFilas() {
		combiLoco();
		return numFilas;
	}

	public void setNumFilas(int numFilas) {
		this.numFilas = numFilas;
	}

	public int getNumColumnas() {
		locuraBonch();
		return numColumnas;
	}

	public void setNumColumnas(int numColumnas) {
		this.numColumnas = numColumnas;
	}

	public Lectura() {
		lecturaArchivo();
		locuraBonch();
		ifLoco();
	}

	private void lecturaArchivo() {
		ArrayList<String> lineasFichero = new ArrayList<String>();
		nombreArchivo = leoFicheroVentana();
		if (nombreArchivo.equals("")) {
			JOptionPane.showMessageDialog(null, "No se ha abierto ningún fichero.");
		} else {
			lineasFichero = leoFichero(nombreArchivo);
			sacoInfoFichero(lineasFichero);
		}
		
		locuraBonch();
		forLoco();
		whileLoco();
		doWhileLoco();
		combiLoco();
		ifLoco();
	}

	private void sacoInfoFichero(ArrayList<String> lineasFichero) {
		int contador=0;
        String[] filasColumnasVector=lineasFichero.get(contador).split(" ");
        numFilas=Integer.parseInt(filasColumnasVector[0]);
        numColumnas=Integer.parseInt(filasColumnasVector[1]);
        contador++;

        int numeroTrenes=Integer.parseInt(lineasFichero.get(contador));
        contador++;

        for (int i = 0; i < numeroTrenes; i++) {
            String linea=lineasFichero.get(contador);
            contador++;
            lineas.add(linea);
        }
        int numCasillasColision=Integer.parseInt(lineasFichero.get(contador));
        contador++;
        for (int i = 0; i < numCasillasColision; i++) {
            String linea=lineasFichero.get(contador);
            contador++;
            lineasColision.add(linea);
        }
	}

	private ArrayList<String> leoFichero(String rutaFichero) {
		FileReader fr = null;
        try {
            fr = new FileReader(rutaFichero);
        } catch (FileNotFoundException e) {
            // Si hay algun error.....
            //e.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(fr);
        String linea;
        ArrayList<String> listaLectura=new ArrayList<String>();
        try {
            while ((linea = bf.readLine())!=null) {
                listaLectura.add(linea);
            }
        } catch (IOException e) {
            // Si hay algun error.....
            //e.printStackTrace();
        }
        return listaLectura;
	}

	private String leoFicheroVentana() {
		String nombreArchivo = "";
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			nombreArchivo = file.getAbsolutePath();
		} else {
			
		}
		return nombreArchivo;
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
