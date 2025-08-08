package org.tatsy.notificador;

import org.tatsy.Usuario;

public class NotificadorMail implements Notificador {
  private MailSender mailSender;

  @Override
  public void notificar(Usuario usuario, String mensaje) {
    mailSender.send(usuario.getMail(), mensaje);
  }
}
