package org.example;

public class Mensaje {
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

  public Usuario getOrigen() {
    return origen;
  }
}
