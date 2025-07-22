package org.qmp.sastres;

import org.qmp.prendas.Atuendo;
import org.qmp.prendas.Prenda;

public abstract class Sastre {
  public Atuendo fabricarUniforme() {
    return new Atuendo(
        this.fabricarParteSuperior(), this.fabricarParteInferior(), this.fabricarCalzado());
  }

  protected abstract Prenda fabricarParteSuperior();

  protected abstract Prenda fabricarParteInferior();

  protected abstract Prenda fabricarCalzado();
}
