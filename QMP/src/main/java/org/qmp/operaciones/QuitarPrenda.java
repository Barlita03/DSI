package org.qmp.operaciones;

import org.qmp.Guardarropa;
import org.qmp.prendas.Prenda;

public class QuitarPrenda extends Propuesta {

  public QuitarPrenda(Guardarropa guardarropa, Prenda prenda) {
    super(guardarropa, prenda);
  }

  @Override
  public void serAceptada() {
    guardarropa.quitarPrenda(prenda);
    super.serAceptada();
  }

  @Override
  public void deshacer() {
    guardarropa.agregarPrenda(prenda);
    super.deshacer();
  }
}
