package control;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSingleton extends Thread{

	private static ServerSingleton instance;

	private ServerSocket server;
	private ArrayList<TCPConection> conexiones;
	private TCPConection.MessageObserver observer;
	
	public static ServerSingleton getInstance(){
		if(instance == null){
			instance = new ServerSingleton();
		}
		return instance;
	}
	
	public ServerSingleton() {
		try {
			conexiones = new ArrayList<>();
			server = new ServerSocket(5000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		try {
			while(true){
				System.out.println("Esperando conexion");
				Socket socket = server.accept();
				System.out.println("Conexion aceptada");
				TCPConection conection = new TCPConection(socket);
				conection.setListener(observer);
				conection.activarRecepcion();
				conexiones.add(conection);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setObserver(TCPConection.MessageObserver observer){
		this.observer = observer;
	}
}
