package org.tatsy.notificador;

import org.tatsy.Usuario;

public interface Notificador {
  void notificar(Usuario usuario, String mensaje);
}
