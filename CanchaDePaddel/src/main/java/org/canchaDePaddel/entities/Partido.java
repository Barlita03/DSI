package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Partido {
  private long partidoId;
  private long partidoCancha;
  private LocalDateTime partidoInicio;
  private LocalDateTime partidoFin;
  private long partidoReservador;
}
