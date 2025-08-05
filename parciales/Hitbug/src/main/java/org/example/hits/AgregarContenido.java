package org.example.hits;

import org.example.Bag;
import org.example.contenido.Contenido;

public class AgregarContenido extends Hit {
  private Contenido contenido;

  public AgregarContenido(Bag bag, Contenido contenido) {
    super(bag);
    this.contenido = contenido;
  }

  @Override
  public void serAprobado() {
    bag.agregarContenido(contenido);
    super.serAprobado();
  }
}
