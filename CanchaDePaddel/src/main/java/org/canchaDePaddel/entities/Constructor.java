package org.canchaDePaddel.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "constructores")
public class Constructor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long constructorCodigo;

  private String constructorNombre;
  private String constructorDomicilio;
}
