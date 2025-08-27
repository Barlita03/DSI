package org.canchaDePaddel;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.canchaDePaddel.entities.Cancha;
import org.canchaDePaddel.entities.Jugador;
import org.canchaDePaddel.entities.Paleta;
import org.canchaDePaddel.entities.Partido;
import org.canchaDePaddel.entities.jugadoresPorPartido.JugadoresPorPartido;
import org.junit.jupiter.api.Test;

public class PaddelTests implements SimplePersistenceTest {

  @Test
  void altaJugador() {
    Jugador jugador = new Jugador();
    jugador.setJugadorNombre("Juan");
    jugador.setJugadorApellido("Perez");
    jugador.setJugadorDomicilio("Calle Falsa 123");
    jugador.setJugadorNacimiento(LocalDate.of(2000, 1, 1));
    entityManager().persist(jugador);
    assert jugador.getJugadorId() >= 0;
  }

  @Test
  void altaCancha() {
    var cancha = new Cancha();
    cancha.setCanchaNombre("Cancha 1");
    cancha.setCanchaIluminacion(true);
    entityManager().persist(cancha);
    assert cancha.getCanchaId() >= 0;
  }

  @Test
  void altaPaleta() {
    var paleta = new Paleta();
    paleta.setPaletaNombre("Paleta Head");
    paleta.setPaletaGrosor(3.5f);
    entityManager().persist(paleta);
    assert paleta.getPaletaId() >= 0;
  }

  @Test
  void altaPartidoYAsignarJugadores() {
    var cancha = new Cancha();
    cancha.setCanchaNombre("Cancha 2");
    cancha.setCanchaIluminacion(false);
    entityManager().persist(cancha);

    var jugador1 = new Jugador();
    jugador1.setJugadorNombre("Pedro");
    jugador1.setJugadorApellido("Gomez");
    jugador1.setJugadorDomicilio("Calle 1");
    jugador1.setJugadorNacimiento(LocalDate.of(1999, 5, 10));
    entityManager().persist(jugador1);

    var jugador2 = new Jugador();
    jugador2.setJugadorNombre("Luis");
    jugador2.setJugadorApellido("Lopez");
    jugador2.setJugadorDomicilio("Calle 2");
    jugador2.setJugadorNacimiento(LocalDate.of(1998, 8, 20));
    entityManager().persist(jugador2);

    var partido = new Partido();
    partido.setPartidoCancha(cancha);
    partido.setPartidoReservador(jugador1);
    partido.setPartidoInicio(LocalDateTime.now());
    partido.setPartidoFin(LocalDateTime.now().plusHours(2));
    entityManager().persist(partido);

    var paleta1 = new Paleta();
    paleta1.setPaletaNombre("Paleta 1");
    paleta1.setPaletaGrosor(3.0f);
    entityManager().persist(paleta1);

    var paleta2 = new Paleta();
    paleta2.setPaletaNombre("Paleta 2");
    paleta2.setPaletaGrosor(3.1f);
    entityManager().persist(paleta2);

    var jpp1 = new JugadoresPorPartido();
    jpp1.setJugador(jugador1);
    jpp1.setPartido(partido);
    jpp1.setPaleta(paleta1);
    entityManager().persist(jpp1);

    var jpp2 = new JugadoresPorPartido();
    jpp2.setJugador(jugador2);
    jpp2.setPartido(partido);
    jpp2.setPaleta(paleta2);
    entityManager().persist(jpp2);

    assert partido.getPartidoId() >= 0;
  }

  @Test
  void consultarPartidosPorJugador() {
    var jugador = new Jugador();
    jugador.setJugadorNombre("Ana");
    jugador.setJugadorApellido("Martinez");
    jugador.setJugadorDomicilio("Calle 3");
    jugador.setJugadorNacimiento(LocalDate.of(2001, 2, 2));
    entityManager().persist(jugador);

    var cancha = new Cancha();
    cancha.setCanchaNombre("Cancha 3");
    cancha.setCanchaIluminacion(true);
    entityManager().persist(cancha);

    var partido = new Partido();
    partido.setPartidoCancha(cancha);
    partido.setPartidoReservador(jugador);
    partido.setPartidoInicio(LocalDateTime.now());
    partido.setPartidoFin(LocalDateTime.now().plusHours(1));
    entityManager().persist(partido);

    var paleta = new Paleta();
    paleta.setPaletaNombre("Paleta Ana");
    paleta.setPaletaGrosor(3.2f);
    entityManager().persist(paleta);

    var jpp = new JugadoresPorPartido();
    jpp.setJugador(jugador);
    jpp.setPartido(partido);
    jpp.setPaleta(paleta);
    entityManager().persist(jpp);

    var partidos =
        entityManager()
            .createQuery(
                "SELECT jpp.partido FROM JugadoresPorPartido jpp WHERE jpp.jugador.jugadorId = :jugadorId",
                Partido.class)
            .setParameter("jugadorId", jugador.getJugadorId())
            .getResultList();
    assert !partidos.isEmpty();
  }

  @Test
  void consultarJugadoresPorPartido() {
    var jugador = new Jugador();
    jugador.setJugadorNombre("Sofia");
    jugador.setJugadorApellido("Diaz");
    jugador.setJugadorDomicilio("Calle 4");
    jugador.setJugadorNacimiento(LocalDate.of(2002, 3, 3));
    entityManager().persist(jugador);

    var cancha = new Cancha();
    cancha.setCanchaNombre("Cancha 4");
    cancha.setCanchaIluminacion(false);
    entityManager().persist(cancha);

    var partido = new Partido();
    partido.setPartidoCancha(cancha);
    partido.setPartidoReservador(jugador);
    partido.setPartidoInicio(LocalDateTime.now());
    partido.setPartidoFin(LocalDateTime.now().plusHours(1));
    entityManager().persist(partido);

    var paleta = new Paleta();
    paleta.setPaletaNombre("Paleta Sofia");
    paleta.setPaletaGrosor(3.3f);
    entityManager().persist(paleta);

    var jpp = new JugadoresPorPartido();
    jpp.setJugador(jugador);
    jpp.setPartido(partido);
    jpp.setPaleta(paleta);
    entityManager().persist(jpp);

    var jugadores =
        entityManager()
            .createQuery(
                "SELECT jpp.jugador FROM JugadoresPorPartido jpp WHERE jpp.partido.partidoId = :partidoId",
                Jugador.class)
            .setParameter("partidoId", partido.getPartidoId())
            .getResultList();
    assert !jugadores.isEmpty();
  }

  @Test
  void consultarPartidosPorCancha() {
    var cancha = new Cancha();
    cancha.setCanchaNombre("Cancha 5");
    cancha.setCanchaIluminacion(true);
    entityManager().persist(cancha);

    var jugador = new Jugador();
    jugador.setJugadorNombre("Mario");
    jugador.setJugadorApellido("Ruiz");
    jugador.setJugadorDomicilio("Calle 5");
    jugador.setJugadorNacimiento(LocalDate.of(2003, 4, 4));
    entityManager().persist(jugador);

    var partido = new Partido();
    partido.setPartidoCancha(cancha);
    partido.setPartidoReservador(jugador);
    partido.setPartidoInicio(LocalDateTime.now());
    partido.setPartidoFin(LocalDateTime.now().plusHours(1));
    entityManager().persist(partido);

    var partidos =
        entityManager()
            .createQuery(
                "SELECT p FROM Partido p WHERE p.partidoCancha.canchaId = :canchaId", Partido.class)
            .setParameter("canchaId", cancha.getCanchaId())
            .getResultList();
    assert !partidos.isEmpty();
  }
}
