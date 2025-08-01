package org.example.servicios;

public interface PhoneVoiceSender {
  void sendMessage(String telefono, String texto, int velocidad);
}
