package org.example;

import org.example.filtros.Filtro;
import org.example.filtros.FiltroMalasPalabras;
import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.privacidad.Libre;
import org.example.mensajes.Mail;
import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Emailer;
import org.example.mensajes.mensajeadores.Mensajeador;
import org.example.servicios.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.resolver.CaptorParameterResolver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FiltrosTest {
  Filtro filtro = new FiltroMalasPalabras(List.of("palabra1", "palabra2", "palabra3"));
  Usuario usuario1 = new Usuario("jorgito@gmail.com", "1131429193");
  Usuario usuario2 = new Usuario("pepito@gmail.com", "1131429193");
  ListaDeCorreo lista = new ListaDeCorreo("lista@gmail.com", List.of(usuario1), List.of(usuario1, usuario2), new Libre(), null);
  MailSender mailSender = mock(MailSender.class);
  Mensajeador mensajeador = new Emailer(mailSender);

  @BeforeEach
  void setup() {
    usuario1.setMensajeador(mensajeador);
    usuario2.setMensajeador(mensajeador);
  }

  @Test
  void sePuedenAgregarFiltrosAUnaLista() {
    assertEquals(0, lista.getFiltros().size());

    lista.agregarFiltro(filtro);

    assertEquals(1, lista.getFiltros().size());
  }

  @Test
  void sePuedenLimpiaLosFiltrosDeUnaLista() {
    lista.agregarFiltro(filtro);
    assertEquals(1, lista.getFiltros().size());

    lista.quitarTodosLosFiltros();

    assertEquals(0, lista.getFiltros().size());
  }

  @Test
  void siUnUsuarioEnviaUnMensajeConMalasPalabraSeNotificaALosAdminYSeLeAplicaUnStrike() {
    lista.agregarFiltro(filtro);

    Mensaje mensaje = new Mensaje(usuario2, "Titulo", "palabra1");
    lista.recibirMensaje(mensaje);

    ArgumentCaptor<Mail> captor = ArgumentCaptor.forClass(Mail.class);
    verify(mailSender).send(captor.capture());
    assertEquals(usuario1, captor.getValue().getDestinatario());

    assertEquals(1, lista.getStrikes().get(usuario2));
  }

  @Test
  void alLlegarALos5StrikesElUsuarioEsBaneado() {
    lista.agregarFiltro(filtro);

    Mensaje mensaje = new Mensaje(usuario2, "Titulo", "palabra1");

    lista.recibirMensaje(mensaje);
    lista.recibirMensaje(mensaje);
    lista.recibirMensaje(mensaje);
    lista.recibirMensaje(mensaje);
    lista.recibirMensaje(mensaje);

    assertEquals(1, lista.getUsuariosBloqueados().size());
  }

  @Test
  void sePuedeBloquearAUnUsuario() {
    // TODO
  }

  @Test
  void sePuedeDesbloquearAUnUsuario() {
    // TODO
  }
}