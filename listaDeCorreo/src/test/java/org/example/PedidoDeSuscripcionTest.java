package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.PedidoDeSuscripcion;
import org.example.listasdecorreo.modosdesuscripcion.Cerrada;
import org.example.mensajes.Mail;
import org.example.mensajes.mensajeadores.Emailer;
import org.example.servicios.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class PedidoDeSuscripcionTest {
  MailSender mailSender = mock(MailSender.class);
  ListaDeCorreo lista =
      new ListaDeCorreo("lista@gmail.com", List.of(), List.of(), null, new Cerrada());
  Usuario usuario = new Usuario("jorgito@gmail.com", "1131429193");
  Emailer mensajeador = new Emailer(mailSender);

  @BeforeEach
  void setup() {
    usuario.setMensajeador(mensajeador);
  }

  @Test
  void unPedidoDeSuscripcionPuedeSerAceptado() {
    lista.suscribirse(usuario);
    PedidoDeSuscripcion pedido = lista.getListaDeEspera().get(0);
    pedido.serAceptado();

    ArgumentCaptor<Mail> mail = ArgumentCaptor.forClass(Mail.class);
    verify(mailSender).send(mail.capture());

    Mail mailEnviado = mail.getValue();

    assertNull(mailEnviado.getOrigen());
    assertEquals(usuario, mailEnviado.getDestinatario());
    assertEquals("Solicitud de suscripcion aceptada", mailEnviado.getTitulo());
    assertEquals(
        "Tu solicitud de suscripcion a lista@gmail.com a sido aceptada", mailEnviado.getTexto());
  }

  @Test
  void unPedidoDeSuscripcionPuedeSerRechazado() {
    lista.suscribirse(usuario);
    PedidoDeSuscripcion pedido = lista.getListaDeEspera().get(0);
    pedido.serRechazado();

    ArgumentCaptor<Mail> mail = ArgumentCaptor.forClass(Mail.class);
    verify(mailSender).send(mail.capture());

    Mail mailEnviado = mail.getValue();

    assertNull(mailEnviado.getOrigen());
    assertEquals(usuario, mailEnviado.getDestinatario());
    assertEquals("Solicitud de suscripcion rechazada", mailEnviado.getTitulo());
    assertEquals(
        "Tu solicitud de suscripcion a lista@gmail.com a sido rechazada", mailEnviado.getTexto());
  }
}
