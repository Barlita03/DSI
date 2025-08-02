package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.PedidoDeSuscripcion;
import org.example.listasdecorreo.modosdesuscripcion.Cerrada;
import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Emailer;
import org.example.servicios.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class PedidoDeSuscripcionTest {
  MailSender mailSender = mock(MailSender.class);
  Usuario usuario1 = new Usuario("jorgito@gmail.com", "1131429193");
  Usuario usuario2 = new Usuario("pepito@gmail.com", "1131429193");

  ListaDeCorreo lista =
      new ListaDeCorreo("lista@gmail.com", List.of(usuario2), List.of(), null, new Cerrada());
  Emailer mensajeador = new Emailer(mailSender);

  @BeforeEach
  void setup() {
    usuario1.setMensajeador(mensajeador);
  }

  @Test
  void unPedidoDeSuscripcionPuedeSerAceptado() {
    lista.suscribirse(usuario1);
    PedidoDeSuscripcion pedido = lista.getListaDeEspera().get(0);
    pedido.serAceptado();

    ArgumentCaptor<Mensaje> mail = ArgumentCaptor.forClass(Mensaje.class);
    verify(mailSender).send(mail.capture());

    Mensaje mailEnviado = mail.getValue();

    assertEquals(usuario1, mailEnviado.getDestinatario());
    assertEquals("Solicitud de suscripcion aceptada", mailEnviado.getTitulo());
  }

  @Test
  void unPedidoDeSuscripcionPuedeSerRechazado() {
    lista.suscribirse(usuario1);
    PedidoDeSuscripcion pedido = lista.getListaDeEspera().get(0);
    pedido.serRechazado();

    ArgumentCaptor<Mensaje> mail = ArgumentCaptor.forClass(Mensaje.class);
    verify(mailSender).send(mail.capture());

    Mensaje mailEnviado = mail.getValue();

    assertEquals(usuario1, mailEnviado.getDestinatario());
    assertEquals("Solicitud de suscripcion rechazada", mailEnviado.getTitulo());
  }
}
