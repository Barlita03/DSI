package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Jugador {
  private long jugadorId;
  private String jugadorNombre;
  private String jugadorApellido;
  private String jugadorDomicilio;
  private LocalDate jugadorNacimiento;
}