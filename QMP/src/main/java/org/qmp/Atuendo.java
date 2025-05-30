package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.prendas.Formalidad;
import org.qmp.prendas.Prenda;

public class Atuendo {
  private final Prenda parteSuperior;
  private final Prenda parteInferior;
  private final Prenda calzado;
  private final List<Prenda> accesorios;

  // --- Constructor ---

  public Atuendo(Prenda parteSuperior, Prenda parteInferior, Prenda calzado) {
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.calzado = calzado;
    this.accesorios = new ArrayList<>();
  }

  public Atuendo(Prenda parteSuperior, Prenda parteInferior, Prenda calzado, Prenda... accesorios) {
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.calzado = calzado;
    this.accesorios = new ArrayList<>(List.of(accesorios));
  }

  public Atuendo(
      Prenda parteSuperior, Prenda parteInferior, Prenda calzado, List<Prenda> accesorios) {
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.calzado = calzado;
    this.accesorios = new ArrayList<Prenda>(accesorios);
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

  public List<Prenda> getAccesorios() {
    return new ArrayList<Prenda>(this.accesorios);
  }

  // --- Metodos ---

  public boolean esFormal() {
    return parteSuperior.getFormalidad() == Formalidad.FORMAL
        && parteInferior.getFormalidad() == Formalidad.FORMAL
        && calzado.getFormalidad() == Formalidad.FORMAL;
  }

  public boolean aptoParaTemperatura(int temperatura) {
    return parteSuperior.aptaParaTemperatura(temperatura)
        && parteInferior.aptaParaTemperatura(temperatura)
        && calzado.aptaParaTemperatura(temperatura);
  }
}
