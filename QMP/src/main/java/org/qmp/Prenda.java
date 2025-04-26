package org.qmp;

import org.qmp.exceptions.NullParamException;

public class Prenda {
  private TipoDePrenda tipoDePrenda;
  private Material material;
  private Color colorPrincipal;
  private Color colorSecundario = null;

  public Prenda(TipoDePrenda tipoDePrenda,
                 Material material,
                 Color colorPrincipal,
                 Color colorSecundario) {
    this.tipoDePrenda = tipoDePrenda;
    this.material = material;
    this.colorPrincipal = colorPrincipal;
    this.colorSecundario = colorSecundario;

    this.verificarTipos();
  }

  public Prenda(TipoDePrenda tipoDePrenda,
                 Material material,
                 Color colorPrincipal) {
    this.tipoDePrenda = tipoDePrenda;
    this.material = material;
    this.colorPrincipal = colorPrincipal;

    this.verificarTipos();
  }

  private void verificarTipos() {
    if (this.tipoDePrenda == null) {
      throw new NullParamException("El tipo de prenda no puede ser nulo");
    } else if (this.material == null) {
      throw new NullParamException("El material no puede ser nulo");
    } else if (this.colorPrincipal == null) {
      throw new NullParamException("El color principal no puede ser nulo");
    }
  }

  public Categoria categoria() {
    return this.tipoDePrenda.getCategoria();
  }

  public TipoDePrenda getTipo() {
    return this.tipoDePrenda;
  }

  public Color getColorPrincipal() {
    return this.colorPrincipal;
  }

  public Color getColorSecundario() {
    return this.colorSecundario;
  }
}
