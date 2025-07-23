package org.qmp.operaciones;

import org.qmp.Guardarropa;
import org.qmp.prendas.Prenda;

public abstract class Propuesta {
  protected final Guardarropa guardarropa;
  protected final Prenda prenda;

  public Propuesta(Guardarropa guardarropa, Prenda prenda) {
    this.guardarropa = guardarropa;
    this.prenda = prenda;

    guardarropa.agregarPropuesta(this);
  }

  public void serAceptada() {
    actualizarGuardarropa();
  }

  public void deshacer() {
    guardarropa.quitarPropuestaProcesada(this);
  }

  public void serRechazada() {
    actualizarGuardarropa();
  }

  private void actualizarGuardarropa() {
    guardarropa.quitarPropuesta(this);
    guardarropa.registrarPropuestaProcesada(this);
  }
}
