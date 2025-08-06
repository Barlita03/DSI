package org.firmadocs.notificadores;

import org.firmadocs.servicios.MailSender;
import org.firmadocs.usuarios.Usuario;

public class NotificadorMail implements Notificador {
  private MailSender api;

  @Override
  public void notificar(Usuario usuario, String mensaje) {
    api.send(usuario.getMail(), mensaje);
  }
}
