package net.xaviersala.pilotero;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Pala {
  private static final int VELOCITATPALA = 10;
  GImage imatge;
  int mira;
  int direccio;
  int velocitat = VELOCITATPALA;

  /**
   * Crea un objete Pala a partir d'una imatge.
   *
   * Inicialment està aturat.
   *
   * @param imatge
   */
  public Pala(GImage imatge, int miraA) {
    this.imatge = imatge;
    mira = miraA;
    direccio = 0;
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
   * Mou a pala cap a dalt o cap a baix
   *
   * El moviment bé determinat per la variable `moviment`
   */
  public void mou() {
    imatge.move(0,VELOCITATPALA * direccio);
  }

  public void gira() {
    direccio *= direccio;
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
   * Funció que mou la pala cap a on és la pilota.
   *
   * Es basa en comprovar els punts mitjos dels dos objectes per veure
   * cap a on s'ha de moure.
   * @param pilota
   */
  public void calculaMoviment(Pilota pilota) {

    GRectangle posicioPilota = pilota.getEspaiQueOcupa();
    double mig = posicioPilota.getY() + posicioPilota.getHeight()/2;

    if (pilota.getDireccio() == mira) {
      // La pilota s'allunya
      if (Math.abs(mig - (imatge.getY() + imatge.getHeight()/2)) > 100) {
        anarCapA(mig);
      } else {
        direccio = 0;
      }

    } else {
      anarCapA(mig);
    }
  }

  /**
   * @param pilota
   */
  private void anarCapA(double mig) {

    int direccioAMoure = (int) (mig - (imatge.getY() + imatge.getHeight()/2) );
    // Comprova que no és zero ja que petaria ...
    if (direccioAMoure != 0) {
      direccio = (int) (direccioAMoure/Math.abs(direccioAMoure));
    } else {
      direccio = 0;
    }
  }



//  public int calculaNovaDireccio(GRectangle posicioPilota) {
//    int migPala = (int) (imatge.getY() + imatge.getHeight()/2);
//    GRectangle pica = imatge.getBounds().intersection(posicioPilota);
//    int migPilota = (int) (pica.getY() + pica.getHeight()/2);
//
//    if (migPilota == migPala) return 0;
//
//    return (migPilota - migPala) / Math.abs(migPilota - migPala);
//  }

  public int rebotaPilota(Pilota pilota) {

    double mitjaPala = (int) (imatge.getHeight()/2);

    GRectangle pica = imatge.getBounds().intersection(pilota.getEspaiQueOcupa());
    double impacte = pica.getY() + pica.getHeight()/2;

    double impacteAPala = imatge.getY() + mitjaPala - impacte;

    double normalizedRelativeIntersectionY = impacteAPala / mitjaPala;
    double rebotd = normalizedRelativeIntersectionY * 90;

    int rebot = (int) rebotd;

    // int nouAngle = (int) ((90 * impacteAPala) / (imatge.getHeight()/2));
    pilota.gira(mira);
    pilota.mouRecte(pica.getWidth()+1);
    pilota.setAngle(rebot % 365);


    return 0;
  }

  public void setDireccio(int x) {
    direccio = x;
  }

  public void setY(double y) {
    imatge.setLocation(imatge.getX(), y);

  }
}
