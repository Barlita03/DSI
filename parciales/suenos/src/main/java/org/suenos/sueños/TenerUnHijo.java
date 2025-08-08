package org.suenos.sueños;

import org.suenos.Usuario;

public class TenerUnHijo extends Sueño {
  @Override
  public void serCumplido(Usuario usuario) {
    usuario.agregarHijos(1);
    usuario.sumarFelicidonios(getFelicidad(usuario));
    super.serCumplido(usuario);
  }

  @Override
  public int getFelicidad(Usuario usuario) {
    return 55 - (usuario.getCantidadDeHijos() * 10);
  }
}