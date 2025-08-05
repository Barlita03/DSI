package org.example.contenido;

import java.util.List;

public abstract class Contenido {
  protected String titulo;

  public Contenido (String titulo) {
    this.titulo = titulo;
  }

  public List<Contenido> getContenido() {
    return List.of(this);
  }

  public String getTitulo() {
    return titulo;
  }

  public abstract void serEditado(Contenido contenido);

  public String getTexto() {
    return null;
  }

  public String getDescripcion() {
    return null;
  }

  public String getUrl() {
    return null;
  }
}