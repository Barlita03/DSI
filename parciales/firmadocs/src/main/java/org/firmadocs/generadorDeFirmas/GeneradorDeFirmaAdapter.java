package org.firmadocs.generadorDeFirmas;

import org.firmadocs.usuarios.Usuario;

public class GeneradorDeFirmaAdapter implements GeneradorDeFirmasInterface {
  private GeneradorDeFirma api;

  @Override
  public String generarFirma(Usuario usuario) {
    return api.generarFirma(usuario.getNombre(), usuario.getApellido(), usuario.getMail(), usuario.getTelefono());
  }
}
