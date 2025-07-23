package org.qmp.notificadores;

import org.qmp.apis.MailSender;

public class EmailerDecorator extends NotificadorDecorator {
  private final MailSender mailSender;
  private final String email;

  public EmailerDecorator(String email, MailSender mailSender, Notificador notificador) {
    super(notificador);
    this.mailSender = mailSender;
    this.email = email;
  }

  @Override
  public void notificar(String mensaje) {
    mailSender.send(email, mensaje);
    super.notificar(mensaje);
  }
}
