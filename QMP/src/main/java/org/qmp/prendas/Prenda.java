package org.qmp.prendas;

public class Prenda {
  private final TipoDePrenda tipoDePrenda;
  private Material material;
  private Trama trama = Trama.LISA;
  private Color colorPrincipal;
  private Color colorSecundario;

  // Constructor
  public Prenda(TipoDePrenda tipoDePrenda,
                 Material material,
                 Trama trama,
                 Color colorPrincipal,
                 Color colorSecundario) {
    this.tipoDePrenda = tipoDePrenda;
    this.material = material;
    this.trama = trama;
    this.colorPrincipal = colorPrincipal;
    this.colorSecundario = colorSecundario;
  }

  public Prenda(TipoDePrenda tipoDePrenda,
                Material material,
                Trama trama,
                Color colorPrincipal) {
    this.tipoDePrenda = tipoDePrenda;
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
