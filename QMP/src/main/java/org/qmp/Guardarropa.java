package org.qmp;

import java.util.ArrayList;
import java.util.List;
import org.qmp.prendas.Prenda;

public class Guardarropa {
  private final List<Prenda> prendas = new ArrayList<>();

  public void agregarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

  public void quitarPrenda(Prenda prenda) {
    prendas.remove(prenda);
  }

  public List<Prenda> getPrendasSuperiores() {
    return new ArrayList<Prenda>(this.prendas.stream().filter(Prenda::esSuperior).toList());
  }

  public List<Prenda> getPrendasInferiores() {
    return new ArrayList<Prenda>(this.prendas.stream().filter(Prenda::esInferior).toList());
  }

  public List<Prenda> getCalzados() {
    return new ArrayList<Prenda>(this.prendas.stream().filter(Prenda::esCalzado).toList());
  }

  public List<Prenda> getAccesorios() {
    return new ArrayList<Prenda>(this.prendas.stream().filter(Prenda::esAccesorio).toList());
  }

  public List<Prenda> getPrendas() {
    return new ArrayList<>(prendas);
  }
}
