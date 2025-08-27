package org.canchaDePaddel.entities.jugadoresPorPartido;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JugadoresPorPartidoId implements Serializable {
  private Long jugador;
  private Long partido;
}
