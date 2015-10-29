package net.xaviersala.pilotero;

import acm.graphics.GLabel;

public class Marcador {
  public static final int FONT=24;
  double campW;
  GLabel resultat;
  int[] gols = new int[2];

  public Marcador(GLabel etiqueta, double w) {
    campW = w;
    resultat = etiqueta;
    resultat.setFont("SansSerif-bold-"+FONT);

    generaEtiqueta();

  }

  /**
   *
   */
  private void generaEtiqueta() {
    resultat.setLabel(gols[0] + " - " + gols[1]);
    centra(resultat);
  }

  /**
   * @param etiqueta
   */
  private void centra(GLabel etiqueta) {
    double x = (campW - etiqueta.getWidth()) / 2;
    etiqueta.setLocation(x, FONT);
  }

  public void reset() {
    gols[0] = 0;
    gols[1] = 0;
  }

  public void marca(int qui) {
    if (qui >= 0 && qui < gols.length) {
      gols[qui] ++;
    }
    generaEtiqueta();

  }
}

