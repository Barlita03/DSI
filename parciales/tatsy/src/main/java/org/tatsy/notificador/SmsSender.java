package org.tatsy.notificador;

public interface SmsSender {
  void send(String telefono, String texto);
}
