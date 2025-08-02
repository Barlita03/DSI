package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.modosdesuscripcion.Abierta;
import org.example.listasdecorreo.modosdesuscripcion.Cerrada;
import org.example.listasdecorreo.privacidad.Libre;
import org.example.listasdecorreo.privacidad.Privada;
import org.example.mensajes.Borrador;
import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Emailer;
import org.example.servicios.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class ListasTest {
  MailSender mailSender = mock(MailSender.class);
  ListaDeCorreo lista = new ListaDeCorreo("lista@gmail.com", List.of(), List.of(), null, null);
  Usuario usuario1 = new Usuario("jorgito@gmail.com", "1131429193");
  Usuario usuario2 = new Usuario("pepito@gmail.com", "1131429193");

  Emailer mensajeador = new Emailer(mailSender);

  @BeforeEach
  void setup() {
    usuario1.setMensajeador(mensajeador);
    usuario2.setMensajeador(mensajeador);
    lista.vaciarMiembros();
    lista.cambiarPrivacidad(null);
    lista.cambiarModoDeSuscripcion(null);
    lista.limpiarBloqueados();
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

    lista.enviarTodosLosMiembros(new Borrador(usuario1, "titulo", "texto"));

    ArgumentCaptor<Mensaje> captor = ArgumentCaptor.forClass(Mensaje.class);

    verify(mailSender, times(2)).send(captor.capture());

    List<Mensaje> mails = captor.getAllValues();

    assertEquals(2, mails.size());
    assertEquals(usuario1, mails.get(0).getDestinatario());
    assertEquals(usuario2, mails.get(1).getDestinatario());
  }

  @Test
  void puedoEnviarUnMensajeAUnaListaPrivadaSiSoyMiembro() {
    lista.cambiarPrivacidad(new Privada());
    lista.agregarMiembro(usuario1);
    lista.agregarMiembro(usuario2);

    lista.recibirMensaje(new Borrador(usuario2, "titulo", "texto"));

    ArgumentCaptor<Mensaje> captor = ArgumentCaptor.forClass(Mensaje.class);

    verify(mailSender, times(2)).send(captor.capture());

    List<Mensaje> mails = captor.getAllValues();

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
        () -> lista.recibirMensaje(new Borrador(usuario2, "titulo", "texto")));
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

  @Test
  void sePuedeBloquearAUnUsuario() {
    assertEquals(0, lista.getUsuariosBloqueados().size());

    lista.bloquearUsuario(usuario1);

    assertEquals(1, lista.getUsuariosBloqueados().size());
  }

  @Test
  void sePuedeDesbloquearAUnUsuario() {
    lista.bloquearUsuario(usuario1);
    assertEquals(1, lista.getUsuariosBloqueados().size());

    lista.desbloquearUsuario(usuario1);

    assertEquals(0, lista.getUsuariosBloqueados().size());
  }

  @Test
  void seAgregaElPrefijoALosMensajesEnviados() {
    lista.setPrefijo("prefijo");
    lista.cambiarPrivacidad(new Libre());
    lista.cambiarModoDeSuscripcion(new Abierta());
    lista.suscribirse(usuario2);

    lista.recibirMensaje(new Borrador(usuario1, "titulo", "texto"));

    assertTrue(usuario2.getCasillaDeMensajes().get(0).getTexto().contains("prefijo"));
  }

  @Test
  void seAgregaElPieDePaginaALosMensajesEnviados() {
    lista.setPieDePagina("pie de pagina");
    lista.cambiarPrivacidad(new Libre());
    lista.cambiarModoDeSuscripcion(new Abierta());
    lista.suscribirse(usuario2);

    lista.recibirMensaje(new Borrador(usuario1, "titulo", "texto"));

    assertTrue(usuario2.getCasillaDeMensajes().get(0).getTexto().contains("pie de pagina"));
  }

  @Test
  void sePuedenConvinarPieDePaginaYPrefijo() {
    lista.setPrefijo("prefijo");
    lista.setPieDePagina("pie de pagina");
    lista.cambiarPrivacidad(new Libre());
    lista.cambiarModoDeSuscripcion(new Abierta());
    lista.suscribirse(usuario2);

    usuario1.setFirma("la cabra");

    lista.recibirMensaje(new Borrador(usuario1, "titulo", "texto"));

    assertTrue(usuario2.getCasillaDeMensajes().get(0).getTexto().contains("prefijo"));
    assertTrue(usuario2.getCasillaDeMensajes().get(0).getTexto().contains("pie de pagina"));
  }
}
