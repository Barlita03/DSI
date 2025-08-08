package org.tatsy.notificador;

import org.tatsy.Usuario;

public class NotificadorSms implements Notificador {
  private SmsSender smsSender;

  @Override
  public void notificar(Usuario usuario, String mensaje) {
    smsSender.send(usuario.getTelefono(), mensaje);
  }
}
