package org.qmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.qmp.apis.AccuWeatherApi;
import org.qmp.apis.accuweather.ServicioMeteorologicoAccuWeather;
import org.qmp.prendas.Atuendo;
import org.qmp.prendas.Formalidad;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.TipoDePrenda;
import org.qmp.prendas.materiales.Color;
import org.qmp.prendas.materiales.Material;
import org.qmp.prendas.materiales.Trama;
import org.qmp.sugeridores.SugeridorBasico;
import org.qmp.sugeridores.SugeridorPorFormalidad;

public class UsuariosTests {
  SugeridorBasico sugeridorBasico = new SugeridorBasico();
  SugeridorPorFormalidad sugeridorPorFormalidad = new SugeridorPorFormalidad();

  @Test
  void unUsuarioSeCreaSatisfactoriamenteSinPrendas() {
    Usuario usuario = new Usuario(21, sugeridorBasico);

    assertEquals(0, usuario.getPrendas().size());
  }

  @Test
  void unUsuarioSeCreaSatisfactoriamenteConPrendas() {

    Prenda remera =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.INFORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda pantalon =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.INFORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 100));

    Usuario usuario = new Usuario(21, sugeridorBasico);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera);
    guardarropa.agregarPrenda(pantalon);

    assertEquals(2, usuario.getPrendas().size());
  }

  @Test
  void unUsuarioPuedeAdquirirPrendas() {
    Prenda remera =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.INFORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda pantalon =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.INFORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 100));
    Prenda zapatilla =
        new Prenda(
            TipoDePrenda.ZAPATILLA,
            Formalidad.INFORMAL,
            20,
            Material.CUERO,
            Trama.LISA,
            new Color(0, 0, 0));

    Usuario usuario = new Usuario(21, sugeridorBasico);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera);
    guardarropa.agregarPrenda(pantalon);

    assertEquals(2, usuario.getPrendas().size());

    guardarropa.agregarPrenda(zapatilla);

    assertEquals(3, usuario.getPrendas().size());
  }

  @Test
  void unUsuarioPuedeDesecharPrendas() {
    Prenda remera =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.INFORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda pantalon =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.INFORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 100));

    Usuario usuario = new Usuario(21, sugeridorBasico);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera);
    guardarropa.agregarPrenda(pantalon);

    assertEquals(2, usuario.getPrendas().size());

    guardarropa.quitarPrenda(remera);

    assertEquals(1, usuario.getPrendas().size());
  }

  @Test
  void unUsuarioPuedeGenerarSugerencias() {
    Prenda remera1 =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.INFORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda pantalon1 =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.INFORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 100));
    Prenda calzado1 =
        new Prenda(
            TipoDePrenda.ZAPATILLA,
            Formalidad.INFORMAL,
            20,
            Material.CUERO,
            Trama.LISA,
            new Color(0, 0, 100));
    Prenda remera2 =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.FORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(255, 255, 255));
    Prenda pantalon2 =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.FORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda calzado2 =
        new Prenda(
            TipoDePrenda.ZAPATILLA,
            Formalidad.FORMAL,
            20,
            Material.CUERO,
            Trama.LISA,
            new Color(255, 255, 255));

    Usuario usuario = new Usuario(21, sugeridorBasico);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera1);
    guardarropa.agregarPrenda(pantalon1);
    guardarropa.agregarPrenda(calzado1);
    guardarropa.agregarPrenda(remera2);
    guardarropa.agregarPrenda(pantalon2);
    guardarropa.agregarPrenda(calzado2);

    assertEquals(8, usuario.generarSugerencias().size());
  }

  @Test
  void siUnUsuarioEsMenorA55YUsaElSugeridorPorFormalidadSeLeGeneranTodaClaseDeAtuendos() {
    Prenda remera1 =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.INFORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda pantalon1 =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.INFORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 100));
    Prenda calzado1 =
        new Prenda(
            TipoDePrenda.ZAPATILLA,
            Formalidad.INFORMAL,
            20,
            Material.CUERO,
            Trama.LISA,
            new Color(0, 0, 100));
    Prenda remera2 =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.FORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(255, 255, 255));
    Prenda pantalon2 =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.FORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda calzado2 =
        new Prenda(
            TipoDePrenda.ZAPATILLA,
            Formalidad.FORMAL,
            20,
            Material.CUERO,
            Trama.LISA,
            new Color(255, 255, 255));

    Usuario usuario = new Usuario(21, sugeridorPorFormalidad);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera1);
    guardarropa.agregarPrenda(pantalon1);
    guardarropa.agregarPrenda(calzado1);
    guardarropa.agregarPrenda(remera2);
    guardarropa.agregarPrenda(pantalon2);
    guardarropa.agregarPrenda(calzado2);

    assertEquals(8, usuario.generarSugerencias().size());
  }

  @Test
  void siUnUsuarioEsMayorA55YUsaElSugeridorPorFormalidadSoloSeLeGeneranSugerenciasFormales() {
    Prenda remera1 =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.INFORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda pantalon1 =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.INFORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 100));
    Prenda calzado1 =
        new Prenda(
            TipoDePrenda.ZAPATILLA,
            Formalidad.INFORMAL,
            20,
            Material.CUERO,
            Trama.LISA,
            new Color(0, 0, 100));
    Prenda remera2 =
        new Prenda(
            TipoDePrenda.REMERA,
            Formalidad.FORMAL,
            20,
            Material.TELA_ALGODON,
            Trama.LISA,
            new Color(255, 255, 255));
    Prenda pantalon2 =
        new Prenda(
            TipoDePrenda.PANTALON,
            Formalidad.FORMAL,
            20,
            Material.TELA_JEAN,
            Trama.LISA,
            new Color(0, 0, 0));
    Prenda calzado2 =
        new Prenda(
            TipoDePrenda.ZAPATILLA,
            Formalidad.FORMAL,
            20,
            Material.CUERO,
            Trama.LISA,
            new Color(255, 255, 255));

    Usuario usuario = new Usuario(60, sugeridorPorFormalidad);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera1);
    guardarropa.agregarPrenda(pantalon1);
    guardarropa.agregarPrenda(calzado1);
    guardarropa.agregarPrenda(remera2);
    guardarropa.agregarPrenda(pantalon2);
    guardarropa.agregarPrenda(calzado2);

    List<Atuendo> sugerencias = usuario.generarSugerencias();

    assertEquals(1, sugerencias.size());

    assertTrue(sugerencias.stream().allMatch(Atuendo::esFormal));
  }

  @Test
  void condicionClimatica() {
    ServicioMeteorologicoAccuWeather servicioMeteorologicoAccuWeather =
        new ServicioMeteorologicoAccuWeather(new AccuWeatherApi(), Duration.ofDays(1));

    assertEquals(
        13, servicioMeteorologicoAccuWeather.getTemperaturaEnCelsius("Buenos Aires, Argentina"));
  }
}
