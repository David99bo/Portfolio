package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelAgregar extends JPanel implements ActionListener{

	public final static String AGREGAR = "Agregar";
	private JLabel lblNombre;
	private JLabel lblCodHab;
	private JLabel lblNomDept;
	private JLabel lblNitsNit;
	private JLabel lblDireccion;
	private JLabel lblCorreo;
	private JTextField txtNombre;
	private JTextField txtCodHab;
	private JTextField txtNomDept;
	private JTextField txtNitsNit;
	private JTextField txtDireccion;
	private JTextField txtCorreo;
	private JButton btnAgregar;
	private JPanel aux;
	private InterfazBaseDatos principal;
	
	public PanelAgregar(InterfazBaseDatos v){
		principal = v;
		setLayout(new BorderLayout());
		aux=new JPanel();
		aux.setLayout(new GridLayout(6, 2));
		TitledBorder border = new TitledBorder("Agregar");
		setBorder(border);
		lblNombre = new JLabel("Nombre: ");
		aux.add(lblNombre);
		txtNombre = new JTextField();
		aux.add(txtNombre);
		lblNomDept = new JLabel("Nombre departamento: ");
		aux.add(lblNomDept);
		txtNomDept = new JTextField();
		aux.add(txtNomDept);
		lblDireccion = new JLabel("Direccion: ");
		aux.add(lblDireccion);
		txtDireccion = new JTextField();
		aux.add(txtDireccion);
		lblCorreo = new JLabel("Correo: ");
		aux.add(lblCorreo);
		txtCorreo = new JTextField();
		aux.add(txtCorreo);
		lblCodHab = new JLabel("Codigo habilitacion: ");
		aux.add(lblCodHab);
		txtCodHab = new JTextField();
		aux.add(txtCodHab);
		lblNitsNit = new JLabel("NitsNit: ");
		aux.add(lblNitsNit);
		txtNitsNit = new JTextField();
		aux.add(txtNitsNit);
		add(aux,BorderLayout.CENTER);
		btnAgregar = new JButton("Agregar");
		btnAgregar.setActionCommand(AGREGAR);
		btnAgregar.addActionListener(this);
		add(btnAgregar,BorderLayout.SOUTH);
		
	}
	
	public void agregar(){
		String[] actual = new String[6];
		actual[0]=txtNomDept.getText();
		actual[1]=txtCodHab.getText();
		actual[2]=txtNombre.getText();
		actual[3]=txtNitsNit.getText();
		actual[4]=txtDireccion.getText();
		actual[5]=txtCorreo.getText();
		principal.agregar(actual);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		if(evento.equals(AGREGAR)){
			agregar();
			txtNomDept.setText("");
			txtCodHab.setText("");
			txtNombre.setText("");
			txtNitsNit.setText("");
			txtDireccion.setText("");
			txtCorreo.setText("");
		}
		
		
	}

}
