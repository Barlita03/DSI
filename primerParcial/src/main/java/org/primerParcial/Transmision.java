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
    if (autor != null) {
      this.autor = autor;
    } else {
      throw new RuntimeException("El autor no puede ser null");
    }

    if (titulo != null) {
      this.titulo = titulo;
    } else {
      throw new RuntimeException("El titulo no puede ser null");
    }

    this.categorias.addAll(List.of(categorias));
  }

  // --- Metodos ---

  public void recibirMensaje(Canal canal, String contenido) {
    chat.add(new Mensaje(canal, contenido));
  }

  public void serIniciada() {
    if (TransmisionesEnCurso.getInstancia().getTransmisiones().stream()
        .noneMatch(t -> t.getAutor().equals(autor))) {
      this.fechaInicio = LocalDateTime.now();
      TransmisionesEnCurso.agregarTransmision(this);
    } else {
      throw new RuntimeException("El canal ya tiene una transmision en curso");
    }
  }

  public void serFinalizada() {
    this.fechaFin = LocalDateTime.now();
    TransmisionesEnCurso.eliminarTransmision(this);
    espectadores.clear();
    autor.agregarAlHistorico(this);
  }

  public void agregarVisualizador(Canal canal) {
    espectadores.add(canal);

    if (espectadores.size() > maximoParticipantes) {
      maximoParticipantes = espectadores.size();
    }
  }

  public void quitarVisualizador(Canal canal) {
    espectadores.remove(canal);
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
