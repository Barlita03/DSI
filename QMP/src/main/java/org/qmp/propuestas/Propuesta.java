package org.qmp.propuestas;

import org.qmp.prendas.Guardarropa;
import org.qmp.prendas.Prenda;

public abstract class Propuesta {
  protected final Guardarropa guardarropa;
  protected final Prenda prenda;
  private EstadoPropuesta estado;

  public Propuesta(Guardarropa guardarropa, Prenda prenda) {
    this.guardarropa = guardarropa;
    this.prenda = prenda;
    this.estado = EstadoPropuesta.PENDIENTE;

    guardarropa.agregarPropuesta(this);
  }

  public void serAceptada() {
    realizarAceptacion();
    estado = EstadoPropuesta.ACEPTADA;
  }

  public void deshacer() {
    if (estado.equals(EstadoPropuesta.ACEPTADA)) {
      realizarDeshacer();
    }
    estado = EstadoPropuesta.PENDIENTE;
  }

  public void serRechazada() {
    estado = EstadoPropuesta.RECHAZADA;
  }

  public EstadoPropuesta getEstado() {
    return estado;
  }

  public abstract void realizarAceptacion();

  public abstract void realizarDeshacer();
}
