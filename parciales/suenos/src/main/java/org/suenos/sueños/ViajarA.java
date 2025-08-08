package org.suenos.sueños;

import org.suenos.Usuario;

public class ViajarA extends Sueño {
  private String lugar;

  public ViajarA(String lugar) {
    this.lugar = lugar;
  }

  @Override
  public int getFelicidad(Usuario usuario) {
    return 10;
  }
}
