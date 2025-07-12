package org.primerParcial;

import java.time.LocalDateTime;

public class Mensaje {
  private final Canal autor;
  private final String contenido;
  private final LocalDateTime fecha;

  // --- Constructor ---

  public Mensaje(Canal autor, String contenido) {
    if(autor != null) {
      this.autor = autor;
    } else {
      throw new RuntimeException("El autor no puede ser null");
    }

    if(contenido != null) {
      if(!contenido.trim().isEmpty()) {
        this.contenido = contenido;
      } else {
        throw new RuntimeException("El contenido no puede estar vacio");
      }
    } else {
      throw new RuntimeException("El contenido no puede ser null");
    }

    this.fecha = LocalDateTime.now();
  }
}
