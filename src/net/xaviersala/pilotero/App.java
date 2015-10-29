package net.xaviersala.pilotero;

import java.awt.event.KeyEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class App extends GraphicsProgram {

  private static final int ALTPANTALLA = 600;
  private static final int AMPLEPANTALLA = 800;

  /**
   *
   */
  private static final long serialVersionUID = 449430650729152965L;

  Camp camp;

  public void run() {

    // Crear els objectes del joc
    GRectangle mida = new GRectangle(getWidth(), getHeight());

    GImage pilota = new GImage("pilota.png");
    add(pilota);
    GImage pala1 = new GImage("pala.png");
    add(pala1);
    GImage pala2 = new GImage("pala.png");
    add(pala2);
    GLabel marcador = new GLabel("0 - 0");
    add(marcador);

    // Passa els objectes al camp
    camp = new Camp(mida,
        new Pala(pala1, +1),
        new Pala(pala2, -1),
        new Pilota(pilota),
        marcador );



    // Començar la partida
    camp.comencarPartida();

    clicaPerComencar();

    // Va fent infinitament.
    while (true) {
      camp.mou();
      pause(80);
    }

  }

  /**
   * Prem una tecla i es mou en la direcció que toca.
   * @param e event
   */
  @Override
  public final void keyPressed(final KeyEvent e) {

      switch(e.getKeyCode()) {
      case KeyEvent.VK_UP:
          camp.mouPala(-1);
          break;
      case KeyEvent.VK_DOWN:
          camp.mouPala(1);
          break;
      case KeyEvent.VK_SPACE:
          camp.mouPala(0);
          break;
      default:
          break;
      }
  }

  /**
   * Clica per començar.
   */
  private void clicaPerComencar() {
      GLabel label = new GLabel("Clica per començar");
      double x = (getWidth() - label.getWidth()) / 2;
      double y = (getHeight() + label.getAscent()) / 2;
      add(label, x, y);
      waitForClick();
      remove(label);
  }

  public final void init() {

    setSize(AMPLEPANTALLA, ALTPANTALLA);
    addKeyListeners(this);
    pause(100);
}

}
