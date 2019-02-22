package control;


import modelo.Operaciones;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import control.ConexionMySQL;
public class MainCanasta{
	static Operaciones mundo;
	public static void main(String[] args) {
		
		
				mundo = new Operaciones();
				
				
				//Recibe primero
				try {
					//El DS lleva el puerto ya que somos Servidor
					DatagramSocket socket = new DatagramSocket(5800);
					
					
					
					String traido, respuesta;
					
					while (true) {
						byte[] buffer= new byte[10048];
						//Solicitud lleva dos parametros porque comenzamos recibiendo
						DatagramPacket solicitud = new DatagramPacket(buffer, buffer.length);
						
						socket.receive(solicitud);
						traido = new String(solicitud.getData());
						respuesta = devolver(traido);
						if(!respuesta.equals("") && respuesta !=null) {
							//Preparación del paquete de envio
							DatagramPacket envio = new DatagramPacket(respuesta.getBytes(), respuesta.getBytes().length, solicitud.getAddress(), solicitud.getPort());
							
							//Envio de la respuesta de la solicitud
							socket.send(envio);
						}
						
						
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
	}
	
	public static String devolver(String mensaje) throws SQLException, ClassNotFoundException {
		
		String retorno = "";
		//Devuelve informacion
		if (mensaje.contains("recargar-")) retorno = mundo.recargar(mensaje);
		
		//Devuelve informacion
		else if (mensaje.contains("Informacion-")) 	retorno =	mundo.informacion(mensaje);
		
		//Devuelven informacion
		else if(mensaje.contains("consumir-")) retorno =	mundo.consumir(mensaje);
		
		//No devuelve
		else if(mensaje.contains("actualizarActual-")) mundo.actualizar(mensaje);
		
		//Devuelven informacion
		else if(mensaje.contains("Agregar-")) mundo.agregar(mensaje);
		else if(mensaje.contains("productos")) retorno =	mundo.productos(mensaje);
		else if(mensaje.contains("productoActual")) retorno =	mundo.InfoActual(mensaje);
		return retorno;
		
	}

	

}
