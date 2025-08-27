package org.canchaDePaddel;

import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.canchaDePaddel.entities.Cancha;
import org.canchaDePaddel.entities.Jugador;
import org.canchaDePaddel.entities.jugadoresPorPartido.JugadoresPorPartido;
import org.canchaDePaddel.entities.Paleta;
import org.canchaDePaddel.entities.Partido;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaddelTests implements SimplePersistenceTest {

	@Test
	void altaJugador() {
		var jugador = new Jugador(1L, "Juan", "Perez", "Calle Falsa 123", LocalDate.of(2000, 1, 1));
		persist(jugador);
		assert jugador.getJugadorId() > 0;
	}

	@Test
	void altaCancha() {
		var cancha = new Cancha(1L, null, "Cancha 1", true);
		persist(cancha);
		assert cancha.getCanchaId() > 0;
	}

	@Test
	void altaPaleta() {
		var paleta = new Paleta(1L, null, null, "Paleta Head", 3.5f);
		persist(paleta);
		assert paleta.getPaletaId() > 0;
	}

	@Test
	void altaPartidoYAsignarJugadores() {
		var cancha = new Cancha(2L, null, "Cancha 2", false);
		persist(cancha);

		var jugador1 = new Jugador(2L, "Pedro", "Gomez", "Calle 1", LocalDate.of(1999, 5, 10));
		persist(jugador1);
		var jugador2 = new Jugador(3L, "Luis", "Lopez", "Calle 2", LocalDate.of(1998, 8, 20));
		persist(jugador2);

		var partido = new Partido(1L, cancha, jugador1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
		persist(partido);

		var paleta1 = new Paleta(10L, null, null, "Paleta 1", 3.0f);
		var paleta2 = new Paleta(11L, null, null, "Paleta 2", 3.1f);
		persist(paleta1);
		persist(paleta2);

		var jpp1 = new JugadoresPorPartido(jugador1, partido, paleta1);
		persist(jpp1);

		var jpp2 = new JugadoresPorPartido(jugador2, partido, paleta2);
		persist(jpp2);

		assert partido.getPartidoId() > 0;
	}

	@Test
	void consultarPartidosPorJugador() {
		var jugador = new Jugador(4L, "Ana", "Martinez", "Calle 3", LocalDate.of(2001, 2, 2));
		persist(jugador);
		var cancha = new Cancha(3L, null, "Cancha 3", true);
		persist(cancha);
		var partido = new Partido(2L, cancha, jugador, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		persist(partido);
		var paleta = new Paleta(12L, null, null, "Paleta Ana", 3.2f);
		persist(paleta);
		var jpp = new JugadoresPorPartido(jugador, partido, paleta);
		persist(jpp);

		var partidos = entityManager()
			.createQuery("SELECT jpp.partido FROM JugadoresPorPartido jpp WHERE jpp.jugador.jugadorId = :jugadorId", Partido.class)
			.setParameter("jugadorId", jugador.getJugadorId())
			.getResultList();
		assert !partidos.isEmpty();
	}

	@Test
	void consultarJugadoresPorPartido() {
		var jugador = new Jugador(5L, "Sofia", "Diaz", "Calle 4", LocalDate.of(2002, 3, 3));
		persist(jugador);
		var cancha = new Cancha(4L, null, "Cancha 4", false);
		persist(cancha);
		var partido = new Partido(3L, cancha, jugador, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		persist(partido);
		var paleta = new Paleta(13L, null, null, "Paleta Sofia", 3.3f);
		persist(paleta);
		var jpp = new JugadoresPorPartido(jugador, partido, paleta);
		persist(jpp);

		var jugadores = entityManager()
			.createQuery("SELECT jpp.jugador FROM JugadoresPorPartido jpp WHERE jpp.partido.partidoId = :partidoId", Jugador.class)
			.setParameter("partidoId", partido.getPartidoId())
			.getResultList();
		assert !jugadores.isEmpty();
	}

	@Test
	void consultarPartidosPorCancha() {
		var cancha = new Cancha(5L, null, "Cancha 5", true);
		persist(cancha);
		var jugador = new Jugador(6L, "Mario", "Ruiz", "Calle 5", LocalDate.of(2003, 4, 4));
		persist(jugador);
		var partido = new Partido(4L, cancha, jugador, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
		persist(partido);

		var partidos = entityManager()
			.createQuery("SELECT p FROM Partido p WHERE p.partidoCancha.canchaId = :canchaId", Partido.class)
			.setParameter("canchaId", cancha.getCanchaId())
			.getResultList();
		assert !partidos.isEmpty();
	}

	// NOTA: No hay relación directa entre Jugador y Paleta en tu modelo, por lo que este test es solo ilustrativo.
	@Test
	void consultarPaletasPorJugador() {
		var paleta = new Paleta(2L, null, null, "Paleta Babolat", 3.2f);
		persist(paleta);
		// Aquí deberías tener una relación entre Jugador y Paleta para que este test tenga sentido.
		// Por ahora, solo se persiste la paleta y se consulta por nombre.
		var paletas = entityManager()
			.createQuery("SELECT p FROM Paleta p WHERE p.paletaNombre = :nombre", Paleta.class)
			.setParameter("nombre", "Paleta Babolat")
			.getResultList();
		assert !paletas.isEmpty();
	}
}
