package org.suenos.sueños;

import org.suenos.Usuario;

public class AdoptarXHijos extends Sueño {
  private int cantidad;

  public AdoptarXHijos(int cantidad) {
    this.cantidad = cantidad;
  }

  @Override
  public void serCumplido(Usuario usuario) {
    usuario.agregarHijos(cantidad);
    usuario.sumarFelicidonios(getFelicidad(usuario));
    super.serCumplido(usuario);
  }

  @Override
  public int getFelicidad(Usuario usuario) {
    return 55 - (usuario.getCantidadDeHijos() * 10);
  }
}
