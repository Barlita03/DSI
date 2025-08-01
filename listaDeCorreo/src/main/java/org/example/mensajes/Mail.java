package org.example.mensajes;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.example.Usuario;

public class Mail {
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario destinatario;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario origen;

  private final String titulo;
  private final String texto;

  public Mail(Mensaje mensaje, Usuario destinatario) {
    this.destinatario = destinatario;
    this.origen = mensaje.getOrigen();
    this.titulo = mensaje.getTitulo();
    this.texto = mensaje.getTexto();
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
  public Usuario getDestinatario() {
    return destinatario;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP")
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
