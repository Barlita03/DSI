package org.example;

public class Mail {
  private final Usuario destinatario;
  private final Usuario origen;
  private final String titulo;
  private final String texto;

  public Mail(Mensaje mensaje, Usuario destinatario) {
    this.destinatario = destinatario;
    this.origen = mensaje.getOrigen();
    this.titulo = mensaje.getTitulo();
    this.texto = mensaje.getTexto();
  }

  public Usuario getDestinatario() {
    return destinatario;
  }

  public Usuario getOrigen() {
    return origen;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getTexto() {
    return texto;
  }
}
