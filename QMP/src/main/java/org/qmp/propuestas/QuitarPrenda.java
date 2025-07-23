package org.qmp.propuestas;

import org.qmp.prendas.Guardarropa;
import org.qmp.prendas.Prenda;

public class QuitarPrenda extends Propuesta {

  public QuitarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void realizarAceptacion() {
    guardarropa.quitarPrenda(prenda);
  }

  @Override
  public void realizarDeshacer() {
    guardarropa.agregarPrenda(prenda);
  }
}
