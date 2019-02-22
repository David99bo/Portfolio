package mundo;

import java.io.*;

import estructuras.AVLTree;
import estructuras.RedBlackTree;
import estructuras.RedBlackTreeNode;

public class BaseDeDatos {
	
	private RedBlackTree<String,Integer> arbolUno;
	
	private AVLTree<String,Integer> arbolDos;
	
	private AVLTree<String,Integer> arbolTres;
	
	private int numeroRegistros;
	
	public BaseDeDatos (){
		arbolUno = new RedBlackTree<>();
		arbolDos = new AVLTree<>();
		arbolTres = new AVLTree<>();
		numeroRegistros=0;
	}
	
	public String[] cargarDatos(File archivo){
		String[] campos=null;
		File dirDatos = new File("./data/datos");
		for(File file : dirDatos.listFiles()){
			if(!file.isDirectory()){
				file.delete();
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			BufferedWriter bw = null;
			campos=br.readLine().split(",");
			String line=br.readLine();
			int i=1;
			while(line!=null && !line.isEmpty()){
				String [] datos = line.split(",");
				numeroRegistros++;
				bw=new BufferedWriter(new FileWriter("./data/datos/R"+numeroRegistros+".txt"));
				bw.write(line+"\n");
				arbolUno.insert(datos[1], i);
				arbolDos.insert(datos[2], i);
				arbolTres.insert(datos[3], i);
				i++;
				line=br.readLine();
				bw.close();
			}
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();;
		}
		return campos;
	}
	
	public int buscarPorCriterio(String valor,String criterio){
		
		int actual=0;
		if(criterio.equals("Nombre")){
			actual=arbolDos.search(valor);
		}
		else if(criterio.equals("NitsNit")){
			actual=arbolTres.search(valor);
		}
		else if(criterio.equals("Codigo habilitacion")){
			RedBlackTreeNode<String, Integer>hola=arbolUno.search(valor);
			if(hola!=null){
				actual=hola.getDato();
			}
		}
		return actual;
		
	}
	
	public void agregar(String[] actual){
		try {
			BufferedWriter bw = null;
			numeroRegistros++;
			bw=new BufferedWriter(new FileWriter("./data/datos/R"+numeroRegistros+".txt"));
			bw.write(actual[0]+","+actual[1]+","+actual[2]+","+actual[3]+","+actual[4]+","+actual[5]+","+"\n");
			arbolUno.insert(actual[1], numeroRegistros);
			arbolDos.insert(actual[2], numeroRegistros);
			arbolTres.insert(actual[3], numeroRegistros);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumeroRegistros(){
		return numeroRegistros;
	}

}
