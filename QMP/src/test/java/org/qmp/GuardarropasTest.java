package org.qmp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.qmp.apis.AccuWeatherApi;
import org.qmp.prendas.AsesorDeImagen;
import org.qmp.prendas.Guardarropa;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;
import org.qmp.serviciosmeteorologicos.accuweather.ServicioMeteorologicoAccuWeather;
import org.qmp.sugeridores.Sugeridor;
import org.qmp.sugeridores.SugeridorBasico;
import org.qmp.usuarios.Usuario;

public class GuardarropasTest {
  ServicioMeteorologico servicioMeteorologico =
      new ServicioMeteorologicoAccuWeather(
          new AccuWeatherApi(), Duration.ofHours(3), "Buenos Aires");
  AsesorDeImagen asesor = new AsesorDeImagen(servicioMeteorologico);
  Sugeridor sugeridor = new SugeridorBasico();
  Usuario usuario1 = new Usuario(21, "email", sugeridor, asesor);
  Usuario usuario2 = new Usuario(21, "email", sugeridor, asesor);

  @Test
  public void unGuardarropasPuedeSerCompartidoEntreVariosUsuarios() {
    Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario1, usuario2));

    assertTrue(
        usuario1.getGuardarropas().contains(guardarropa)
            && usuario2.getGuardarropas().contains(guardarropa));
  }

  @Test
  public void sePuedeAgregarAUnUsuarioAlGuardarropa() {
    Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario1));

    assertTrue(
        usuario1.getGuardarropas().contains(guardarropa)
            && !usuario2.getGuardarropas().contains(guardarropa));

    guardarropa.agregarUsuario(usuario2);

    assertTrue(
        usuario1.getGuardarropas().contains(guardarropa)
            && usuario2.getGuardarropas().contains(guardarropa));
  }

  @Test
  public void sePuedeQuitarAUnUsuarioAlGuardarropa() {
    Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario1, usuario2));

    assertTrue(
        usuario1.getGuardarropas().contains(guardarropa)
            && usuario2.getGuardarropas().contains(guardarropa));

    guardarropa.quitarUsuario(usuario2);

    assertTrue(
        usuario1.getGuardarropas().contains(guardarropa)
            && !usuario2.getGuardarropas().contains(guardarropa));
  }
}
