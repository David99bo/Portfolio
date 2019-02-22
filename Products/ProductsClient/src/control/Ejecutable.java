package control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.*;
import java.util.Date;

import javax.swing.*;

import vista.FrameAgregar;
import vista.InterfazMain;
import vista.PanelListaProd;
import vista.PanelProductoInf;
import vista.PanelRecarga;

public class Ejecutable {

	
	public static InterfazMain ventana;
	public static PanelProductoInf panelprodinf;
	public static PanelListaProd panellistaprod;
	public static PanelRecarga panelrecarga;
	public static FrameAgregar frameagregar;
	public static DatagramSocket socket;
	private static String prodActual;
	
	public static final int PORT =5800;
	public static final String ADDRESSN ="localhost";
	public static void main(String[] args) throws SocketException {
		socket = new DatagramSocket();
		ventana = new InterfazMain();
		panelrecarga = PanelRecarga.getInstance();
		panelprodinf = PanelProductoInf.getInstance();
		panellistaprod = PanelListaProd.getInstance();
		escuchaMiVentana();
		escuchaBotonRecargar();
		escuchaBotonLista();
		escuchaBotonConsumir();
		escuchaBotonProdInf();
		escuchaBotonRecargarPanel();
		escuchaBotonAgregaraLista();
		escuchaBotonAgregaraListaProd();
		escuchaEscogerLista();
		
		pedirProductos();
		pedirProductoActual();
		ventana.setVisible(true);
		
	}

	private static void pedirProductoActual() {
		try {
			
			byte[] buf = new byte[1024];
			InetAddress address = InetAddress.getByName(ADDRESSN);
			//envia operacion recargar: recargar-cantidadRecarga
			String mensaje ="productoActual";
			buf = mensaje.getBytes();
			DatagramPacket paquete = new DatagramPacket(buf, buf.length, address, PORT);
			socket.send(paquete);
			byte[] buf1 = new byte[1024];
			DatagramPacket paqueteR = new DatagramPacket(buf1, buf1.length);
			socket.receive(paqueteR);
			
			String recibido = new String(paqueteR.getData());
			String[] splitsirris = recibido.split("-");
			System.out.println(recibido);
			String[] recibidoSplit = recibido.split(",");
			for (int i = 0; i < recibidoSplit.length; i++) {
			recibidoSplit[i]=recibidoSplit[i].split(";")[0];
			}
			
			prodActual = recibidoSplit[0];
			ventana.setCantidadConsDia(Double.parseDouble(recibidoSplit[1]), recibidoSplit[2]);
			ventana.setLlenadoCanasta(recibidoSplit[0], Integer.parseInt(recibidoSplit[3]));
			ventana.setUltimoLlenado(recibidoSplit[4]);
			String[] splitdelSplitsirris = splitsirris[1].split(",");
			ventana.setUltimosProd(splitdelSplitsirris[0], splitdelSplitsirris[1], splitdelSplitsirris[2]);
			
			ventana.repaint();
			ventana.getAux().repaint();
			ventana.getAux1().repaint();
			ventana.getCanasta().repaint();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void pedirProductos() {
		
		try {
		byte[] buf = new byte[1024];
		InetAddress address = InetAddress.getByName(ADDRESSN);
		//envia operacion recargar: recargar-cantidadRecarga
		String mensaje ="productos";
		buf = mensaje.getBytes();
		DatagramPacket paquete = new DatagramPacket(buf, buf.length, address, PORT);
		socket.send(paquete);
		System.out.println("ok");
		byte[] buf1 = new byte[1024];
		DatagramPacket paqueteR = new DatagramPacket(buf1, buf1.length);
		socket.receive(paqueteR);
		
		String recibido = new String(paqueteR.getData());
		System.out.println(recibido);
		String[] analisisR = recibido.split(";");
		for (int i = 0; i < analisisR.length-1; i++) {
			if(!analisisR[i].equals("")&&!analisisR[i].equals(" "))panellistaprod.getLista().addElement(analisisR[i]);
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void escuchaMiVentana() {
		ventana.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
	}


	public static void escuchaBotonRecargar() {
		ventana.getButRecargar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				panelrecarga.setVisible(true);
				panelrecarga.setLabProducto(prodActual);
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo recargar");
				}
			}


			}
		);
	}
	public static void escuchaBotonRecargarPanel() {
		panelrecarga.getButRecargar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					byte[] buf = new byte[1024];
					InetAddress address = InetAddress.getByName(ADDRESSN);
					//envia operacion recargar: recargar-cantidadRecarga
					String mensaje ="recargar-"+panelrecarga.getCantidad().getText();
					buf = mensaje.getBytes();
					System.out.println(mensaje);
					DatagramPacket paquete = new DatagramPacket(buf, buf.length, address, PORT);
					socket.send(paquete);
					byte[] buf1 = new byte[1024];
					DatagramPacket paqueteR = new DatagramPacket(buf1, buf1.length);
					socket.receive(paqueteR);
					String recibido = new String(paqueteR.getData());
					String[] descomp = recibido.split(",");
					//RECIBE nombreProducto,nuevaCantidadTotal,fechaEnAcabarse
					ventana.setCantidadConsDia(-1, descomp[2]);
					ventana.setLlenadoCanasta(descomp[0],Integer.parseInt(descomp[1]));
					Date date = new Date();
					ventana.setUltimoLlenado(date.toString());
				panelrecarga.setCantidad(new JTextField(""));
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo recargar");
				}
			}


			}
		);
	}

	public static void escuchaBotonLista() {
		ventana.getButLista().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				panellistaprod.setVisible(true);
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo cargar la ventan");
				}
			}


			}
		);
	}
	
	public static void escuchaBotonAgregaraLista() {
		
		panellistaprod.getAgregar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				FrameAgregar.getInstance().setVisible(true);
				
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo cargar la ventana");
				}
			}


			}
		);
	}
	
	public static void escuchaBotonAgregaraListaProd() {
		FrameAgregar.getInstance().getAgregar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					//Aqui se envian los productos que se agregan
				String nombre= FrameAgregar.getInstance().getTextNombre().getText();
				String volumen = FrameAgregar.getInstance().getTextVolumen().getText();
				String info = FrameAgregar.getInstance().getTextInfo().getText();
				
				byte[] buf = new byte[1024];
				InetAddress address = InetAddress.getByName(ADDRESSN);
				String mensaje ="Agregar-"+nombre+","+volumen+","+info;
				buf = mensaje.getBytes();
				DatagramPacket paquete = new DatagramPacket(buf, buf.length, address, PORT);
				socket.send(paquete);
				FrameAgregar.getInstance().getTextNombre().setText("");
				FrameAgregar.getInstance().getTextVolumen().setText("");
				FrameAgregar.getInstance().getTextInfo().setText("");
				FrameAgregar.getInstance().setVisible(false);
				panellistaprod.getLista().addElement(nombre);
				//formato del envio: Agregar-nombre,volumen,fechaInicio,info
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo recargar");
				}
			}


			}
		);
	}
	
	public static void escuchaEscogerLista() {
		panellistaprod.getElegir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				byte[] buf = new byte[1024];
				InetAddress address = InetAddress.getByName(ADDRESSN);
				String mensaje ="actualizarActual-";
				Date date = new Date();
				String datetext =date.toString();
				
				mensaje+= panellistaprod.getProd().getSelectedValue()+","+datetext;
				buf = mensaje.getBytes();
				DatagramPacket paquete = new DatagramPacket(buf, buf.length, address, PORT);
				socket.send(paquete);
				
				ventana.setLlenadoCanasta(panellistaprod.getProd().getSelectedValue(),0);
				panelrecarga.getLabProducto().setText(panellistaprod.getProd().getSelectedValue());
				prodActual = panellistaprod.getProd().getSelectedValue();
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo escoger");
				}
			}


			}
		);
	}
	public static void escuchaBotonProdInf() {
		ventana.getButProdInfo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				panelprodinf.setVisible(true);
				byte[] buf = new byte[1024];
				InetAddress address = InetAddress.getByName(ADDRESSN);
				String mensaje ="Informacion-"+prodActual;
				if(prodActual!=null||!prodActual.equals("")) {
					
					buf = mensaje.getBytes();
					DatagramPacket paquete = new DatagramPacket(buf, buf.length, address, PORT);
					socket.send(paquete);
					byte[] bufR = new byte[1024];
					DatagramPacket paqueteR = new DatagramPacket(bufR, bufR.length);
					socket.receive(paqueteR);
					
					String recibido = new String(paqueteR.getData());
					String[] recibidoR = recibido.split(",");
					//recibe: nombre,volumen,info
					
					panelprodinf.setLabNombre(recibidoR[0]);
					panelprodinf.setLabVolum(recibidoR[1]+" cm cubicos");
					panelprodinf.getInfoExtra().setText(recibidoR[2]);
				}
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo mostrar la info del producto");
				}
			}


			}
		);
	}

	public static void escuchaBotonConsumir() {
		ventana.getButConsumir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					byte[] buf = new byte[1024];
					InetAddress address = InetAddress.getByName(ADDRESSN);
					String mensaje ="consumir-";
					String cantidad =JOptionPane.showInputDialog("¿Cuantas desea consumir?");
					mensaje+=cantidad;
					buf = mensaje.getBytes();
					DatagramPacket paquete = new DatagramPacket(buf, buf.length, address, PORT);
					socket.send(paquete);
					byte[] buf1 = new byte[1024];
					DatagramPacket paqueteR = new DatagramPacket(buf1, buf1.length);
					socket.receive(paqueteR);
					String recibido = new String(paqueteR.getData());
					String[] descomp = recibido.split(",");
					ventana.setCantidadConsDia(Double.parseDouble(descomp[0]), descomp[1]);
					ventana.setLlenadoCanasta(prodActual,Integer.parseInt(descomp[2]));
				}catch(Exception e) {
				JOptionPane.showMessageDialog(ventana, "No se pudo consumir");
				}
			}
			}
		);
	}
	
	


}

