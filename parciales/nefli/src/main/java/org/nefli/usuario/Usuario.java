package org.nefli.usuario;

import org.nefli.usuario.tiposDePerfil.TipoDePerfil;

public class Usuario {
  private List<ReproductorUsuario> reproductores;
  private List<Pelicula> peliculasVistas;
  private TipoDePerfil tipo;

  public boolean puedeVer(Contenido contenido) {
    return tipo.puedeVer(contenido);
  }

  public List<ReproductorUsuario> continuarViendo() {
    return reproductores;
  }
}