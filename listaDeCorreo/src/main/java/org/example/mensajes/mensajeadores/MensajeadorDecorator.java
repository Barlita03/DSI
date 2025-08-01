package org.example.mensajes.mensajeadores;

import org.example.Usuario;
import org.example.mensajes.Mensaje;

public abstract class MensajeadorDecorator implements Mensajeador {
  private Mensajeador wrapee;

  public MensajeadorDecorator(Mensajeador wrapee) {
    this.wrapee = wrapee;
  }

  @Override
  public void enviarMensaje(Mensaje mensaje, Usuario destinatario) {
    wrapee.enviarMensaje(mensaje, destinatario);
  }
}
