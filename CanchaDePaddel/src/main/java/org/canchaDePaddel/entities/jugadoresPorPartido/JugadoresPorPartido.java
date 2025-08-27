package org.canchaDePaddel.entities.jugadoresPorPartido;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.canchaDePaddel.entities.Jugador;
import org.canchaDePaddel.entities.Paleta;
import org.canchaDePaddel.entities.Partido;

@Getter
@Setter
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
