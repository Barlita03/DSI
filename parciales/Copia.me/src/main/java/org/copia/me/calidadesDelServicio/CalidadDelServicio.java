package org.copia.me.calidadesDelServicio;

import org.copia.me.servicios.Servicio;

public abstract class CalidadDelServicio {
  private final Servicio servicio;

  public CalidadDelServicio (Servicio servicio) {
    this.servicio = servicio;
  }
}
