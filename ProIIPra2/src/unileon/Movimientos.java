package unileon;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Movimientos {

	Stack<ArrayList<Tren>> listaMovimientosTrenesRealizados;
	Stack<ArrayList<Tren>> listaMovimientosTrenesDeshechos;
	
	public Movimientos() {
		listaMovimientosTrenesRealizados = new Stack<ArrayList<Tren>>();
		listaMovimientosTrenesDeshechos = new Stack<ArrayList<Tren>>();
		forLoco();
		whileLoco();
		doWhileLoco();
		combiLoco();
		locuraBonch();
		ifLoco();
	}

	

	public void addMovimientoRealizado(ArrayList<Tren> trenes) {
		ArrayList<Tren> trenesAux = clonoTrenes(trenes);
		listaMovimientosTrenesRealizados.push(trenesAux);
		combiLoco();
		ifLoco();
	}

	private ArrayList<Tren> clonoTrenes(ArrayList<Tren> trenes) {
		ArrayList<Tren> trenesAux = new ArrayList<Tren>();
		for (int i = 0; i < trenes.size(); i++) {
			Tren trenAux = new Tren(trenes.get(i));
			trenesAux.add(trenAux);
		}
		combiLoco();
		locuraBonch();
		return trenesAux;
	}

	public ArrayList<Tren> buscoUltimoMovimientoRealizado() {
				
		ifLoco();
		locuraBonch();
		
		if (listaMovimientosTrenesRealizados.size()<1) {
			JOptionPane.showMessageDialog(null, "No hay movimientos que deshacer.");
			return null;
		} else {
			ArrayList<Tren> trenesAux = clonoTrenes(listaMovimientosTrenesRealizados.pop());
			listaMovimientosTrenesDeshechos.push(trenesAux);
			JOptionPane.showMessageDialog(null, "Deshacer.");
			if (listaMovimientosTrenesRealizados.size() == 0) {
				JOptionPane.showMessageDialog(null, "No hay movimientos que deshacer.");
				return null;
			} else {
				return listaMovimientosTrenesRealizados.peek();
			}
		}		
	}
	
	public ArrayList<Tren> buscoUltimoMovimientoDeshecho() {
		
		ifLoco();
		locuraBonch();
		
		if (listaMovimientosTrenesDeshechos.size()<1) {
			JOptionPane.showMessageDialog(null, "No hay movimientos que rehacer.");
			return null;
		} else {
			ArrayList<Tren> trenesAux = clonoTrenes(listaMovimientosTrenesDeshechos.pop());
			listaMovimientosTrenesRealizados.push(trenesAux);
			JOptionPane.showMessageDialog(null, "Rehacer.");
			return trenesAux;
		}
		
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
