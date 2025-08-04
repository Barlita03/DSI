package org.copia.me.documentos;

public class Documento {
  private final String contenido;
  private EstadoDocumento estado = EstadoDocumento.NO_REVISADO;

  public Documento (String contenido) {
    this.contenido = contenido;
  }

  public void marcarCopia() {
    if(!esDudoso() && estado.equals(EstadoDocumento.ORIGINAL)) {
      marcarDudoso();
    } else {
      setEstado(EstadoDocumento.COPIA);
    }
  }

  public void marcarOriginal() {
    if(!esDudoso() &&  estado.equals(EstadoDocumento.COPIA)) {
      marcarDudoso();
    } else {
      setEstado(EstadoDocumento.ORIGINAL);
    }
  }

  public void marcarDudoso() {
    setEstado(EstadoDocumento.DUDOSO);
  }

  private boolean esDudoso() {
    return estado.equals(EstadoDocumento.DUDOSO);
  }

  private void setEstado(EstadoDocumento estado) {
    this.estado = estado;
  }
}
