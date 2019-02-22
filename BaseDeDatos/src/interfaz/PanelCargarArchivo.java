package interfaz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
public class PanelCargarArchivo extends JPanel implements ActionListener{

	public final static String AGREGAR="Agregar";
	public final static String GUARDAR="Guardar";
	private JButton botonCargar;
	private InterfazBaseDatos principal;
	
	public PanelCargarArchivo (InterfazBaseDatos v){
		principal=v;
		TitledBorder border = new TitledBorder("Agregar Archivo");
		setBorder(border);
		botonCargar=new JButton("Examinar");
		botonCargar.setActionCommand(AGREGAR);
		botonCargar.addActionListener(this);
		add(botonCargar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(AGREGAR)){
			principal.cargarArchivo();
		}
		
		
	}
}
