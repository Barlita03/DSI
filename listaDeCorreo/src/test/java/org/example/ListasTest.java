package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.modosdesuscripcion.Abierta;
import org.example.listasdecorreo.modosdesuscripcion.Cerrada;
import org.example.listasdecorreo.privacidad.Privada;
import org.example.servicios.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class ListasTest {
  MailSender mailSender = mock(MailSender.class);
  ListaDeCorreo lista = new ListaDeCorreo("lista@gmail.com", List.of(), null, null, mailSender);
  Usuario usuario1 = new Usuario("jorgito@gmail.com");
  Usuario usuario2 = new Usuario("pepito@gmail.com");

  @BeforeEach
  void setup() {
    lista.vaciarMiembros();
    lista.cambiarPrivacidad(null);
    lista.cambiarModoDeSuscripcion(null);
  }

  @Test
  void sePuedeAgregarUnUsuarioAUnaLista() {
    assertEquals(0, lista.getMiembros().size());

    lista.agregarMiembro(usuario1);

    assertEquals(1, lista.getMiembros().size());
  }

  @Test
  void alEnviarUnMensajeAUnaListaElMismoSeEnviaATodosLosMiembros() {
    lista.agregarMiembro(usuario1);
    lista.agregarMiembro(usuario2);

    lista.enviarTodosLosMiembros(new Mensaje(null, "titulo", "texto"));

    ArgumentCaptor<Mail> captor = ArgumentCaptor.forClass(Mail.class);

    verify(mailSender, times(2)).send(captor.capture());

    List<Mail> mails = captor.getAllValues();

    assertEquals(2, mails.size());
    assertEquals(usuario1, mails.get(0).getDestinatario());
    assertEquals(usuario2, mails.get(1).getDestinatario());
  }

  @Test
  void puedoEnviarUnMensajeAUnaListaPrivadaSiSoyMiembro() {
    lista.cambiarPrivacidad(new Privada());
    lista.agregarMiembro(usuario1);
    lista.agregarMiembro(usuario2);

    lista.recibirMensaje(new Mensaje(usuario2, "titulo", "texto"));

    ArgumentCaptor<Mail> captor = ArgumentCaptor.forClass(Mail.class);

    verify(mailSender, times(2)).send(captor.capture());

    List<Mail> mails = captor.getAllValues();

    assertEquals(2, mails.size());
    assertEquals(usuario1, mails.get(0).getDestinatario());
    assertEquals(usuario2, mails.get(1).getDestinatario());
  }

  @Test
  void noPuedoEnviarUnMensajeAUnaListaPrivadaSiNoSoyMiembro() {
    lista.cambiarPrivacidad(new Privada());
    lista.agregarMiembro(usuario1);

    assertThrows(
        RuntimeException.class,
        () -> lista.recibirMensaje(new Mensaje(usuario2, "titulo", "texto")));
  }

  @Test
  void puedoSuscribirmeSinProblemasAUnaListaSiEsAbierta() {
    lista.cambiarModoDeSuscripcion(new Abierta());
    assertEquals(0, lista.getMiembros().size());

    lista.suscribirse(usuario1);

    assertEquals(1, lista.getMiembros().size());
  }

  @Test
  void siUnaListaEsCerradaSeGeneraUnPedidoDeSuscripcion() {
    lista.cambiarModoDeSuscripcion(new Cerrada());
    assertEquals(0, lista.getListaDeEspera().size());

    lista.suscribirse(usuario1);

    assertEquals(1, lista.getListaDeEspera().size());
  }
}
