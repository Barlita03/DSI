package org.example.mensajes;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.example.Usuario;

public class Borrador {
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario origen;

  private final String titulo;
  private String texto;

  // --- Constructor ---

  public Borrador(Usuario origen, String titulo, String texto) {
    this.origen = origen;
    this.titulo = titulo;

    if (origen.getFirma() != null) {
      this.texto = texto + "\n\n" + origen.getFirma();
    } else {
      this.texto = texto;
    }
  }

  // --- Getters ---

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

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public Mensaje construirMensaje(Usuario usuario) throws Exception {
    return new Mensaje(this, usuario);
  }
}
