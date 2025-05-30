package org.qmp.materiales;

public class Material {
  private final TipoDeMaterial tipoDeMaterial;

  public Material(TipoDeMaterial tipoDeMaterial) {
    this.tipoDeMaterial = tipoDeMaterial;
  }

  public TipoDeMaterial getTipoDeMaterial() {
    return this.tipoDeMaterial;
  }
}
