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
@Table(name = "canchas")
public class Cancha {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long canchaId;

  @ManyToOne
  private Color canchaColor;

  private String canchaNombre;
  private boolean canchaIluminacion;
}
