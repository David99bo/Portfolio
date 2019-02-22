package vista;

import javax.swing.*;

import java.awt.*;

public class PanelListaProd extends JFrame{

	private static PanelListaProd instance;

	private JList<String> prod;
	private JLabel labProd;
	private JButton elegir;
	private JButton agregar;
	private DefaultListModel<String> lista;
	private JScrollPane scroll;
	
	private PanelListaProd() {
		setSize(200,300);
		setResizable(false);
		lista = new DefaultListModel<>();
		setLayout(new BorderLayout());
		JPanel aux = new JPanel(new GridLayout(0,1));
		
		labProd = new JLabel("Lista de productos");
		elegir = new JButton("Elegir producto actual");
		agregar = new JButton("Agregar nuevo producto");
		
		prod = new JList<>(lista);
		scroll = new JScrollPane(prod);
		aux.add(labProd);
		aux.add(scroll);
		aux.add(elegir);
		aux.add(agregar);
		aux.setBorder(BorderFactory.createTitledBorder("Productos"));
		add(aux);

	}
	
	public static PanelListaProd getInstance(){
		if(instance==null) {
		instance = new PanelListaProd();
		}
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public JList<String> getProd() {
		return prod;
	}

	public void setProd(JList<String> prod) {
		this.prod = prod;
	}

	public JLabel getLabProd() {
		return labProd;
	}

	public void setLabProd(JLabel labProd) {
		this.labProd = labProd;
	}

	public JButton getElegir() {
		return elegir;
	}

	public void setElegir(JButton elegir) {
		this.elegir = elegir;
	}

	public JButton getAgregar() {
		return agregar;
	}

	public void setAgregar(JButton agregar) {
		this.agregar = agregar;
	}

	public   DefaultListModel<String> getLista() {
		return lista;
	}

	public void setLista( DefaultListModel<String> lista) {
		this.lista = lista;
	}
	
	
}
