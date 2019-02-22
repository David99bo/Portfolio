package vista;

import javax.swing.*;
import java.awt.*;

public class FrameAgregar extends JFrame{

	private static FrameAgregar instance;
	private JLabel labNombre;
	private JLabel labVolumen;
	private JLabel labInfo;
	private JTextField textNombre;
	private JTextField textVolumen;
	private JTextArea textInfo;
	private JScrollPane scroll;
	private JButton agregar;
	

	public static FrameAgregar getInstance(){
		if(instance==null) {
		instance = new FrameAgregar();
		}
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	private FrameAgregar() {
		setResizable(false);
	setLayout(new BorderLayout());
	setSize(300,300);
	labNombre = new JLabel("Nombre:");
	labVolumen = new JLabel("Volumen:");
	labInfo = new JLabel("Info:");
	textNombre = new JTextField();
	textVolumen = new JTextField();
	textInfo = new JTextArea();
	agregar = new JButton("Agregar");
	scroll = new JScrollPane(textInfo);
	JPanel aux = new JPanel(new GridLayout(0, 1));
	aux.add(labNombre);
	aux.add(textNombre);
	aux.add(labVolumen);
	aux.add(textVolumen);
	aux.add(labInfo);
	aux.add(scroll);
	
	aux.setBorder(BorderFactory.createTitledBorder("Información del Producto"));
	
	add(aux, BorderLayout.CENTER);
	add(agregar, BorderLayout.SOUTH);
	
	
	}

	public JLabel getLabNombre() {
		return labNombre;
	}

	public void setLabNombre(JLabel labNombre) {
		this.labNombre = labNombre;
	}

	public JLabel getLabVolumen() {
		return labVolumen;
	}

	public void setLabVolumen(JLabel labVolumen) {
		this.labVolumen = labVolumen;
	}

	public JLabel getLabInfo() {
		return labInfo;
	}

	public void setLabInfo(JLabel labInfo) {
		this.labInfo = labInfo;
	}

	public JTextField getTextNombre() {
		return textNombre;
	}

	public void setTextNombre(JTextField textNombre) {
		this.textNombre = textNombre;
	}

	public JTextField getTextVolumen() {
		return textVolumen;
	}

	public void setTextVolumen(JTextField textVolumen) {
		this.textVolumen = textVolumen;
	}

	public JTextArea getTextInfo() {
		return textInfo;
	}

	public void setTextInfo(JTextArea textInfo) {
		this.textInfo = textInfo;
	}

	public JButton getAgregar() {
		return agregar;
	}

	public void setAgregar(JButton agregar) {
		this.agregar = agregar;
	}
	
	
	
}
