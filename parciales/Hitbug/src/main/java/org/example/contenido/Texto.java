package org.example.contenido;

public class Texto extends Contenido {
  private String texto;

  public Texto(String titulo, String texto) {
    super(titulo);
    this.texto = texto;
  }

  @Override
  public void serEditado(Contenido contenido) {
    this.titulo = contenido.getTitulo();
    this.texto = contenido.getTexto();
  }

  @Override
  public String getTexto() {
    return texto;
  }
}
