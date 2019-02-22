package modelo;

import java.sql.SQLException;
import java.util.Date;

import control.ConexionMySQL;
import control.ServerSingleton;
import control.TCPConection;

public class Operaciones  implements TCPConection.MessageObserver {

	public final static int NARANJA = 10;
	public final static int HUEVO = 7;
	

	public Operaciones() {
		try {
			ServerSingleton.getInstance().setObserver(this);
			ServerSingleton.getInstance().start();
			//Realiza el intento de conexión con la base de datos
			
		} catch (Exception e) {
			System.out.println("No se pudo establecer conexion con la base de datos");
		}
	}
	
	public String recargar(String datagrama) throws ClassNotFoundException {
		System.out.println(datagrama);
		String mensaje = "";
		String nombreActual = "";
		String[] split = datagrama.split("-");
		
		int diaTerm = 0;
		int cantidadCanasta = 0;
		int cantidadTotal = 0;
		Date date = new Date();
		int totalCons = 0;
		try {
			if(!ConexionMySQL.getInstance().intermedio("SELECT SUM(cantidadRec) FROM Recargas_DavidBolivar_JohnUrbano AS r, ProductosCanasta_DavidBolivar_JohnUrbano AS p WHERE p.ocurrencia=r.ocurrencia AND p.fechaAcabado IS NULL").split(";")[0].equals("null"))
			cantidadTotal = Integer.parseInt(ConexionMySQL.getInstance().intermedio("SELECT SUM(cantidadRec) FROM Recargas_DavidBolivar_JohnUrbano AS r, ProductosCanasta_DavidBolivar_JohnUrbano AS p WHERE p.ocurrencia=r.ocurrencia AND p.fechaAcabado IS NULL").split(";")[0]);;
			totalCons = Integer.parseInt(ConexionMySQL.getInstance().intermedio("SELECT cantidadConsum FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL").split(";")[0]);
			
			cantidadCanasta = cantidadTotal-totalCons+Integer.parseInt(split[1].trim());
			String ocurrencia = ConexionMySQL.getInstance().intermedio("SELECT ocurrencia FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL").split(";")[0];
			ConexionMySQL.getInstance().intermedio("INSERT INTO `Recargas_DavidBolivar_JohnUrbano`(`ocurrencia`, `fechaRecarga`, `cantidadRec`) VALUES ("+ocurrencia+",'"+date.toString()+"',"+split[1].trim()+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			nombreActual = ConexionMySQL.getInstance().intermedio("SELECT nombre FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL");
			
			String davidJodeMucho = ConexionMySQL.getInstance().intermedio("SELECT fechaInic FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE `fechaAcabado` IS NULL");
			
			String[] hola = davidJodeMucho.split(" ");
			
			date.toString();
			
			int actual= calcularMes(hola[1]);
			int mesinicio = calcularMes(date.toString().split(" ")[1]);
			double promedior = 0;
			if(totalCons!=0) {
				
				if(actual>mesinicio) {
					promedior = (30*((actual-mesinicio)+12*(Integer.parseInt(date.toString().split(" ")[5])-Integer.parseInt(hola[5].split(";")[0])))-Integer.parseInt(date.toString().split(" ")[2]))/totalCons;
				}else {
					promedior = (30*(((12-mesinicio)+12*(Integer.parseInt(date.toString().split(" ")[5])-Integer.parseInt(hola[5].split(";")[0])))+actual)-Integer.parseInt(date.toString().split(" ")[2]))/totalCons;
				}
			}
			
			
			if(promedior!=0) {
				diaTerm= (int) ((cantidadTotal-totalCons)/promedior);
			}
			
			
		} catch (Exception e) {
			System.out.println("No se pudo obtener el nombre del producto actual");
		}
		
			mensaje = nombreActual+","+cantidadCanasta+","+ diaTerm;
		
		
		
		
		return mensaje;
	}
	public String informacion(String datagrama) throws ClassNotFoundException {
	
		String mensaje = "";
		
		String[] split = datagrama.split("-");
		
		try {
			mensaje = ConexionMySQL.getInstance().intermedio("SELECT * FROM Productos_DavidBolivar_JohnUrbano WHERE nombre = '" + split[1].trim()+"'");
		} catch (SQLException e) {
			System.out.println("no se pudo obtener la información");
		}
		
		
		return mensaje;
	}
	public String consumir(String datagrama) throws ClassNotFoundException {
		
		String mensaje = "";
		String nombreActual = "";
		String[] split = datagrama.split("-");
		try {
			nombreActual = ConexionMySQL.getInstance().intermedio("SELECT nombre FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL");
			
		} catch (Exception e) {
			System.out.println("No se pudo obtener el nombre del producto actual");
		}
		double promedior = 0;
		int diaTerm = 0;

		int cantidadCanasta = 0;
		int cantidadTotal = 0;
			Date date = new Date();
			try {
				
				
				int cantidadConsumVieja = Integer.parseInt(ConexionMySQL.getInstance().intermedio("SELECT cantidadConsum FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL").split(";")[0]);
				
				int cantidadNueva = cantidadConsumVieja+Integer.parseInt(split[1].trim());
				ConexionMySQL.getInstance().intermedio("UPDATE `ProductosCanasta_DavidBolivar_JohnUrbano` SET `cantidadConsum`="+cantidadNueva+" WHERE fechaAcabado IS NULL");
				nombreActual = ConexionMySQL.getInstance().intermedio("SELECT nombre FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL");
				
				String davidJodeMucho = ConexionMySQL.getInstance().intermedio("SELECT fechaInic FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE `fechaAcabado` IS NULL");
				
				String[] hola = davidJodeMucho.split(" ");
				
				date.toString();
				if(!ConexionMySQL.getInstance().intermedio("SELECT SUM(cantidadRec) FROM Recargas_DavidBolivar_JohnUrbano AS r, ProductosCanasta_DavidBolivar_JohnUrbano AS p WHERE p.ocurrencia=r.ocurrencia AND p.fechaAcabado IS NULL").split(";")[0].equals("null"))
					cantidadTotal	= Integer.parseInt(ConexionMySQL.getInstance().intermedio("SELECT SUM(cantidadRec) FROM Recargas_DavidBolivar_JohnUrbano AS r, ProductosCanasta_DavidBolivar_JohnUrbano AS p WHERE p.ocurrencia=r.ocurrencia AND p.fechaAcabado IS NULL").split(";")[0]);
					;
					int totalCons = Integer.parseInt(ConexionMySQL.getInstance().intermedio("SELECT cantidadConsum FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL").split(";")[0]);
				
				int actual= calcularMes(hola[1]);
				int mesinicio = calcularMes(date.toString().split(" ")[1]);
				
				if(totalCons!=0) {
					
					if(actual>mesinicio) {
						promedior = (30*((actual-mesinicio)+12*(Integer.parseInt(date.toString().split(" ")[5])-Integer.parseInt(hola[5].split(";")[0])))-Integer.parseInt(date.toString().split(" ")[2]))/totalCons;
					}else {
						promedior = (30*(((12-mesinicio)+12*(Integer.parseInt(date.toString().split(" ")[5])-Integer.parseInt(hola[5].split(";")[0])))+actual)-Integer.parseInt(date.toString().split(" ")[2]))/totalCons;
					}
				}
				cantidadCanasta = cantidadTotal-totalCons-Integer.parseInt(split[1].trim());
				
				if(promedior!=0) {
					diaTerm= (int) ((cantidadTotal-totalCons)/promedior);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("No se pudo obtener el volumen del producto actual");
			}
			
			mensaje = promedior+","+ diaTerm+","+cantidadCanasta;
		
		
		
		return mensaje;
	}
	public void actualizar(String datagrama) throws SQLException, ClassNotFoundException {
		
		
		String[] split = datagrama.split("-");
		String[] splitDelSplit = split[1].split(",");
		String nombreActual = "";
		try {
			nombreActual = ConexionMySQL.getInstance().intermedio("SELECT nombre FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL");
			
		} catch (Exception e) {
			System.out.println("No se pudo obtener el nombre del producto actual");
		}
			// Se verifica primero si el producto actual se encuentra ya que si lo está tendria sentido solo recargarlo
		if(!splitDelSplit[0].equals(nombreActual)) {
			
			ConexionMySQL.getInstance().intermedio("UPDATE ProductosCanasta_DavidBolivar_JohnUrbano SET fechaAcabado = '"+ splitDelSplit[1].trim()+"' WHERE fechaAcabado IS NULL");
			
			ConexionMySQL.getInstance().intermedio("INSERT INTO ProductosCanasta_DavidBolivar_JohnUrbano (nombre, fechaInic, cantidadConsum) VALUES ('"+splitDelSplit[0].trim()+"', '"+splitDelSplit[1].trim()+"', 0)");
		
		}else {
			System.out.println("Intentaron cambiar un producto que ya estaba");
		}
		
	}
	public String productos (String datagrama) throws ClassNotFoundException, SQLException {

		return ConexionMySQL.getInstance().intermedio("SELECT nombre FROM Productos_DavidBolivar_JohnUrbano");
		
		
	}
	
	public String agregar(String datagrama) {
		
		String mensaje = "";
		String[] split = datagrama.split("-");
		String[] splitDelSplit = split[1].split(",");
		
		try {
			
			System.out.println("ok");
			String dos = splitDelSplit[2].trim();
			mensaje = "INSERT INTO Productos_DavidBolivar_JohnUrbano (nombre, volumen, informacion) VALUES "
					+ "('"+splitDelSplit[0]+"', "+splitDelSplit[1]+", '"+dos+"')";
			//", '"+splitDelSplit[2]+
			
			ConexionMySQL.getInstance().intermedio(mensaje);
			System.out.println("Se insertó");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se logró insertar el producto");
		}
	
		
		return mensaje;
	}
	
	public String InfoActual(String datagrama) throws ClassNotFoundException, SQLException {
		
		String gatoAmarillo = "";
		int cantidadTotal = 0;
		gatoAmarillo = ConexionMySQL.getInstance().intermedio("SELECT nombre FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL");
		String davidJodeMucho = ConexionMySQL.getInstance().intermedio("SELECT fechaInic FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE `fechaAcabado` IS NULL");
		
		String[] hola = davidJodeMucho.split(" ");
		Date date = new Date(); 
		date.toString();
		if(!ConexionMySQL.getInstance().intermedio("SELECT SUM(cantidadRec) FROM Recargas_DavidBolivar_JohnUrbano AS r, ProductosCanasta_DavidBolivar_JohnUrbano AS p WHERE p.ocurrencia=r.ocurrencia AND p.fechaAcabado IS NULL").split(";")[0].equals("null"))
		cantidadTotal	= Integer.parseInt(ConexionMySQL.getInstance().intermedio("SELECT SUM(cantidadRec) FROM Recargas_DavidBolivar_JohnUrbano AS r, ProductosCanasta_DavidBolivar_JohnUrbano AS p WHERE p.ocurrencia=r.ocurrencia AND p.fechaAcabado IS NULL").split(";")[0]);
		;
		 int totalCons = Integer.parseInt(ConexionMySQL.getInstance().intermedio("SELECT cantidadConsum FROM ProductosCanasta_DavidBolivar_JohnUrbano WHERE fechaAcabado IS NULL").split(";")[0]);
		
		int actual= calcularMes(hola[1]);
		int mesinicio = calcularMes(date.toString().split(" ")[1]);
		double promedior = 0;
		if(totalCons!=0) {
			
			if(actual>mesinicio) {
				promedior = (30*((actual-mesinicio)+12*(Integer.parseInt(date.toString().split(" ")[5])-Integer.parseInt(hola[5].split(";")[0])))-Integer.parseInt(date.toString().split(" ")[2]))/totalCons;
			}else {
				promedior = (30*(((12-mesinicio)+12*(Integer.parseInt(date.toString().split(" ")[5])-Integer.parseInt(hola[5].split(";")[0])))+actual)-Integer.parseInt(date.toString().split(" ")[2]))/totalCons;
			}
		}
		
		gatoAmarillo+=","+promedior;
		int diaTerm = 0;
		if(promedior!=0) {
			diaTerm= (int) ((cantidadTotal-totalCons)/promedior);
		}
		
		gatoAmarillo+=","+diaTerm;
		int cuantosHay = cantidadTotal-totalCons;
		gatoAmarillo+=","+cuantosHay;
		
		String ultimaVezLLen = ConexionMySQL.getInstance().intermedio("SELECT fechaRecarga FROM Recargas_DavidBolivar_JohnUrbano ORDER BY Id_Recarga DESC LIMIT 1;");
		gatoAmarillo+=","+ultimaVezLLen;
		
		String ultimosTres = "";
		String ultimos = ConexionMySQL.getInstance().intermedio("SELECT nombre FROM ProductosCanasta_DavidBolivar_JohnUrbano ORDER BY ocurrencia DESC");
		String[] nada = ultimos.split(";");
		
		for (int i = 0; i < 3; i++) {
		
			if(i<2) {
				ultimosTres+=nada[i]+",";	
			}else {
				ultimosTres+=nada[i];
			}
			
		
		}
		gatoAmarillo+="-"+ultimosTres;
		return gatoAmarillo;
	}

	public int calcularMes(String mes) {
	int promedio = 0;

	switch (mes) {
	case "JAN":
		promedio = 1;
		break;
	case "FEB":
		
		promedio = 2;
		break;
	case "MAR":
		promedio = 3;
		break;
	case "APR":
		promedio = 4;
		break;
	case "MAY":
		promedio = 5;
		break;
	case "JUN":
		promedio = 6;
		break;
	case "JUL":
		promedio = 7;
		break;
	case "AUG":
		promedio = 8;
		break;
	case "SEP":
		promedio = 9;
		break;
	case "OCT":
		promedio = 10;
		break;
	case "NOV":
		promedio = 11;
		break;
	case "DEC":
		promedio = 12;
		break;

	default:
		break;
	}
		 return promedio;
	}
	
	@Override
	public void onDataReceiver(String mensaje) {
		
			try {
				if(mensaje.contains("actualizarActual-")) {
					actualizar(mensaje);
				}else if(mensaje.contains("Agregar-")) agregar(mensaje);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}
}

