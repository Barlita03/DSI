package org.primerParcial;

import java.time.LocalDateTime;

public class Mensaje {
  private final Canal autor;
  private final String contenido;
  private final LocalDateTime fecha;

  // --- Constructor ---

  public Mensaje(Canal autor, String contenido) {
    validarAutor(autor);
    this.autor = autor;

    validarContenido(contenido);
    this.contenido = contenido;

    this.fecha = LocalDateTime.now();
  }

  // --- Getters ---

  public String getContenido() {
    return contenido;
  }

  public Canal getAutor() {
    return autor;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }

  // --- Metodos ---

  private void validarAutor (Canal autor) {
    if (autor == null) {
      throw new RuntimeException("El autor no puede ser null");
    }
  }

  private void validarContenido (String contenido) {
    if (contenido == null) {
      throw new RuntimeException("El contenido no puede ser null");
    } else {
      if (contenido.trim().isEmpty()) {
        throw new RuntimeException("El contenido no puede estar vacio");
      }
    }
  }
}
