package org.tatsy.solicitudesDeVehiculo;

import org.tatsy.Posicion;

import java.time.LocalDateTime;

public class SolicitudDeVehiculoProgramada extends SolicitudDeVehiculo {
  private LocalDateTime fecha;

  public SolicitudDeVehiculoProgramada(Posicion posicion, LocalDateTime fecha) {
    super(posicion);
    this.fecha = fecha;
  }

  @Override
  public void buscarVehiculo() {
    // TODO para que se ejecute solo 15 min antes de la hora establecida
  }
}
