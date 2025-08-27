package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jugadoresPorPartido")
@IdClass(JugadoresPorPartidoId.class)
public class JugadoresPorPartido {

  @Id
  @ManyToOne
  @JoinColumn(name = "jugador_id")
  private Jugador jugador;

  @Id
  @ManyToOne
  @JoinColumn(name = "partido_id")
  private Partido partido;

  @ManyToOne
  @JoinColumn(name = "paleta_id")
  private Paleta paleta;
}
