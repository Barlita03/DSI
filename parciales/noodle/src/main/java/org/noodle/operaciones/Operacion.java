package org.noodle.operaciones;

import org.noodle.grupos.Grupo;

public abstract class Operacion {
  protected final Grupo grupo;

  public Operacion (Grupo grupo) {
    this.grupo = grupo;
  }

  public abstract void serAceptada();

  public void serRechazada() {
    grupo.removerOperacionPendiente(this);
  };
}
