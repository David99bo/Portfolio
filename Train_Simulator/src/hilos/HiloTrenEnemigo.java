package hilos;


import interfaz.InterfazJuego;
import mundo.Tren;

public class HiloTrenEnemigo extends Thread{
	
	private Tren enemigo;
	private InterfazJuego ventana;
	private int cont;
	private int llegada;
	public HiloTrenEnemigo(InterfazJuego ventana, Tren enemigo){
		this.enemigo=enemigo;
		this.ventana=ventana;
		cont = 0;
		System.out.println(enemigo.getCamino().toString());
		enemigo.setLlegada(ventana.getMundo().getVertices().get(enemigo.getCamino().get(1)));
	}
	
	@Override
	public void run() {
		super.run();
		while(!enemigo.isDetenido()){
			ventana.actualizar();
			if(enemigo.getVelocidad()>0){
				
			if(!enemigo.isOnVertex()){
			
				enemigo.movimiento();
					//System.out.println(tren.getPosX()+" "+tren.getPosY());
					try {
						Thread.sleep(100-(20*enemigo.getVelocidad()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				ventana.actualizar();
				
			}
			else{
				
				if(!enemigo.getActual().getCodigo().equals(enemigo.getMeta().getCodigo())){
					System.out.println("k");
				enemigo.setSalida(ventana.getMundo().getVertices().get(enemigo.getCamino().get(cont+1)));
				enemigo.setLlegada(ventana.getMundo().getVertices().get(enemigo.getCamino().get(cont+2)));
				cont++;
				enemigo.movimiento();
				
				//System.out.println(tren.getPosX()+" "+tren.getPosY());
				try {
					Thread.sleep(100-(20*enemigo.getVelocidad()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			ventana.actualizar();
				}else {
					enemigo.setDetenido(true);
					enemigo.setPosX(30000);enemigo.setPosY(30000);
					ventana.actualizar();
				}
			}
		}
		}
	}

}
