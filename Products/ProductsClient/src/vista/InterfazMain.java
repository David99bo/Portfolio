package vista;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class InterfazMain extends JFrame{

	private JLabel[] ultimosProd = new JLabel[4];
	private JLabel fecha;
	private JLabel[] cantidadConsDia= new JLabel[3];
	private JLabel llenadoCanasta;
	private JLabel ultimoLlenado;
	private JButton butRecargar;
	private JButton butLista;
	private JButton butProdInfo;
	private JButton butConsumir;
	private JPanel aux;
	private JPanel aux1;
	private JPanel canasta;
	
	public InterfazMain() {
		setSize(400,500);
		setResizable(false);
		setLayout( new GridLayout(0, 1));
		setBackground(Color.WHITE);
		ultimosProd[0] = new JLabel("Ultimos 3 productos en canasta:");
		 aux = new JPanel(new GridLayout(0, 1));
		for (int i = 0; i < ultimosProd.length; i++) {
			if(i!=0) {
				ultimosProd[i] = new JLabel();
			}
			aux.add(ultimosProd[i]);
		}
		aux.setBorder(BorderFactory.createTitledBorder("Top productos"));
		add(aux);
		
		 aux1 = new JPanel(new GridLayout(0, 1));
		cantidadConsDia[0] = new JLabel("Cantidad de producto consumido por dia:");
		cantidadConsDia[1] = new JLabel("Se terminaran en");
		Date date = new Date();
		String[] fechita = date.toString().split(" ");
		fecha = new JLabel("Hoy: "+fechita[0]+" "+fechita[1]+" "+fechita[2]);
		aux1.add(fecha);
		aux1.add(cantidadConsDia[0]);
		aux1.add(cantidadConsDia[1]);
		aux1.setBorder(BorderFactory.createTitledBorder("Cantidad Consumida por día"));
		add(aux1);
		 canasta = new JPanel(new GridLayout(0, 1));
		llenadoCanasta = new JLabel("Actual en canasta: ");
		canasta.add(llenadoCanasta);
		
		ultimoLlenado = new JLabel("La ultima vez que llenaste fue:");
		canasta.add(ultimoLlenado);	
		canasta.setBorder(BorderFactory.createTitledBorder("Canasta"));
		add(canasta);
		
		JPanel botones = new JPanel(new GridLayout(0, 1));
		botones.setBorder(BorderFactory.createTitledBorder("Opciones"));
		butRecargar = new JButton("Recargar producto");
		botones.add(butRecargar);
		butLista = new JButton("Lista productos");
		botones.add(butLista);
		butProdInfo = new JButton("Información del producto");
		botones.add(butProdInfo);
		butConsumir = new JButton("Consumir!");
		botones.add(butConsumir);
		add(botones);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public JLabel[] getUltimosProd() {
		return ultimosProd;
	}

	public void setUltimosProd(String prod1, String prod2, String prod3) {
		this.ultimosProd[1].setText(prod1);
		this.ultimosProd[2].setText(prod2);
		this.ultimosProd[3].setText(prod3);
	}

	public JLabel getFecha() {
		return fecha;
	}

	public void setFecha(JLabel fecha) {
		this.fecha = fecha;
	}



	public JLabel getLlenadoCanasta() {
		return llenadoCanasta;
	}

	public void setLlenadoCanasta(String actual, int cantidad) {
		this.llenadoCanasta.setText("Actual en canasta: "+cantidad+" "+actual);
	}

	public JLabel getUltimoLlenado() {
		return ultimoLlenado;
	}

	public void setUltimoLlenado(String fecha) {
		this.ultimoLlenado.setText("La ultima vez que llenaste fue: "+fecha);;
	}

	public JButton getButRecargar() {
		return butRecargar;
	}

	public void setButRecargar(JButton butRecargar) {
		this.butRecargar = butRecargar;
	}

	public JButton getButLista() {
		return butLista;
	}

	public void setButLista(JButton butLista) {
		this.butLista = butLista;
	}

	public JButton getButConsumir() {
		return butConsumir;
	}

	public void setButConsumir(JButton butConsumir) {
		this.butConsumir = butConsumir;
	}



	public void setButProdInfo(JButton butProdInfo) {
		this.butProdInfo = butProdInfo;
	}

	public JButton getButProdInfo() {
		return butProdInfo;
	}

	public JLabel[] getCantidadConsDia() {
		return cantidadConsDia;
	}

	public void setCantidadConsDia(double cantidad, String fecha) {
		if(cantidad!=-1)this.cantidadConsDia[0].setText("Cantidad de producto consumido por dia: "+cantidad);
		this.cantidadConsDia[1].setText("Se terminaran en: "+fecha+" días");
	}

	public void setLlenadoCanasta(JLabel llenadoCanasta) {
		this.llenadoCanasta = llenadoCanasta;
	}

	public void setUltimoLlenado(JLabel ultimoLlenado) {
		this.ultimoLlenado = ultimoLlenado;
	}

	public JPanel getAux() {
		return aux;
	}

	public void setAux(JPanel aux) {
		this.aux = aux;
	}

	public JPanel getAux1() {
		return aux1;
	}

	public void setAux1(JPanel aux1) {
		this.aux1 = aux1;
	}

	public JPanel getCanasta() {
		return canasta;
	}

	public void setCanasta(JPanel canasta) {
		this.canasta = canasta;
	}

	public void setCantidadConsDia(JLabel[] cantidadConsDia) {
		this.cantidadConsDia = cantidadConsDia;
	}
	
	
	
}
