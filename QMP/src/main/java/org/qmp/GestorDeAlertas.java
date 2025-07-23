package org.qmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.qmp.servicioMeteorologico.ServicioMetereologico;

public class GestorDeAlertas {
  private static final GestorDeAlertas instancia = new GestorDeAlertas();
  private final Map<ServicioMetereologico, List<String>> alertas = new HashMap<>();

  public static GestorDeAlertas getInstancia() {
    return instancia;
  }

  public List<String> getAlertas() {
    List<String> alertasFiltradas = new ArrayList<>();

    for (List<String> alertasPorServicio : alertas.values()) {
      alertasFiltradas.addAll(alertasPorServicio);
    }

    return alertasFiltradas;
  }

  public void agregarAlerta(ServicioMetereologico servicio, String alerta) {
    if (alertas.get(servicio) == null) {
      alertas.put(servicio, new ArrayList<>());
    }

    alertas.get(servicio).add(alerta);
  }

  public void quitarAlerta(ServicioMetereologico servicio, String alerta) {
    alertas.get(servicio).remove(alerta);
  }

  public void dispararAlertas() {
    for (ServicioMetereologico servicio : alertas.keySet()) {
      alertas.get(servicio).forEach(servicio::notificar);
    }

    alertas.clear();
  }
}
