package org.suenos.sueños.accionesPostSueño;

import org.suenos.Usuario;
import org.suenos.sueños.ViajarA;

public class ViajaA implements AccionPostSueño {
  private String lugar;

  @Override
  public void serRealizada(Usuario usuario) {
    usuario.agregarSueño(new ViajarA(lugar));

  }
}
