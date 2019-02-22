package control;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPConection {	
	private Socket socket;
	
	private Receptor receptor;
	
	private MessageObserver observer;
	
	private String nombre=""; 
	
	public TCPConection(Socket socket){
		this.socket = socket;
		this.receptor = new Receptor(socket);
	}
	
	public void send(String mensaje){
		try {
			System.out.println("Enviando mensaje...");
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			out.println(mensaje);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public interface MessageObserver{
		void onDataReceiver(String mensaje);
	}
		
	public void setListener(MessageObserver observer){
		this.observer = observer;
	}
	
	public void activarRecepcion(){
		receptor.start();
	}
	
	private class Receptor extends Thread{
		Socket socket;
		
		public Receptor(Socket socket){
			this.socket = socket;
		}
		
		@Override
		public void run(){
			try {
				BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
				InputStreamReader reader = new InputStreamReader(bis);
				BufferedReader buffReader = new BufferedReader(reader);
				
				while(true){
					String line = buffReader.readLine();
					
					if(line == null){
						observer.onDataReceiver("Conexión finalizada");
						return;
					}
					observer.onDataReceiver(line);
					
				}				
			} catch (IOException e) {
				System.out.println("Cliente desconectado");
			}
		}
	}

}
