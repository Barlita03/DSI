package org.qmp;

import java.util.ArrayList;
import java.util.List;

public class Guardarropas {
  private List<Prenda> prendas;

  public Guardarropas(List<Prenda> prendas) {
    this.prendas = new ArrayList<Prenda>(prendas);
  }

  public List<Prenda> crearAtuendo() {
    // TODO
    return null;
  }

  public void agregarPrenda(Prenda prenda) {
    this.prendas.add(prenda);
  }
}