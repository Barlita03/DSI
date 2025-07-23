package org.qmp.propuestas;

import org.qmp.Guardarropa;
import org.qmp.prendas.Prenda;

public class AgregarPrenda extends Propuesta {

  public AgregarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void realizarAceptacion() {
    guardarropa.agregarPrenda(prenda);
  }

  @Override
  public void realizarDeshacer() {
    guardarropa.quitarPrenda(prenda);
  }
}
