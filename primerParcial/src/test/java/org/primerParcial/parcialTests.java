package org.primerParcial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class parcialTests {

  @BeforeEach
  public void setup() {
    GestorDeCanales.limpiarLista();
    TransmisionesEnCurso.limpiarLista();
  }

  @Test
  public void sePuedeIniciarUnaTransmision() {
    Canal canal = new Canal();

    Transmision transmision = new Transmision(canal, "transmision");

    assertNull(transmision.getInicio());
    assertEquals(0, TransmisionesEnCurso.getInstancia().getTransmisiones().size());

    transmision.serIniciada();

    assertEquals(
        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
        transmision.getInicio().truncatedTo(ChronoUnit.SECONDS));
    assertEquals(1, TransmisionesEnCurso.getInstancia().getTransmisiones().size());
  }

  @Test
  public void sePuedenListarCanales() {
    new Canal();
    new Canal();
    new Canal();

    assertEquals(3, GestorDeCanales.getInstancia().getCanales().size());
  }

  @Test
  public void sePuedenListarLasTransmisionesHistoricasDeUnCanal() {
    Canal canal = new Canal();

    canal.agregarAlHistorico(new Transmision(canal, "transmision"));
    canal.agregarAlHistorico(new Transmision(canal, "transmision"));
    canal.agregarAlHistorico(new Transmision(canal, "transmision"));

    assertEquals(3, canal.getHistoricoTransmisiones().size());
  }

  @Test
  public void sePuedenListarLasTransmisionesEnCurso() {
    Canal canal = new Canal();

    new Transmision(canal, "transmision").serIniciada();
    new Transmision(canal, "transmision").serIniciada();
    new Transmision(canal, "transmision").serIniciada();

    assertEquals(3, TransmisionesEnCurso.getInstancia().getTransmisiones().size());
  }

  @Test
  public void unUsuarioPuedeSuscribirseAUnCanal() {
    Canal canal1 = new Canal();
    Canal canal2 = new Canal();

    assertEquals(0, canal1.getSuscripciones().size());

    canal1.suscribirse(canal2);

    assertEquals(1, canal1.getSuscripciones().size());
  }

  @Test
  public void unUsuarioPuedeDarMuestrasDeApoyoAUnCanal() {
    Canal canal = new Canal();

    assertEquals(0, canal.getMuestrasDeApoyo().size());

    canal.recibirApoyo((byte) 10);

    assertEquals(1, canal.getMuestrasDeApoyo().size());
  }

  @Test
  public void unUsuarioPuedeUnirseAUnaTransmision() {
    Canal canal1 = new Canal();
    Canal canal2 = new Canal();

    Transmision transmision = new Transmision(canal1, "transmision");

    assertEquals(0, transmision.getEspectadores().size());

    transmision.agregarVisualizador(canal2);

    assertEquals(1, transmision.getEspectadores().size());
  }

  @Test
  public void unUsuarioPuedeEnviarUnMensaje() {
    Canal canal = new Canal();

    Transmision transmision = new Transmision(canal, "transmision");

    assertEquals(0, transmision.getChat().size());

    transmision.recibirMensaje(canal, "mensaje");

    assertEquals(1, transmision.getChat().size());
  }

  @Test
  public void unUsuarioPuedeVerLosMensajesEnviadosEnUnaTransmision() {
    Canal canal = new Canal();

    Transmision transmision = new Transmision(canal, "transmision");

    transmision.recibirMensaje(canal, "mensaje");
    transmision.recibirMensaje(canal, "mensaje");
    transmision.recibirMensaje(canal, "mensaje");

    assertEquals(3, transmision.getChat().size());
    assertTrue(transmision.getChat().stream().allMatch(m -> m.getContenido().equals("mensaje")));
    assertTrue(transmision.getChat().stream().allMatch(m -> m.getAutor().equals(canal)));
    assertTrue(
        transmision.getChat().stream()
            .allMatch(
                m ->
                    m.getFecha()
                        .truncatedTo(ChronoUnit.SECONDS)
                        .equals(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))));
  }
}
