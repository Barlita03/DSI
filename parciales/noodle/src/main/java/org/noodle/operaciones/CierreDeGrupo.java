package org.noodle.operaciones;

import org.noodle.grupos.BorradorGrupo;

public class CierreDeGrupo {
  private final BorradorGrupo borrador;

  public CierreDeGrupo(BorradorGrupo borrador) {
    this.borrador = borrador;
  }

  public void serAceptada() {
    borrador.cerrarGrupo();
  }
}
