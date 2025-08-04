package org.noodle.servicios;

public interface MailSender {
  void send(String address, String subject, String body);
}
