package org.copia.me.revisiones;

import java.util.ArrayList;
import java.util.List;

public class Revisor {
  private final String email;
  private int maxRevisionesPorMes;
  private final List<Revision> revisionesDeEsteMes = new ArrayList<>();

  public Revisor(String email, int maxRevisionesPorMes) {
    this.email = email;
    this.maxRevisionesPorMes = maxRevisionesPorMes;
  }

  public void agregarRevision(Revision revision) {
    puedeRecibirRevisiones();
    revisionesDeEsteMes.add(revision);
  }

  public void removerRevision(Revision revision) {
    revisionesDeEsteMes.remove(revision);
  }

  private void puedeRecibirRevisiones() {
    if (maxRevisionesPorMes < revisionesDeEsteMes.size() + 1) {
      throw new RuntimeException("El revisor no puede recibir mas revisiones este mes");
    }
  }

  // Se calendariza con un cron
  public void cambiarDeMes() {
    revisionesDeEsteMes.removeAll(revisionesDeEsteMes.stream().filter(r -> !r.estaRevisada()).toList());
  }

  public boolean estaDisponible() {
    return maxRevisionesPorMes < revisionesDeEsteMes.size();
  }

  public int cantidadDeRevisionesQuePuedeAceptar() {
    return maxRevisionesPorMes - revisionesDeEsteMes.size();
  }

  public void agregarRevisiones(List<Revision> revisiones) {
    revisionesDeEsteMes.addAll(revisiones);
  }
}
