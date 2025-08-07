package org.nefli.usuario.tiposDePerfil;

public class Adulto implements TipoDePerfil {
  @Override
  public boolean puedeVer(Contenido contenido) {
    return true;
  }
}
