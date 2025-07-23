package org.primerParcial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParcialTests {

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
  public void unCanalPuedeTenerSoloUnaTransmisionEnCurso() {
    Canal canal = new Canal();

    Transmision transmision1 = new Transmision(canal, "transmision1");
    Transmision transmision2 = new Transmision(canal, "transmision2");

    transmision1.serIniciada();

    assertThrows(RuntimeException.class, transmision2::serIniciada);

    transmision1.serFinalizada();
    transmision2.serIniciada();

    assertEquals(
        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
        transmision1.getFin().truncatedTo(ChronoUnit.SECONDS));
    assertEquals(
        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
        transmision2.getInicio().truncatedTo(ChronoUnit.SECONDS));
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
    Canal canal1 = new Canal();
    Canal canal2 = new Canal();
    Canal canal3 = new Canal();

    new Transmision(canal1, "transmision").serIniciada();
    new Transmision(canal2, "transmision").serIniciada();
    new Transmision(canal3, "transmision").serIniciada();

    assertEquals(3, TransmisionesEnCurso.getInstancia().getTransmisiones().size());
  }

  @Test
  public void unUsuarioPuedeSuscribirseAUnCanal() {
    Canal canal1 = new Canal();
    Canal canal2 = new Canal();

    assertEquals(0, canal1.getSuscriptores().size());

    canal1.recibirSuscripcion(canal2);

    assertEquals(1, canal1.getSuscriptores().size());
  }

  @Test
  public void unUsuarioPuedeDesuscribirseDeUnCanal() {
    Canal canal1 = new Canal();
    Canal canal2 = new Canal();

    canal1.recibirSuscripcion(canal2);

    assertEquals(1, canal1.getSuscriptores().size());

    canal1.perderSuscripcion(canal2);

    assertEquals(0, canal1.getSuscriptores().size());
  }

  @Test
  public void unUsuarioPuedeDarMuestrasDeApoyoAUnCanal() {
    Canal canal = new Canal();

    assertEquals(0, canal.getMuestrasDeApoyo().size());

    canal.recibirApoyo(10);

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
  public void unUsuarioPuedeAbandonarUnaTransmision() {
    Canal canal1 = new Canal();
    Canal canal2 = new Canal();

    Transmision transmision = new Transmision(canal1, "transmision");
    transmision.agregarVisualizador(canal2);

    assertEquals(1, transmision.getEspectadores().size());

    transmision.quitarVisualizador(canal2);

    assertEquals(0, transmision.getEspectadores().size());
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
