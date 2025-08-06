package org.example.servicios;

import org.example.usuarios.Usuario;

public interface Notificador {
  void enviarNotificacion(Usuario usuario, String mensaje);
}
