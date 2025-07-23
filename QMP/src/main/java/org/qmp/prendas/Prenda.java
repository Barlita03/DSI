package org.qmp.prendas;

import org.qmp.prendas.materiales.Color;
import org.qmp.prendas.materiales.Material;
import org.qmp.prendas.materiales.Trama;

public class Prenda {
  private final TipoDePrenda tipoDePrenda;
  private final Formalidad formalidad;
  private final int temperaturaMaxima;
  private final Material material;
  private Trama trama = Trama.LISA;
  private final Color colorPrincipal;
  private Color colorSecundario;

  // --- Constructor ---
  public Prenda(
      TipoDePrenda tipoDePrenda,
      Formalidad formalidad,
      int temperaturaMaxima,
      Material material,
      Trama trama,
      Color colorPrincipal,
      Color colorSecundario) {
    this.tipoDePrenda = tipoDePrenda;
    this.formalidad = formalidad;
    this.temperaturaMaxima = temperaturaMaxima;
    this.material = material;
    this.trama = trama;
    this.colorPrincipal = colorPrincipal;
    this.colorSecundario = colorSecundario;
  }

  public Prenda(
      TipoDePrenda tipoDePrenda,
      Formalidad formalidad,
      int temperaturaMaxima,
      Material material,
      Trama trama,
      Color colorPrincipal) {
    this.tipoDePrenda = tipoDePrenda;
    this.formalidad = formalidad;
    this.temperaturaMaxima = temperaturaMaxima;
    this.material = material;
    this.trama = trama;
    this.colorPrincipal = colorPrincipal;
  }

  // --- Getters ---

  public Categoria categoria() {
    return this.tipoDePrenda.getCategoria();
  }

  public TipoDePrenda getTipo() {
    return this.tipoDePrenda;
  }

  public Formalidad getFormalidad() {
    return this.formalidad;
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

  // --- Metodos ---

  public boolean esSuperior() {
    return this.tipoDePrenda.getCategoria() == Categoria.PARTE_SUPERIOR;
  }

  public boolean esInferior() {
    return this.tipoDePrenda.getCategoria() == Categoria.PARTE_INFERIOR;
  }

  public boolean esCalzado() {
    return this.tipoDePrenda.getCategoria() == Categoria.CALZADO;
  }

  public boolean esAccesorio() {
    return this.tipoDePrenda.getCategoria() == Categoria.ACCESORIO;
  }

  public boolean aptaParaTemperatura(int temperatura) {
    return temperatura <= temperaturaMaxima;
  }
}
