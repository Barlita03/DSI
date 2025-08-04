package org.copia.me.revisiones;

import org.copia.me.documentos.Documento;
import org.copia.me.documentos.EstadoDocumento;

public class Revision {
  private final Documento unDocumento;
  private final Documento otroDocumento;
  private EstadoDocumento estado = EstadoDocumento.NO_REVISADO;

  public Revision (Documento unDocumento, Documento otroDocumento) {
    this.unDocumento = unDocumento;
    this.otroDocumento = otroDocumento;
  }

  public boolean estaRevisada() {
    return !estado.equals(EstadoDocumento.NO_REVISADO);
  }

  public void marcarCopia() {
    setEstado(EstadoDocumento.COPIA);
  }

  public void marcarOriginal() {
    setEstado(EstadoDocumento.ORIGINAL);
  }

  private void setEstado(EstadoDocumento estado) {
    this.estado = estado;
  }

  public Documento getUnDocumento() {
    return unDocumento;
  }

  public Documento getOtroDocumento() {
    return otroDocumento;
  }

  public Revision clonar() {
    return new Revision(unDocumento, otroDocumento);
  }
}
