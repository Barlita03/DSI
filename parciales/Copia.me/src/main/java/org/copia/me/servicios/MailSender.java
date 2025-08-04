package org.copia.me.servicios;

public interface MailSender {
  void send(String email, String contenido);
}
