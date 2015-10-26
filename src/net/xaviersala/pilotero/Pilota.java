package net.xaviersala.pilotero;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Pilota {
  private static final int VELOCITATPILOTA = 18;
  GImage imatge;
  int angle;
  int direccio;


  /**
   * Crea un objecte Pilota que té una imatge, un angle de
   * moviment i per un futur una velocitat.
   *
   * @param image Imatge a carregar
   */
  public Pilota(GImage image) {
    imatge = image;
    angle = 0;
    direccio = 1;
  }

  /**
   * @return retorna l'angle en el que es mou l'objecte
   */
  public int getAngle() {
    return angle;

  }

  /**
   * Defineix en quin angle s'ha de moure l'objecte.
   * @param angle angle nou
   */
  public void setAngle(int angle) {
    this.angle = angle;
  }

  /**
   * Posiciona la pilota en les coordenades especificades.
   * @param x coordenada X
   * @param y coordenada Y
   */
  public void setPosicio(int x, int y) {
    imatge.setLocation(x, y);
  }

  /**
   * Mou la pilota en la direcció que tingui definida en
   * l'angle i a la velocitat definida a velocitat.
   */
  public void mou() {
    imatge.movePolar(direccio * VELOCITATPILOTA, angle);
  }

  /**
   * @return retorna la direcció
   */
  public int getDireccio() {
    return direccio;
  }

  /**
   * La pilota ha xocat amb algun objecte verticalment i per tant ha de
   * canviar l'angle.
   */
  public void canviaDireccioVertical() {
    System.out.println("Xoc");
    angle = (360 - angle) % 365;
  }

  /**
   * @return retorna l'espai que està ocupant l'objecte
   */
  public GRectangle getEspaiQueOcupa() {
    return imatge.getBounds();
  }

  /**
   * La pilota ha xocat amb un objecte horitzontal i per tant ha de canviar d'angle.
   */
  public void canviaDireccioHoritzontal() {
    angle = 180 - angle;

  }

  public void gira(int dir) {
    direccio = dir;
  }

  /**
   * Defineix la coordenada X de l'objecte.
   * @param i nova coordenada
   */
  public void setX(double i) {
    imatge.setLocation(i, imatge.getY());

  }

  /**
   * Defineix la coordenada Y de la pilota.
   * @param d nova coordenada
   */
  public void setY(double d) {
    imatge.setLocation(imatge.getX(), d);

  }

  public void mouRecte(double quant) {
   imatge.move(direccio * quant, 0);

  }

  public String toString() {
    return "(" + imatge.getX() + "," + imatge.getY() + ":" + direccio + "," + angle + "º)";
  }


}
