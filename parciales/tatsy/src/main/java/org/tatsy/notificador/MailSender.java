package org.tatsy.notificador;

public interface MailSender {
  void send(String mail, String texto);
}
