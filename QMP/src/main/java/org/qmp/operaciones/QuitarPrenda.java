package org.qmp.operaciones;

import org.qmp.Guardarropa;
import org.qmp.prendas.Prenda;

public class QuitarPrenda extends Sugerencia {

  public QuitarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void serAceptada() {
    guardarropa.quitarPrenda(prenda);
  }

  @Override
  public void deshacer() {
    guardarropa.agregarPrenda(prenda);
  }
}
