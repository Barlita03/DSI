package org.qmp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.qmp.prendas.Atuendo;
import org.qmp.prendas.Formalidad;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.TipoDePrenda;
import org.qmp.prendas.materiales.Color;
import org.qmp.prendas.materiales.Material;
import org.qmp.prendas.materiales.Trama;
import org.qmp.sugeridores.SugeridorBasico;
import org.qmp.sugeridores.SugeridorPorFormalidad;
import org.qmp.usuarios.GestorDeUsuarios;
import org.qmp.usuarios.Usuario;

public class UsuariosTests {
  SugeridorBasico sugeridorBasico = new SugeridorBasico();
  SugeridorPorFormalidad sugeridorPorFormalidad = new SugeridorPorFormalidad();
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

  @BeforeEach
  void setup() {
    GestorDeUsuarios.limpiarLista();
  }

  @Test
  void unUsuarioPuedeAdquirirPrendas() {
    Usuario usuario = new Usuario(21, "email", sugeridorBasico);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera1);
    guardarropa.agregarPrenda(pantalon1);

    assertEquals(2, usuario.getPrendas().size());

    guardarropa.agregarPrenda(calzado1);

    assertEquals(3, usuario.getPrendas().size());
  }

  @Test
  void unUsuarioPuedeDesecharPrendas() {
    Usuario usuario = new Usuario(21, "email", sugeridorBasico);
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(usuario);
    Guardarropa guardarropa = new Guardarropa("criterio", usuarios);
    guardarropa.agregarPrenda(remera1);
    guardarropa.agregarPrenda(pantalon1);

    assertEquals(2, usuario.getPrendas().size());

    guardarropa.quitarPrenda(remera1);

    assertEquals(1, usuario.getPrendas().size());
  }

  @Test
  void unUsuarioPuedeGenerarSugerencias() {
    Usuario usuario = new Usuario(21, "email", sugeridorBasico);
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
    Usuario usuario = new Usuario(21, "email", sugeridorPorFormalidad);
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
    Usuario usuario = new Usuario(60, "email", sugeridorPorFormalidad);
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
  void unUsuarioPuedeTenerVariosGuardarropas() {
    Usuario usuario = new Usuario(21, "email", sugeridorBasico);
    new Guardarropa("Criterio", List.of(usuario));
    new Guardarropa("Criterio", List.of(usuario));

    assertEquals(2, usuario.getGuardarropas().size());
  }

  @Test
  void unEmpleadoPuedeDispararLasSugerenciasParaTodosLosUsuarios() {
    Usuario usuario1 = new Usuario(21, "email", sugeridorBasico);
    Usuario usuario2 = new Usuario(21, "email", sugeridorBasico);

    Guardarropa guardarropa = new Guardarropa("Criterio", List.of(usuario1, usuario2));
    guardarropa.agregarPrenda(remera1);
    guardarropa.agregarPrenda(pantalon1);
    guardarropa.agregarPrenda(calzado1);

    assertEquals(0, usuario1.getSugerenciasDiarias().size());
    assertEquals(0, usuario2.getSugerenciasDiarias().size());

    GestorDeUsuarios.actualizarSugerenciasDiarias();

    assertEquals(1, usuario1.getSugerenciasDiarias().size());
    assertEquals(1, usuario2.getSugerenciasDiarias().size());
  }
}
