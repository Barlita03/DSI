package org.example.filtros;

import org.example.usuarios.Usuario;

public interface Filtro {
  boolean cumple(Usuario usuario);
}
