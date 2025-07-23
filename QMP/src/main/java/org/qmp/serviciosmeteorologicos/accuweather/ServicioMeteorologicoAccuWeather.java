package org.qmp.serviciosmeteorologicos.accuweather;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.qmp.apis.AccuWeatherApi;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;

public class ServicioMeteorologicoAccuWeather implements ServicioMeteorologico {
  private final Map<String, RespuestaAccuWeather> ultimasRespuestas = new HashMap<>();
  private final AccuWeatherApi api;
  private final Duration periodoDeValidez;
  private final String ubicacion;

  // --- Constructor ---

  public ServicioMeteorologicoAccuWeather(
      AccuWeatherApi api, Duration periodoDeValidez, String ubicacion) {
    this.api = api;
    this.ubicacion = ubicacion;
    this.periodoDeValidez = periodoDeValidez;
  }

  // --- Getters ---

  public Map<String, Object> getCondicionesClimaticas() {
    validar();
    return ultimasRespuestas.get(ubicacion).getEstadoDelTiempo();
  }

  public List<String> getAlertasMeteorologicas() {
    validar();
    return ultimasRespuestas.get(ubicacion).getAlertasMeteorologicas().get("CurrentAlerts");
  }

  public int getTemperaturaEnFarenheit() {
    Map<String, Object> temperatura =
        (Map<String, Object>) getCondicionesClimaticas().get("Temperature");
    return (int) temperatura.get("Value");
  }

  public int getTemperaturaEnCelsius() {
    return (getTemperaturaEnFarenheit() - 32) * 5 / 9;
  }

  // --- Metodos ---

  private void validar() {
    if (!ultimasRespuestas.containsKey(ubicacion) || ultimasRespuestas.get(ubicacion).expiro()) {
      actualizar();
    }
  }

  private void actualizar() {
    if (ultimasRespuestas.get(ubicacion) != null) {
      ultimasRespuestas.remove(ubicacion);
    }

    ultimasRespuestas.put(
        ubicacion,
        new RespuestaAccuWeather(
            api.getWeather(ubicacion).get(0), api.getAlerts(ubicacion), proximaExpiracion()));
  }

  private LocalDateTime proximaExpiracion() {
    return LocalDateTime.now().plus(periodoDeValidez);
  }
}
