package org.canchaDePaddel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import org.canchaDePaddel.entities.*;
import org.canchaDePaddel.entities.jugadoresPorPartido.JugadoresPorPartido;

public class DemoPersistencia {
  public static void main(String[] args) {
    EntityManagerFactory emf =
        Persistence.createEntityManagerFactory(
            "simple-persistence-unit"); // Debe coincidir con el nombre en persistence.xml
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // Crear entidades
      Jugador jugador = new Jugador();
      jugador.setJugadorNombre("Juan");
      jugador.setJugadorApellido("Perez");
      jugador.setJugadorDomicilio("Calle Falsa 123");
      jugador.setJugadorNacimiento(LocalDate.of(2000, 1, 1));
      em.persist(jugador);

      Cancha cancha = new Cancha();
      cancha.setCanchaNombre("Cancha 1");
      cancha.setCanchaIluminacion(true);
      em.persist(cancha);

      Paleta paleta = new Paleta();
      paleta.setPaletaNombre("Paleta Head");
      paleta.setPaletaGrosor(3.5f);
      em.persist(paleta);

      Partido partido = new Partido();
      partido.setPartidoCancha(cancha);
      partido.setPartidoReservador(jugador);
      partido.setPartidoInicio(LocalDateTime.now());
      partido.setPartidoFin(LocalDateTime.now().plusHours(2));
      em.persist(partido);

      JugadoresPorPartido jpp = new JugadoresPorPartido();
      jpp.setJugador(jugador);
      jpp.setPartido(partido);
      jpp.setPaleta(paleta);
      em.persist(jpp);

      tx.commit();
      System.out.println("Datos persistidos correctamente.");
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
      emf.close();
    }
  }
}
