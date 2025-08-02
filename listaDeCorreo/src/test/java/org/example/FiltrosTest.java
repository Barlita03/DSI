package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.example.filtros.Filtro;
import org.example.filtros.FiltroMalasPalabras;
import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.privacidad.Libre;
import org.example.mensajes.Borrador;
import org.example.mensajes.Mensaje;
import org.example.mensajes.mensajeadores.Emailer;
import org.example.mensajes.mensajeadores.Mensajeador;
import org.example.servicios.MailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class FiltrosTest {
  Filtro filtro = new FiltroMalasPalabras(List.of("palabra1", "palabra2", "palabra3"));
  Usuario usuario1;
  Usuario usuario2;

  {
    try {
      usuario1 = new Usuario("jorgito@gmail.com", "1131429193");
      usuario2 = new Usuario("pepito@gmail.com", "1131429193");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  ListaDeCorreo lista =
      new ListaDeCorreo(
          "lista@gmail.com", List.of(usuario1), List.of(usuario1, usuario2), new Libre(), null);
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

    Borrador borrador = new Borrador(usuario2, "Titulo", "palabra1");
    lista.recibirMensaje(borrador);

    ArgumentCaptor<Mensaje> captor = ArgumentCaptor.forClass(Mensaje.class);
    verify(mailSender).send(captor.capture());
    assertEquals(usuario1, captor.getValue().getDestinatario());

    assertEquals(1, lista.getStrikes().get(usuario2));
  }

  @Test
  void alLlegarALos5StrikesElUsuarioEsBaneado() {
    lista.agregarFiltro(filtro);

    Borrador borrador = new Borrador(usuario2, "Titulo", "palabra1");

    lista.recibirMensaje(borrador);
    lista.recibirMensaje(borrador);
    lista.recibirMensaje(borrador);
    lista.recibirMensaje(borrador);
    lista.recibirMensaje(borrador);

    assertEquals(1, lista.getUsuariosBloqueados().size());
  }
}
