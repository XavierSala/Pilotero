package net.xaviersala.pilotero;

import acm.graphics.GLabel;
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
  public Camp(GRectangle pantalla, Pala pala1, Pala pala2, Pilota pilota, GLabel etiqueta) {
    pales[0] = pala1;
    pales[1] = pala2;
    this.pilota = pilota;
    midaCamp = pantalla;
    marcador = new Marcador(etiqueta, (int) midaCamp.getWidth());


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
    System.out.println(pilota);
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

    if (surtAForaDeLaPantalla(posicioPilota)) {
      pilota.canviaDireccioVertical();
      double novaPosicio = tornaADinsY(posicioPilota);
      pilota.setY(novaPosicio);
    }
    if (surtPerLaPorteria(posicioPilota)) {
      pilota.canviaDireccioHoritzontal();



      // És gol!
      if (pilota.getEspaiQueOcupa().getX() <= midaCamp.getX()) {
        marcador.marca(1);
      } else {
        marcador.marca(0);
      }

      double novaPosicioX = tornaADinsX(posicioPilota);
      pilota.setX(novaPosicioX);

    }
  }

  /**
   * Mou les pales del joc.
   * @param posicioPilota posició de la pilota
   */
  private void mourePales(GRectangle posicioPilota) {
    for (int i=0; i< pales.length; i++) {

      // Perquè no s'ha de moure la controlada per l'usuari
      if (i > 0) {
        pales[i].calculaMoviment(pilota);
      }
      pales[i].mou();
      // Comprovo que la pala no se'n vagi de la pantalla
      if (surtAForaDeLaPantalla(pales[i].getEspaiQueOcupa())) {
        pales[i].setDireccio(0);
        pales[i].setY(tornaADinsY(pales[i].getEspaiQueOcupa()));
      }
      if (pales[i].xocaAmb(pilota.getEspaiQueOcupa())) {
        pales[i].rebotaPilota(pilota);
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
  private boolean surtPerLaPorteria(GRectangle posicioPilota) {
    return posicioPilota.getX() < midaCamp.getX() ||
      posicioPilota.getX() + posicioPilota.getWidth() > midaCamp.getX() + midaCamp.getWidth();
  }

  /**
   * Comprova si un objecte surt per les bandes.
   *
   * @param posicio Coordenades de la pilota
   * @return si ha xocat o no
   */
  private boolean surtAForaDeLaPantalla(GRectangle posicio) {
    return posicio.getY() < midaCamp.getY() ||
        posicio.getY() + posicio.getHeight() > midaCamp.getY() + midaCamp.getHeight();
  }

  /**
   * L'objecte ha sortit per les bandes de manera que s'ha de tornar a
   * posar a dins.
   *
   * @param posicio posició de l'objecte
   */
  private double tornaADinsY(GRectangle posicio) {
    double resultat = 0;
    if (posicio.getY() < midaCamp.getY()) {
      resultat = midaCamp.getY();
    } else {
      resultat =  midaCamp.getY() + midaCamp.getHeight() - posicio.getHeight();
    }
    return resultat;
  }

  /**
   * Ha sortit per les zones de gol i es torna al camp.
   *
   * @param posicioPilota posició de la pilota
   */
  private double tornaADinsX(GRectangle posicioPilota) {
    if (posicioPilota.getX() < midaCamp.getX()) {
      return midaCamp.getX();
    }
    return midaCamp.getX() + midaCamp.getWidth() - posicioPilota.getWidth();
  }

  public void mouPala(int i) {
    pales[0].setDireccio(i);
  }


}
