package vista;

import javax.swing.*;
import java.awt.*;

public class PanelProductoInf extends JFrame{

	
	private static PanelProductoInf instance;
	private JLabel labTitl;
	private JLabel labNombre;
	private JLabel labVolum;
	private JLabel labInfoExtra;
	private JTextArea infoExtra;
	
	public PanelProductoInf() {
		setLayout(new BorderLayout());
		setSize(300,300);
		labTitl = new JLabel("Informacion del producto");
		labNombre = new JLabel("Nombre: ");
		labVolum = new JLabel("Volumen: ");
		labInfoExtra = new JLabel("Información Extra");
		infoExtra = new JTextArea();
		JPanel aux = new JPanel(new GridLayout(0,1));
		aux.setBorder(BorderFactory.createTitledBorder("Información"));
		aux.add(labTitl);
		aux.add(labNombre);
		aux.add(labVolum);
		aux.add(labInfoExtra);
		aux.add(infoExtra);
		add(aux);
		infoExtra.setEditable(false);
		
	}
	
	public static PanelProductoInf getInstance(){
		if(instance==null) {
		instance = new PanelProductoInf();
		}
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public JLabel getLabTitl() {
		return labTitl;
	}

	public void setLabTitl(JLabel labTitl) {
		this.labTitl = labTitl;
	}

	public JLabel getLabNombre() {
		return labNombre;
	}

	public void setLabNombre(String labNombre) {
		this.labNombre.setText("Nombre: "+labNombre);
	}

	public JLabel getLabVolum() {
		return labVolum;
	}

	public void setLabVolum(String labVolum) {
		this.labVolum.setText("Volumen: "+labVolum);
	}

	public JLabel getLabInfoExtra() {
		return labInfoExtra;
	}

	public void setLabInfoExtra(JLabel labInfoExtra) {
		this.labInfoExtra = labInfoExtra;
	}

	public JTextArea getInfoExtra() {
		return infoExtra;
	}

	public void setInfoExtra(JTextArea infoExtra) {
		this.infoExtra = infoExtra;
	}
	
	
	
}
