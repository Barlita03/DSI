package org.noodle.asignaciones;

import org.noodle.grupos.GestorDeGrupos;

import java.util.ArrayList;
import java.util.List;

public class GestorDeAsignaciones {
  private static final GestorDeAsignaciones instancia = new GestorDeAsignaciones();
  private final List<Asignacion> asignaciones = new ArrayList<>();

  public static GestorDeAsignaciones getInstancia() {
    return instancia;
  }

  public void agregarAsignacion(Asignacion asignacion) {
    asignaciones.add(asignacion);
  }

  public void removerAsignacion(Asignacion asignacion) {
    asignaciones.remove(asignacion);
  }

  public void actualizarEntregas() {
    asignaciones.forEach(Asignacion::actualizarEntregas);
  }
}
