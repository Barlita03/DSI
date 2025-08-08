package org.suenos.sueños;

import org.suenos.Usuario;

public class RecibirseDeUnaCarrera extends Sueño {
  private String carrera;

  public RecibirseDeUnaCarrera(String carrera) {
    this.carrera = carrera;
  }

  @Override
  public int getFelicidad(Usuario usuario) {
    return 100;
  }
}
