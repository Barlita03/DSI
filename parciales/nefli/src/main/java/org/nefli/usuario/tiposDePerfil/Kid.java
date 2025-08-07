package org.nefli.usuario.tiposDePerfil;

public class Kid implements TipoDePerfil {
  @Override
  public boolean puedeVer(Contenido contenido) {
    return contenido.esATP();
  }
}