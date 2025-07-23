package org.qmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;

public class GestorDeAlertas {
  private static final GestorDeAlertas instancia = new GestorDeAlertas();
  private final Map<ServicioMeteorologico, List<String>> alertasPendientes = new HashMap<>();
  private final List<String> alertasLanzadas = new ArrayList<>();

  public static GestorDeAlertas getInstancia() {
    return instancia;
  }

  public List<String> getAlertasPendientes() {
    List<String> alertasFiltradas = new ArrayList<>();

    for (List<String> alertasPorServicio : alertasPendientes.values()) {
      alertasFiltradas.addAll(alertasPorServicio);
    }

    return alertasFiltradas;
  }

  public List<String> getAlertasLanzadas() {
    return new ArrayList<>(alertasLanzadas);
  }

  public void agregarAlerta(ServicioMeteorologico servicio, String alerta) {
    if (alertasPendientes.get(servicio) == null) {
      alertasPendientes.put(servicio, new ArrayList<>());
    }

    alertasPendientes.get(servicio).add(alerta);
  }

  public void quitarAlerta(ServicioMeteorologico servicio, String alerta) {
    alertasPendientes.get(servicio).remove(alerta);
  }

  public void dispararAlertas() {
    for (ServicioMeteorologico servicio : alertasPendientes.keySet()) {
      alertasLanzadas.addAll(alertasPendientes.get(servicio));
      // alertasPendientes.get(servicio).forEach(servicio::notificar);
    }

    alertasPendientes.clear();
  }
}
