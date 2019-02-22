package vista;

import javax.swing.*;
import java.awt.*;

public class PanelRecarga extends JFrame{

	private static PanelRecarga instance;
	private JButton butRecargar;
	private JLabel labProducto;
	private JTextField cantidad;
	
	private PanelRecarga() {
		setSize(300,100);
		setLayout(new GridLayout(1,2));
		butRecargar = new JButton("Recargar!");
		add(butRecargar);
		JPanel aux = new JPanel(new GridLayout(0,1));
		labProducto = new JLabel("Producto: ");
		aux.add(labProducto);
		cantidad = new JTextField();
		aux.add(cantidad);
		add(aux);
		setResizable(false);
	}
	
	public static PanelRecarga getInstance(){
		if(instance==null) {
		instance = new PanelRecarga();
		}
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public JButton getButRecargar() {
		return butRecargar;
	}

	public void setButRecargar(JButton butRecargar) {
		this.butRecargar = butRecargar;
	}

	public JLabel getLabProducto() {
		return labProducto;
	}

	public void setLabProducto(String prod) {
		this.labProducto.setText("Producto: "+prod);
	}

	public JTextField getCantidad() {
		return cantidad;
	}

	public void setCantidad(JTextField cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
