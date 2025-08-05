package org.example.contenido;

public class Multimedia extends Contenido {
  private String descripcion;
  private String url;

  public Multimedia(String titulo, String descripcion, String url) {
    super(titulo);
    this.descripcion = descripcion;
    this.url = url;
  }

  @Override
  public void serEditado(Contenido contenido) {
    this.titulo = contenido.getTitulo();
    this.descripcion = contenido.getDescripcion();
    this.url = contenido.getUrl();
  }

  @Override
  public String getDescripcion() {
    return descripcion;
  }

  @Override
  public String getUrl() {
    return url;
  }
}
