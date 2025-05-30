package org.qmp.sastres;

import org.qmp.prendas.Borrador;
import org.qmp.prendas.Color;
import org.qmp.prendas.Material;
import org.qmp.prendas.Prenda;
import org.qmp.prendas.TipoDePrenda;
import org.qmp.prendas.Uniforme;

public abstract class Sastre {
  public Uniforme fabricarUniforme() {
    return new Uniforme(this.fabricarParteSuperior(),
                        this.fabricarParteInferior(),
                        this.fabricarCalzado());
  }

  protected abstract Prenda fabricarParteSuperior();

  protected abstract Prenda fabricarParteInferior();

  protected abstract Prenda fabricarCalzado();
}
