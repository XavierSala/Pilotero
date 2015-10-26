package net.xaviersala.pilotero;

import acm.graphics.GImage;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class App extends GraphicsProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 449430650729152965L;

	public void run() {
		setSize(800, 600);
		pause(109);
		
		// Crear els objectes del joc		
		GRectangle mida = new GRectangle(getWidth(), getHeight());
		
		GImage pilota = new GImage("pilota.png");
		add(pilota);
		GImage pala1 = new GImage("pala.png");
		add(pala1);
		GImage pala2 = new GImage("pala.png");
		add(pala2);
		
		// Passa els objectes al camp
		Camp camp = new Camp(mida, 
				new Pala(pala1), 
				new Pala(pala2), 
				new Pilota(pilota));
		
		
		// Començar la partida
		camp.comencarPartida();
		
		// Va fent infinitament.
		while (true) {
			camp.mou();
			pause(80);
		}
		
	}

}
