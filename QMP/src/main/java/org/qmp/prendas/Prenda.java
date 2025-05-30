package org.qmp.prendas;

import org.qmp.materiales.Color;
import org.qmp.materiales.Material;
import org.qmp.materiales.Trama;

public class Prenda {
  private final TipoDePrenda tipoDePrenda;
  private final Formalidad formalidad;
  private Material material;
  private Trama trama = Trama.LISA;
  private Color colorPrincipal;
  private Color colorSecundario;

  // Constructor
  public Prenda(TipoDePrenda tipoDePrenda,
                Formalidad formalidad,
                Material material,
                Trama trama,
                Color colorPrincipal,
                Color colorSecundario) {
    this.tipoDePrenda = tipoDePrenda;
    this.formalidad = formalidad;
    this.material = material;
    this.trama = trama;
    this.colorPrincipal = colorPrincipal;
    this.colorSecundario = colorSecundario;
  }

  public Prenda(TipoDePrenda tipoDePrenda,
                Formalidad formalidad,
                Material material,
                Trama trama,
                Color colorPrincipal) {
    this.tipoDePrenda = tipoDePrenda;
    this.formalidad = formalidad;
    this.material = material;
    this.trama = trama;
    this.colorPrincipal = colorPrincipal;
  }

  // Getters
  public Categoria categoria() {
    return this.tipoDePrenda.getCategoria();
  }

  public TipoDePrenda getTipo() {
    return this.tipoDePrenda;
  }

  public Material getMaterial() {
    return this.material;
  }

  public Trama getTrama() {
    return this.trama;
  }

  public Color getColorPrincipal() {
    return this.colorPrincipal;
  }

  public Color getColorSecundario() {
    return this.colorSecundario;
  }
}
