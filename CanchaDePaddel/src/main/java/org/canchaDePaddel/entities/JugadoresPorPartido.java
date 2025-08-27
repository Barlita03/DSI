package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "jugadoresPorPartido")
public class JugadoresPorPartido {

  @ManyToOne
  private Jugador jugadorId;

  @ManyToOne
  private Partido partidoId;

  private long paletaId;
}
