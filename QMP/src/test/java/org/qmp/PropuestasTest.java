package org.qmp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.qmp.apis.AccuWeatherApi;
import org.qmp.prendas.Formalidad;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.TipoDePrenda;
import org.qmp.prendas.materiales.Color;
import org.qmp.prendas.materiales.Material;
import org.qmp.prendas.materiales.Trama;
import org.qmp.propuestas.AgregarPrenda;
import org.qmp.propuestas.Propuesta;
import org.qmp.propuestas.QuitarPrenda;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;
import org.qmp.serviciosmeteorologicos.accuweather.ServicioMeteorologicoAccuWeather;
import org.qmp.sugeridores.Sugeridor;
import org.qmp.sugeridores.SugeridorBasico;
import org.qmp.usuarios.Usuario;

public class PropuestasTest {
  ServicioMeteorologico servicioMeteorologico =
      new ServicioMeteorologicoAccuWeather(new AccuWeatherApi(), Duration.ofHours(3));
  AsesorDeImagen asesor = new AsesorDeImagen(servicioMeteorologico);
  Sugeridor sugeridor = new SugeridorBasico();
  Usuario usuario = new Usuario(21, "email", sugeridor, asesor);

  Prenda prenda1 =
      new Prenda(
          TipoDePrenda.REMERA,
          Formalidad.INFORMAL,
          20,
          Material.TELA_ALGODON,
          Trama.LISA,
          new Color(0, 0, 0));
  Prenda prenda2 =
      new Prenda(
          TipoDePrenda.REMERA,
          Formalidad.INFORMAL,
          20,
          Material.TELA_ALGODON,
          Trama.LISA,
          new Color(0, 0, 0));

  Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario));

  @BeforeEach
  void setup() {
    guardarropa.limpiarListaPrendas();
    guardarropa.limpiarListaPropuestas();

    guardarropa.agregarPrenda(prenda1);
  }

  @Test
  void unUsuarioPuedeCrearPropuestas() {
    new QuitarPrenda(guardarropa, prenda1);

    assertEquals(1, guardarropa.getPropuestasPendientes().size());
  }

  @Test
  void unUsuarioPuedeListarLasPropuestas() {
    new QuitarPrenda(guardarropa, prenda1);

    assertEquals(1, usuario.getPropuestasPendientes().size());
  }

  @Test
  void unUsuarioPuedeListarLasOperaciones() {
    Propuesta propuesta = new QuitarPrenda(guardarropa, prenda1);

    propuesta.serAceptada();

    assertEquals(0, usuario.getPropuestasPendientes().size());
    assertEquals(1, usuario.getPropuestasProcesadas().size());
  }

  @Test
  void sePuedeAceptarLaPropuestaDeQuitarUnaPrenda() {
    Propuesta propuesta = new QuitarPrenda(guardarropa, prenda1);

    assertEquals(1, guardarropa.getPrendas().size());

    propuesta.serAceptada();

    assertEquals(0, guardarropa.getPrendas().size());
  }

  @Test
  void sePuedeDeshacerLaPropuetaDeQuitarUnaPrenda() {
    Propuesta propuesta = new QuitarPrenda(guardarropa, prenda1);

    propuesta.serAceptada();

    assertEquals(0, guardarropa.getPrendas().size());

    propuesta.deshacer();

    assertEquals(1, guardarropa.getPrendas().size());
  }

  @Test
  void sePuedeAceptarLaPropuestaDeAgregarUnaPrenda() {
    Propuesta propuesta = new AgregarPrenda(guardarropa, prenda1);

    assertEquals(1, guardarropa.getPrendas().size());

    propuesta.serAceptada();

    assertEquals(2, guardarropa.getPrendas().size());
  }

  @Test
  void sePuedeDeshacerLaPropuetaDeAgregarUnaPrenda() {
    Propuesta propuesta = new AgregarPrenda(guardarropa, prenda1);

    propuesta.serAceptada();

    assertEquals(2, guardarropa.getPrendas().size());

    propuesta.deshacer();

    assertEquals(1, guardarropa.getPrendas().size());
  }
}
