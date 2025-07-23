package org.qmp.servicioMeteorologico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.qmp.notificadores.Notificador;

public abstract class ServicioMetereologico {
  private final Map<String, List<Notificador>> notificadores = new HashMap<>();
  private final List<String> alertas = new ArrayList<>();

  // --- Constructor ----

  public ServicioMetereologico(String... eventos) {
    for (String evento : eventos) {
      notificadores.put(evento, new ArrayList<>());
    }
  }

  // --- Metodos ----

  public void agregarNotificador(String evento, Notificador notificador) {
    notificadores.get(evento).add(notificador);
  }

  public void quitarNotificador(String evento, Notificador notificador) {
    notificadores.get(evento).remove(notificador);
  }

  public void notificar(String evento) {
    alertas.add(evento);
    notificadores.get(evento).forEach(Notificador::notificar);
  }

  public List<String> getAlertas() {
    return alertas;
  }

  // --- Metodos abstractos ----

  public abstract Map<String, Object> getCondicionesClimaticas(String direccion);

  public abstract int getTemperaturaEnFarenheit(String direccion);

  public abstract int getTemperaturaEnCelsius(String direccion);
}
