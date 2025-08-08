package org.suenos.sueños.accionesPostSueño;

import org.suenos.Usuario;

public class GanaLoQueQuiere implements AccionPostSueño {
  @Override
  public void serRealizada(Usuario usuario) {
    if(usuario.getBuenaPlata() >= usuario.getSalario()) {
      usuario.multiplicarBuenaPlata(2);
    }
  }
}