package org.example.servicios;

import org.example.mensajes.Mail;

public interface MailSender {
  void send(Mail mail);
}
