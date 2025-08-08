package org.tatsy.notificador;

import jdk.internal.net.http.websocket.WebSocketImpl;
import org.tatsy.Usuario;

public class NotificadorWebApp implements Notificador {
  private AppSender appSender;

  @Override
  public void notificar(Usuario usuario, String mensaje) {
    appSender.send(usuario.getUsername(), mensaje);
  }
}
