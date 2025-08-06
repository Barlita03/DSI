package org.example.servicios;

import org.example.usuarios.Usuario;

public interface Localizador {
  Ubicacion localizar(Usuario user);
}
