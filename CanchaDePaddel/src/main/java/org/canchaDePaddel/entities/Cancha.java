package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cancha {
  private long canchaId;
  private long canchaColor;
  private String canchaNombre;
  private boolean canchaIluminacion;
}
