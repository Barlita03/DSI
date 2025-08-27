package org.canchaDePaddel.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "paletas")
public class Paleta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long paletaId;

  @ManyToOne
  private Constructor paletaConstructor;

  @ManyToOne
  private Color paletaColor;

  private String paletaNombre;
  private float paletaGrosor;
}
