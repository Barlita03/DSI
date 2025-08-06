package org.example.filtros;

import org.example.usuarios.Usuario;

public class FiltroPorAltura implements Filtro {
  private final int alturaMinima;
  private final int alturaMaxima;

  public FiltroPorAltura(int alturaMaxima, int alturaMinima) {
    this.alturaMaxima = alturaMaxima;
    this.alturaMinima = alturaMinima;
  }

  @Override
  public boolean cumple(Usuario usuario) {
    return usuario.getAltura() >= alturaMinima && usuario.getAltura() <= alturaMaxima;
  }
}
