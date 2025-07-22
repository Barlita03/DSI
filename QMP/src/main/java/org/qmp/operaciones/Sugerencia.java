package org.qmp.operaciones;

import org.qmp.Guardarropa;
import org.qmp.prendas.Prenda;

public abstract class Sugerencia {
  protected final Guardarropa guardarropa;
  protected final Prenda prenda;

  public Sugerencia(Guardarropa guardarropa, Prenda prenda) {
    this.guardarropa = guardarropa;
    this.prenda = prenda;
  }

  public void serAceptada() {}

  public void deshacer() {}

  public void serRechazada() {
    guardarropa.quitarSugerencia(this);
  }
}
