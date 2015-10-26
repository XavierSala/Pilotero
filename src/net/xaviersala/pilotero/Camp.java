package net.xaviersala.pilotero;

import acm.graphics.GRectangle;

public class Camp {
	Pala[] pales = new Pala[2];
	Pilota pilota;
	Marcador marcador;
	App pantalla;
	GRectangle midaCamp;
	
	/**
	 * Afegeix els elements de la partida en el camp.
	 * 
	 * @param pantalla rectangle que ocupa el camp
	 * @param pala1 pala del costat esquerra
	 * @param pala2 pala del costat dret
	 * @param pilota pilota
	 */
	public Camp(GRectangle pantalla, Pala pala1, Pala pala2, Pilota pilota) {
		pales[0] = pala1;
		pales[1] = pala2;
		this.pilota = pilota;
		marcador = new Marcador();
		midaCamp = pantalla;
		
	}
	
	/**
	 * Posiciona pilota i pales en la pantalla.
	 */
	public void comencarPartida() {
				
		pales[0].setPosicio(pales[0].getEspaiQueOcupa().getWidth(), midaCamp.getHeight()/2);
		pales[1].setPosicio(midaCamp.getWidth() - pales[1].getEspaiQueOcupa().getWidth() - 10, midaCamp.getHeight()/2);
		
		pilota.setPosicio(100, 100);
		pilota.setAngle(35);
		
	}
	
	/**
	 * Fa que el joc funcioni sol.
	 */
	public void juga() {
		
		while(true) {
			mou();			
		}
		
	}
	
	/**
	 * Fa un moviment bàsic del joc.
	 */
	public void mou() {
		GRectangle posicioPilota = pilota.getEspaiQueOcupa();
				
		mourePilota(posicioPilota);		
		mourePales(posicioPilota);					
	}

	/**
	 * Mou la pilota. 
	 * 
	 * Ha de comprovar que la pilota no surt de pantalla
	 * 
	 * @param posicioPilota posició de la pilota
	 */
	private void mourePilota(GRectangle posicioPilota) {
		pilota.mou();
		
		if (xocaLaPilotaAFora(posicioPilota)) {
			pilota.canviaDireccioVertical();
			tornaLaPilotaADinsY(posicioPilota);
		}
		if (xocaLaPilotaAPorteria(posicioPilota)) {
			pilota.canviaDireccioHoritzontal();
			tornaLaPilotaADinsX(posicioPilota);
				
		}
	}

	/**
	 * Mou les pales del joc.
	 * @param posicioPilota posició de la pilota
	 */
	private void mourePales(GRectangle posicioPilota) {
		for (int i=0; i< pales.length; i++) {
			
			pales[i].calculaMoviment(posicioPilota);
			pales[i].mou();
			if (pales[i].xocaAmb(pilota.getEspaiQueOcupa())) {
				pilota.canviaDireccioHoritzontal();			
			}
			
		}
	}

	
	/** 
	 * Comprova si la pilita ha xocat amb una de les dues porteries
	 * o no.
	 * 
	 * @param posicioPilota Rectangle que conté la pilota
	 * @return Si ha xocat o no.
	 */
	private boolean xocaLaPilotaAPorteria(GRectangle posicioPilota) {
		return posicioPilota.getX() < midaCamp.getX() || 
			posicioPilota.getX() + posicioPilota.getWidth() > midaCamp.getX() + midaCamp.getWidth();
	}

	/**
	 * Comprova si la pilota ha xocat en les bandes.
	 * 
	 * @param posicioPilota Coordenades de la pilota
	 * @return si ha xocat o no
	 */
	private boolean xocaLaPilotaAFora(GRectangle posicioPilota) {
		return posicioPilota.getY() < midaCamp.getY() || 
				posicioPilota.getY() + posicioPilota.getHeight() > midaCamp.getY() + midaCamp.getHeight();
	}

	/**
	 * La pilota ha sortit per les bandes de manera que s'ha de tornar a 
	 * posar a dins.
	 * 
	 * @param posicioPilota posició de la pilota
	 */
	private void tornaLaPilotaADinsY(GRectangle posicioPilota) {
		if (posicioPilota.getY() < midaCamp.getY()) {
			pilota.setY(midaCamp.getY());
		} else {
			pilota.setY(midaCamp.getY() + midaCamp.getHeight() - posicioPilota.getHeight());
		}
	}

	/**
	 * La pilota ha sortit per les zones de gol i es torna al camp.
	 * 
	 * @param posicioPilota posició de la pilota
	 */
	private void tornaLaPilotaADinsX(GRectangle posicioPilota) {
		if (posicioPilota.getX() < midaCamp.getX()) {
			pilota.setX(midaCamp.getX());
		} else {
			pilota.setX(midaCamp.getX() + midaCamp.getWidth() - posicioPilota.getWidth());
		}
	}


}
