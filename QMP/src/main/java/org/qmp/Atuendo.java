package org.qmp;

import org.qmp.prendas.Formalidad;
import org.qmp.prendas.Prenda;

public class Atuendo {
  private final Prenda parteSuperior;
  private final Prenda parteInferior;
  private final Prenda calzado;

  // --- Constructor ---

  public Atuendo(Prenda parteSuperior, Prenda parteInferior, Prenda calzado) {
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.calzado = calzado;
  }

  // --- Getters ---

  public Prenda getParteSuperior() {
    return parteSuperior;
  }

  public Prenda getParteInferior() {
    return parteInferior;
  }

  public Prenda getCalzado() {
    return calzado;
  }

  // --- Metodos ---

  public boolean esFormal() {
    return parteSuperior.getFormalidad() == Formalidad.FORMAL
        && parteInferior.getFormalidad() == Formalidad.FORMAL
        && calzado.getFormalidad() == Formalidad.FORMAL;
  }
}
