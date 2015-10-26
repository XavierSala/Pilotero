package net.xaviersala.pilotero;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Pala {
	private static final int VELOCITATPALA = 10;
	GImage imatge;
	int moviment;
	int velocitat = VELOCITATPALA;
	
	/**
	 * Crea un objete Pala a partir d'una imatge.
	 * 
	 * Inicialment està aturat.
	 * 
	 * @param imatge
	 */
	public Pala(GImage imatge) {
		this.imatge = imatge;
		moviment = 0;
	}
	
	/**
	 * Posiciona l'objecte en les coordenades especificades
	 * @param d coordenada X
	 * @param e coordenada Y
	 */
	public void setPosicio(double d, double e) {
		imatge.setLocation(d, e);
	}
	
	/**
	 * @return Retorna l'espai físic que ocupa l'objecte.
	 */
	public GRectangle getEspaiQueOcupa() {
		return imatge.getBounds();
	}
	
	/**
	 * Diu si la pala ha xocat amb l'objecte que se li
	 * passi amb el paràmetre.
	 * @param pilota rectangle de l'objecte a comprovar
	 * @return si ha xocat o no
	 */
	public boolean xocaAmb(GRectangle pilota) {
		return imatge.getBounds().intersects(pilota);
	}
	
	/**
	 * Mou a pala cap a dalt o cap a baix
	 * 
	 * El moviment bé determinat per la variable `moviment`
	 */
	public void mou() {
		imatge.move(0,VELOCITATPALA * moviment);
	}
	
	/**
	 * Funció que mou la pala cap a on és la pilota. 
	 * 
	 * Es basa en comprovar els punts mitjos dels dos objectes per veure
	 * cap a on s'ha de moure.
	 * @param pilota
	 */
	public void calculaMoviment(GRectangle pilota) {
		int direccio = (int) ((pilota.getY() + pilota.getHeight()/2) - (imatge.getY() + imatge.getHeight()/2) );
		if (direccio != 0) {
			moviment = (int) (direccio/Math.abs(direccio));
		} else {
			moviment = 0;
		}
	}
	
	public void gira() {
		moviment *= moviment;
	}
}
