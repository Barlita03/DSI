package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Paleta {
  private long paletaId;
  private long paletaConstructor;
  private long paletaColor;
  private String paletaNombre;
  private float paletaGrosor;
}
