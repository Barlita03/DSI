package org.example.hits;

import org.example.Bag;
import org.example.contenido.Contenido;

public class QuitarContenido extends Hit {
  private Contenido contenido;

  public QuitarContenido(Bag bag, Contenido contenido) {
    super(bag);
    this.contenido = contenido;
  }

  @Override
  public void serAprobado() {
    bag.quitarContenido(contenido);
    super.serAprobado();
  }
}
