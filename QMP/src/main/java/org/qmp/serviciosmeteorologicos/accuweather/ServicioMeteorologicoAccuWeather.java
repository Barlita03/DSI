package org.qmp.serviciosmeteorologicos.accuweather;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.qmp.apis.AccuWeatherApi;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;

public class ServicioMeteorologicoAccuWeather implements ServicioMeteorologico {
  private final Map<String, RespuestaAccuWeather> ultimasRespuestas;
  private final AccuWeatherApi api;
  private final Duration periodoDeValidez;

  // --- Constructor ---

  public ServicioMeteorologicoAccuWeather(AccuWeatherApi api, Duration periodoDeValidez) {
    this.api = api;
    this.periodoDeValidez = periodoDeValidez;
    this.ultimasRespuestas = new HashMap<>();
  }

  // --- Getters ---

  public Map<String, Object> getCondicionesClimaticas(String direccion) {
    validar(direccion);
    return ultimasRespuestas.get(direccion).getEstadoDelTiempo();
  }

  public List<String> getAlertasMeteorologicas(String direccion) {
    validar(direccion);
    return ultimasRespuestas.get(direccion).getAlertasMeteorologicas().get("CurrentAlerts");
  }

  public int getTemperaturaEnFarenheit(String direccion) {
    Map<String, Object> temperatura =
        (Map<String, Object>) getCondicionesClimaticas(direccion).get("Temperature");
    return (int) temperatura.get("Value");
  }

  public int getTemperaturaEnCelsius(String direccion) {
    return (getTemperaturaEnFarenheit(direccion) - 32) * 5 / 9;
  }

  // --- Metodos ---

  private void validar(String direccion) {
    if (!ultimasRespuestas.containsKey(direccion) || ultimasRespuestas.get(direccion).expiro()) {
      actualizar(direccion);
    }
  }

  private void actualizar(String direccion) {
    if (ultimasRespuestas.get(direccion) != null) {
      ultimasRespuestas.remove(direccion);
    }

    ultimasRespuestas.put(
        direccion,
        new RespuestaAccuWeather(
            api.getWeather(direccion).get(0), api.getAlerts(direccion), proximaExpiracion()));
  }

  private LocalDateTime proximaExpiracion() {
    return LocalDateTime.now().plus(periodoDeValidez);
  }
}
