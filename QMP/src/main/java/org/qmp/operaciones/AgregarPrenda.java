package org.qmp.operaciones;

import org.qmp.Guardarropa;
import org.qmp.prendas.Prenda;

public class AgregarPrenda extends Sugerencia {

  public AgregarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void serAceptada() {
    guardarropa.agregarPrenda(prenda);
  }

  @Override
  public void deshacer() {
    guardarropa.quitarPrenda(prenda);
  }
}
