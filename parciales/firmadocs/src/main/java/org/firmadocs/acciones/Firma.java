package org.firmadocs.acciones;

import org.firmadocs.procesos.Proceso;
import org.firmadocs.usuarios.Usuario;

public class Firma extends Accion {
  private String firma;

  public Firma(Usuario usuario, Proceso proceso) {
    super(usuario, proceso);
  }

  public String getFirma() {
    return firma;
  }

  private void setFirma(String firma) {
    this.firma = firma;
  }

  @Override
  public void serRealizada() {
    setFirma(usuario.generarFirma());
    super.serRealizada();
  }
}
