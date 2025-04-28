package org.qmp;

import org.qmp.exceptions.InvalidPrenda;

public class Borrador {
  private final TipoDePrenda tipoDePrenda;
  private Material material = null;
  private Trama trama = Trama.LISA;
  private Color colorPrincipal = null;
  private Color colorSecundario = null;

  public Borrador(TipoDePrenda tipoDePrenda) {
    this.tipoDePrenda = tipoDePrenda;
  }

  // Setters
  public void setMaterial(Material material) {
    this.material = material;
  }

  public void setTrama(Trama trama) {
    this.trama = trama;
  }

  public void setColorPrincipal(Color color) {
    this.colorPrincipal = color;
  }

  public void setColorSecundario(Color color) {
    this.colorSecundario = color;
  }

  // Validaciones
  public boolean prendaValida() {
    return this.material != null
           && this.colorPrincipal != null
           && this.trama != null;
  }

  public void verificarSiLaPrendaEsValida() {
    if (!this.prendaValida()) {
      throw new InvalidPrenda("Esta prenda no tiene completo alguno/s de sus atributos");
    }
  }

  // Otros
  public Prenda crearPrenda() {
    verificarSiLaPrendaEsValida();
    return new Prenda(tipoDePrenda, material, trama, colorPrincipal, colorSecundario);
  }
}
