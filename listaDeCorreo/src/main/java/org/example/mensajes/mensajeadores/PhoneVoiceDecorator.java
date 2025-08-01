package org.example.mensajes.mensajeadores;

import org.example.Usuario;
import org.example.mensajes.Mensaje;
import org.example.servicios.PhoneVoiceSender;

public class PhoneVoiceDecorator extends MensajeadorDecorator {
  private final PhoneVoiceSender phoneVoiceSender;
  private final int velocidad;

  public PhoneVoiceDecorator(PhoneVoiceSender phoneVoiceSender, int velocidad, Mensajeador wrapee) {
    super(wrapee);
    this.phoneVoiceSender = phoneVoiceSender;
    this.velocidad = velocidad;
  }

  @Override
  public void enviarMensaje(Mensaje mensaje, Usuario destinatario) {
    phoneVoiceSender.sendMessage(destinatario.getTelefono(), mensaje.getTexto(), velocidad);
    super.enviarMensaje(mensaje, destinatario);
  }
}
