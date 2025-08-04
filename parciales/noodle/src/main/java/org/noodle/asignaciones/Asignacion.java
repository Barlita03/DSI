package org.noodle.asignaciones;

import java.util.ArrayList;
import java.util.List;

public class Asignacion {
  private final String titulo;
  private final List<Entrega> entregas;

  public Asignacion (String titulo, List<Entrega> entregas) {
    this.titulo = titulo;
    this.entregas = entregas;
  }

  public void agregarEntrega(Entrega entrega) {
    entregas.add(entrega);
  }

  public void removerEntrega(Entrega entrega) {
    entregas.remove(entrega);
  }

  public List<Entrega> getEntregas() {
    return entregas;
  }

  public void actualizarEntregas() {
    entregas.forEach(Entrega::serHabilitada);
  }
}
