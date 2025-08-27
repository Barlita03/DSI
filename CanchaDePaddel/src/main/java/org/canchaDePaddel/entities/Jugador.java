package org.canchaDePaddel.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jugadores")
public class Jugador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long jugadorId;

  private String jugadorNombre;
  private String jugadorApellido;
  private String jugadorDomicilio;
  private LocalDate jugadorNacimiento;
}
