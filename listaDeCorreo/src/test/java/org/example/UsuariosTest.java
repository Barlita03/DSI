package org.example;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Emailer;
import org.example.mensajes.mensajeadores.Mensajeador;
import org.example.mensajes.mensajeadores.PhoneTextDecorator;
import org.example.mensajes.mensajeadores.PhoneVoiceDecorator;
import org.example.servicios.MailSender;
import org.example.servicios.PhoneTextSender;
import org.example.servicios.PhoneVoiceSender;
import org.junit.jupiter.api.Test;

public class UsuariosTest {
  Usuario usuario = new Usuario("jorgito@gmail.com", "1131429193");
  MailSender mailSender = mock(MailSender.class);
  PhoneTextSender phoneTextSender = mock(PhoneTextSender.class);
  PhoneVoiceSender phoneVoiceSender = mock(PhoneVoiceSender.class);
  Mensaje mensaje = new Mensaje(usuario, "Titulo", "Texto");

  @Test
  void unUsuarioPuedeRecibirMensajesDeTextoAdemasDelMail() {
    Mensajeador mensajeador = new PhoneTextDecorator(phoneTextSender, new Emailer(mailSender));
    usuario.setMensajeador(mensajeador);

    usuario.recibirMensaje(mensaje);

    verify(mailSender).send(any());
    verify(phoneTextSender).sendMessage(usuario.getTelefono(), mensaje.getTexto());
  }

  @Test
  void unUsuarioPuedeRecibirMensajesDeVozAdemasDelMail() {
    Mensajeador mensajeador =
        new PhoneVoiceDecorator(phoneVoiceSender, 10, new Emailer(mailSender));
    usuario.setMensajeador(mensajeador);

    usuario.recibirMensaje(mensaje);

    verify(mailSender).send(any());
    verify(phoneVoiceSender).sendMessage(usuario.getTelefono(), mensaje.getTexto(), 10);
  }

  @Test
  void unUsuarioPuedeRecibirMensajesDeVozYUnMensajeDeTextoAdemasDelMail() {
    Mensajeador mensajeador =
        new PhoneVoiceDecorator(
            phoneVoiceSender, 10, new PhoneTextDecorator(phoneTextSender, new Emailer(mailSender)));
    usuario.setMensajeador(mensajeador);

    usuario.recibirMensaje(mensaje);

    verify(mailSender).send(any());
    verify(phoneTextSender).sendMessage(usuario.getTelefono(), mensaje.getTexto());
    verify(phoneVoiceSender).sendMessage(usuario.getTelefono(), mensaje.getTexto(), 10);
  }
}
