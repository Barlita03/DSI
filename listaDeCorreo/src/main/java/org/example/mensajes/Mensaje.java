package org.example.mensajes;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.example.Usuario;

public class Mensaje {
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2")
  private final Usuario origen;

  private final String titulo;
  private final String texto;

  // --- Constructor ---

  public Mensaje(Usuario origen, String titulo, String texto) {
    this.origen = origen;
    this.titulo = titulo;
    this.texto = texto;
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
}
