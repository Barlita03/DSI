package org.example.servicios;

import org.example.mensajes.Mensaje;

public interface MailSender {
  void send(Mensaje mail);
}
