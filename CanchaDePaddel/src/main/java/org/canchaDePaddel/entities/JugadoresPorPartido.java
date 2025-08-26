package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JugadoresPorPartido {
  private long jugadorId;
  private long partidoId;
  private long paletaId;
}
