package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
public class PanelConsultar extends JPanel implements ActionListener{

	public final static String[] CRITERIOS = {"Nombre","NitsNit","Codigo habilitacion"};
	private JLabel lblCrit;
	private JLabel lblValor;
	private JComboBox<String> opciones;
	private JTextField txtValor;
	private JPanel aux1;
	private JPanel aux2;
	private JButton btnBuscar;
	public final static String BUSCAR = "Buscar";
	private InterfazBaseDatos principal;
	
	public PanelConsultar (InterfazBaseDatos v){
		principal=v;
		aux1=new JPanel();
		aux2=new JPanel();
		aux2.setLayout(new BorderLayout());
		btnBuscar= new JButton("Buscar");
		btnBuscar.setActionCommand(BUSCAR);
		btnBuscar.addActionListener(this);
		setLayout(new BorderLayout());
		TitledBorder border = new TitledBorder("Consultar");
		setBorder(border);
		lblCrit= new JLabel("Criterio: ");
		lblValor = new JLabel("Valor: ");
		opciones = new JComboBox<String>(CRITERIOS);
		txtValor = new JTextField();
		aux1.setLayout(new GridLayout(2, 2));
		aux1.add(lblCrit);
		aux1.add(opciones);
		aux1.add(lblValor);
		aux1.add(txtValor);
		add(aux1,BorderLayout.CENTER);
		aux2.add(btnBuscar,BorderLayout.EAST);
		add(aux2,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(BUSCAR)){
			principal.mostrarDatos(txtValor.getText().trim(), (String)opciones.getSelectedItem());
		}
		
	}
}
