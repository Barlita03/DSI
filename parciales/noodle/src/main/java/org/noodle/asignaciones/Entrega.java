package org.noodle.asignaciones;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Entrega {
  private final String url;
  private final LocalDate semanaHabilitacion;
  private EstadoEntrega estado = EstadoEntrega.CERRADA;

  public Entrega(String url, LocalDate semanaHabilitacion) {
    this.url = url;
    this.semanaHabilitacion = semanaHabilitacion;
  }

  public void cambiarEstado(EstadoEntrega estado) {
    this.estado = estado;
  }

  public void serHabilitada() {
    if (esEstaSemana()) {
      cambiarEstado(EstadoEntrega.ABIERTA);
    }
  }

  public boolean esEstaSemana() {
    LocalDate hoy = LocalDate.now();
    WeekFields wf = WeekFields.of(Locale.getDefault());
    int semanaHoy = hoy.get(wf.weekOfWeekBasedYear());
    int semanaEntrega = semanaHabilitacion.get(wf.weekOfWeekBasedYear());
    return semanaHoy == semanaEntrega && hoy.getYear() == semanaHabilitacion.getYear();
  }

  public boolean estaHabilitada() {
    return !estado.equals(EstadoEntrega.CERRADA);
  }
}
