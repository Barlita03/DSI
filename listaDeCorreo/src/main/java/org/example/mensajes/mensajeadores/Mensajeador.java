package org.example.mensajes.mensajeadores;

import org.example.Usuario;
import org.example.mensajes.Mensaje;

public interface Mensajeador {
  void enviarMensaje(Mensaje mensaje, Usuario destinatario);
}
