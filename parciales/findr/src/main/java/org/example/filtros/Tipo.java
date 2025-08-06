package org.example.filtros;

import org.example.usuarios.Usuario;

import java.util.List;

public class Tipo implements Filtro {
  private final List<Filtro> filtros;

  public Tipo(List<Filtro> filtros) {
    this.filtros = filtros;
  }

  @Override
  public boolean cumple(Usuario usuario) {
    return filtros.stream().allMatch(f -> f.cumple(usuario));
  }
}
