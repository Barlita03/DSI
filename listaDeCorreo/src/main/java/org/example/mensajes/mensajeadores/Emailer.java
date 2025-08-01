package org.example.mensajes.mensajeadores;

import org.example.Usuario;
import org.example.mensajes.Mail;
import org.example.mensajes.Mensaje;
import org.example.servicios.MailSender;

public class Emailer implements Mensajeador {
  private MailSender mailSender;

  public Emailer(MailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarMensaje(Mensaje mensaje, Usuario destinatario) {
    mailSender.send(new Mail(mensaje, destinatario));
  }
}
