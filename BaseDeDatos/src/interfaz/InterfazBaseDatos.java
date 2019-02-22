package interfaz;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;

import mundo.BaseDeDatos;
public class InterfazBaseDatos extends JFrame{
	
	private PanelBanner banner;
	private PanelCargarArchivo cargar;
	private PanelConsultar consultar;
	private PanelAgregar agregar;
	private PanelInformacion informacion;
	private JPanel panelAuxiliar;
	private BaseDeDatos mundo;
	public InterfazBaseDatos(){
		setTitle("Base de datos");
		setLayout(new BorderLayout());
		setResizable(false);
		mundo= new BaseDeDatos();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelAuxiliar=new JPanel();
		panelAuxiliar.setLayout(new BorderLayout());
		banner = new PanelBanner("./data/Imagen1.png");
		add(banner,BorderLayout.NORTH);
		cargar = new PanelCargarArchivo(this);
		add(cargar,BorderLayout.SOUTH);
		consultar = new PanelConsultar(this);
		panelAuxiliar.add(consultar,BorderLayout.NORTH);
		agregar = new PanelAgregar(this);
		add(agregar,BorderLayout.CENTER);
		informacion = new PanelInformacion();
		panelAuxiliar.add(informacion,BorderLayout.CENTER);
		add(panelAuxiliar,BorderLayout.EAST);
		pack();
	}
	
	public void cargarArchivo(){
		
		JFileChooser fc = new JFileChooser("./data");
		int result = fc.showOpenDialog(this);
		if(result==JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			mundo.cargarDatos(file);
		}
		
	}
	
	public void mostrarDatos(String valor,String criterio){
		int reg = mundo.buscarPorCriterio(valor, criterio);
		File arch= new File("./data/datos/R"+reg+".txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(arch));
			String[] datos;
			datos = br.readLine().split(",");
			br.close();
			String cadena="Informacion de: ";
			cadena+=datos[2]+"\n";
			cadena+="Nombre departamento: "+datos[0]+"\n";
			cadena+="Codigo habilitacion: "+datos[1]+"\n";
			cadena+="NitsNit: "+datos[3]+"\n";
			cadena+="Direccion: "+datos[4]+"\n";
			cadena+="Email: "+datos[5]+"\n";
			informacion.setInformacion(cadena);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void agregar(String[] actual){
		mundo.agregar(actual);
	}
	
	public static void main(String[] args) {
		InterfazBaseDatos ventana= new InterfazBaseDatos();
		ventana.setVisible(true);
	}

}
