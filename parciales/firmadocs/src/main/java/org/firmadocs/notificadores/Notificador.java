package org.firmadocs.notificadores;

import org.firmadocs.usuarios.Usuario;

public interface Notificador {
  void notificar(Usuario usuario, String mensaje);
}
