package org.example.mensajes.mensajeadores;

import org.example.Usuario;
import org.example.mensajes.Mensaje;
import org.example.servicios.PhoneTextSender;

public class PhoneTextDecorator extends MensajeadorDecorator {
  private final PhoneTextSender phoneTextSender;

  public PhoneTextDecorator(PhoneTextSender phoneTextSender, Mensajeador wrapee) {
    super(wrapee);
    this.phoneTextSender = phoneTextSender;
  }

  @Override
  public void enviarMensaje(Mensaje mensaje, Usuario destinatario) {
    phoneTextSender.sendMessage(destinatario.getTelefono(), mensaje.getTexto());
    super.enviarMensaje(mensaje, destinatario);
  }
}
