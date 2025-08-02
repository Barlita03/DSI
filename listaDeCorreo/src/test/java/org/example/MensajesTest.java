package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.example.mensajes.Borrador;
import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Emailer;
import org.example.mensajes.mensajeadores.Mensajeador;
import org.example.servicios.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MensajesTest {
  MailSender mailSender = mock(MailSender.class);
  Mensajeador mensajeador = new Emailer(mailSender);
  Usuario usuario = new Usuario("jorgito@gmail.com", "1131429193");

  @BeforeEach
  void setup() {
    usuario.setMensajeador(mensajeador);
  }

  @Test
  void unMensajeSeEnviaEncriptado() {
    Borrador borrador = new Borrador(usuario, "titlo", "texto");

    Mensaje mensaje = new Mensaje(borrador, usuario);

    assertNotEquals("texto", mensaje.getTexto());
  }

  @Test
  void alRecibirElMensajeElUsuarioLoRecibeDesencriptado() {
    Borrador borrador = new Borrador(usuario, "titlo", "texto");

    Mensaje mensaje = new Mensaje(borrador, usuario);

    usuario.recibirMensaje(mensaje);

    assertEquals("texto", usuario.getCasillaDeMensajes().get(0).getTexto());
  }
}
