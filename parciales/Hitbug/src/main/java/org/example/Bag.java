package org.example;

import org.example.contenido.Contenido;
import org.example.hits.Hit;

import java.util.ArrayList;
import java.util.List;

public class Bag extends Contenido {
  private List<Contenido> contenido = new ArrayList<>();
  private Usuario propietario;
  private List<Usuario> colaboradores = new ArrayList<>();
  private List<Hit> historial = new ArrayList<>();
  private List<Hit> hitRequests = new ArrayList<>();

  public Bag(String titulo, Usuario propietario) {
    super(titulo);
    this.propietario = propietario;
  }

  public Bag(Bag bag) {
    super(bag.getTitulo());
    this.contenido = new ArrayList<>(bag.getContenido());
    this.propietario = bag.getPropietario();
    this.colaboradores = new ArrayList<>(bag.getColaboradores());
    this.historial = new ArrayList<>(bag.getHistorial());
    this.hitRequests = new ArrayList<>(bag.getHitRequests());
  }

  @Override
  public List<Contenido> getContenido() {
    return contenido.stream().flatMap(c -> c.getContenido().stream()).toList();
  }

  public void agregarContenido (Contenido contenido) {
    this.contenido.add(contenido);
  }

  public void quitarContenido (Contenido contenido) {
    this.contenido.remove(contenido);
  }

  public void agregarColaborador(Usuario usuario) {
    colaboradores.add(usuario);
  }

  public void quitarColaborador(Usuario usuario) {
    colaboradores.remove(usuario);
  }

  public void agregarAlHistorial(Hit hit) {
    historial.add(hit);
  }

  public void solicitarHit(Hit hit) {
    hitRequests.add(hit);
  }

  public Bag clonar() {
    return new Bag(this);
  }

  public List<Hit> getHitRequests() {
    return hitRequests;
  }

  public List<Hit> getHistorial() {
    return historial;
  }

  public List<Usuario> getColaboradores() {
    return colaboradores;
  }

  public Usuario getPropietario() {
    return propietario;
  }

  @Override
  public void serEditado(Contenido contenido) {
    // TODO
  }
}
