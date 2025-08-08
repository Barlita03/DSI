package org.suenos.sueños.accionesPostSueño;

import org.suenos.Usuario;

public class ResignarSueñoMasImportante implements AccionPostSueño {
  @Override
  public void serRealizada(Usuario usuario) {
    usuario.removerSueño(usuario.sueñoDeMasValor());
  }
}
