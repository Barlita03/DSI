package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
public class Color {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long colorCodigo;

  private String colorDescripcion;
}
