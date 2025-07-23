package org.qmp.prendas;

import org.qmp.exceptions.InvalidPrenda;
import org.qmp.prendas.atributos.Formalidad;
import org.qmp.prendas.atributos.TipoDePrenda;
import org.qmp.prendas.materiales.Color;
import org.qmp.prendas.materiales.Material;
import org.qmp.prendas.materiales.Trama;

public class Borrador {
  private final TipoDePrenda tipoDePrenda;
  private Formalidad formalidad = null;
  private int temperaturaMaxima;
  private Material material = null;
  private Trama trama = Trama.LISA;
  private Color colorPrincipal = null;
  private Color colorSecundario = null;

  public Borrador(TipoDePrenda tipoDePrenda) {
    this.tipoDePrenda = tipoDePrenda;
  }

  // Setters
  public void setFormalidad(Formalidad formalidad) {
    this.formalidad = formalidad;
  }

  public void setTemperaturaMaxima(int temperaturaMaxima) {
    this.temperaturaMaxima = temperaturaMaxima;
  }

  public void setMaterial(Material material) {
    this.verificarParametroNoNulo(material);
    this.material = material;
  }

  public void setTrama(Trama trama) {
    this.verificarParametroNoNulo(material);
    this.trama = trama;
  }

  public void setColorPrincipal(Color color) {
    this.verificarParametroNoNulo(material);
    this.colorPrincipal = color;
  }

  public void setColorSecundario(Color color) {
    this.verificarParametroNoNulo(material);
    this.colorSecundario = color;
  }

  // Validaciones
  public boolean prendaValida() {
    return this.material != null && this.colorPrincipal != null && this.trama != null;
  }

  public void verificarSiLaPrendaEsValida() {
    if (!this.prendaValida()) {
      throw new InvalidPrenda("Esta prenda no tiene completo alguno/s de sus atributos");
    }
  }

  public void verificarParametroNoNulo(Object object) {
    if (object == null) {
      throw new NullPointerException("Los atributos no pueden ser nulos");
    }
  }

  // Otros
  public Prenda crearPrenda() {
    verificarSiLaPrendaEsValida();
    return new Prenda(
        tipoDePrenda,
        formalidad,
        temperaturaMaxima,
        material,
        trama,
        colorPrincipal,
        colorSecundario);
  }
}
