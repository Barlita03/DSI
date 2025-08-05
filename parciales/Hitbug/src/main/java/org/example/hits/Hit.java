package org.example.hits;

import org.example.Bag;

public abstract class Hit {
  protected Bag bag;
  private EstadoHit estado = EstadoHit.PENDIENTE;

  public Hit (Bag bag) {
    this.bag = bag;
  }

  public void serAprobado() {
    estado = EstadoHit.APROBADO;
    bag.agregarAlHistorial(this);
    guardarEstadoBag();
  }

  public void serRechazado() {
    estado = EstadoHit.RECHAZADO;
    bag.agregarAlHistorial(this);
    guardarEstadoBag();
  }

  private void guardarEstadoBag() {
    bag = bag.clonar();
  }
}
