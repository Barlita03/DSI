package org.example.hits;

import org.example.Bag;
import org.example.contenido.Contenido;

public class EditarContenido extends Hit {
  private Contenido contenido;
  private Contenido modificacion;

  public EditarContenido (Bag bag, Contenido contenido, Contenido modificacion) {
    super(bag);
    this.contenido = contenido;
    this.modificacion = modificacion;
  }

  @Override
  public void serAprobado() {
    contenido.serEditado(modificacion);
    super.serAprobado();
  }
}
