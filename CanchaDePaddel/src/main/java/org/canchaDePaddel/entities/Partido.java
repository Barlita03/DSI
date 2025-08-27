package org.canchaDePaddel.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partidos")
public class Partido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long partidoId;

  @ManyToOne private Cancha partidoCancha;

  @ManyToOne private Jugador partidoReservador;

  private LocalDateTime partidoInicio;
  private LocalDateTime partidoFin;
}
