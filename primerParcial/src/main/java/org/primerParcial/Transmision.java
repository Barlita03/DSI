package org.primerParcial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transmision {
  private final String titulo;
  private final Canal autor;
  private final List<String> categorias = new ArrayList<>();
  private final List<Canal> espectadores = new ArrayList<>();
  private int maximoParticipantes = 0;
  private LocalDateTime fechaInicio = null;
  private LocalDateTime fechaFin = null;
  private final List<Mensaje> chat = new ArrayList<>();

  // --- Constructor ---

  public Transmision(Canal autor, String titulo, String... categorias) {
    validarAutor(autor);
    this.autor = autor;

    validarTitulo(titulo);
    this.titulo = titulo;

    this.categorias.addAll(List.of(categorias));
  }

  // --- Metodos ---

  public void recibirMensaje(Canal canal, String contenido) {
    chat.add(new Mensaje(canal, contenido));
  }

  public void serIniciada() {
    if (autor.puedeTransmitir()) {
      this.fechaInicio = LocalDateTime.now();
      autor.setTransmisionEnCurso(this);
    } else {
      throw new RuntimeException("El canal ya tiene una transmision en curso");
    }
  }

  public void serFinalizada() {
    this.fechaFin = LocalDateTime.now();
    espectadores.clear();
    autor.setTransmisionEnCurso(null);
    autor.agregarAlHistorico(this);
  }

  public void agregarVisualizador(Canal canal) {
    espectadores.add(canal);
    actualizarMaximoDeEspectadores();
  }

  public void quitarVisualizador(Canal canal) {
    espectadores.remove(canal);
  }

  private void actualizarMaximoDeEspectadores() {
    if (espectadores.size() > maximoParticipantes) {
      maximoParticipantes = espectadores.size();
    }
  }

  private void validarAutor(Canal autor) {
    if (autor == null) {
      throw new RuntimeException("El autor no puede ser null");
    }
  }

  private void validarTitulo(String titulo) {
    if (titulo == null) {
      throw new RuntimeException("El titulo no puede ser null");
    }
  }

  // --- Getters ---

  public LocalDateTime getInicio() {
    return fechaInicio;
  }

  public LocalDateTime getFin() {
    return fechaFin;
  }

  public List<Canal> getEspectadores() {
    return new ArrayList<>(espectadores);
  }

  public List<Mensaje> getChat() {
    return new ArrayList<>(chat);
  }

  public String getTitulo() {
    return titulo;
  }

  public Canal getAutor() {
    return autor;
  }
}