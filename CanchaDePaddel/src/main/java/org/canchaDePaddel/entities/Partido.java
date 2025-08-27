package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "partidos")
public class Partido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long partidoId;

  @ManyToOne
  private Cancha partidoCancha;

  @ManyToOne
  private Jugador partidoReservador;

  private LocalDateTime partidoInicio;
  private LocalDateTime partidoFin;
}
