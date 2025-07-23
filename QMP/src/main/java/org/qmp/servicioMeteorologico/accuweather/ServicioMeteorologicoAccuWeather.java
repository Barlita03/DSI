package org.qmp.servicioMeteorologico.accuweather;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.qmp.apis.AccuWeatherApi;
import org.qmp.servicioMeteorologico.ServicioMetereologico;

public class ServicioMeteorologicoAccuWeather implements ServicioMetereologico {
  private final Map<String, RespuestaAccuWeather> ultimasRespuestas;
  private final AccuWeatherApi api;
  private final Duration periodoDeValidez;

  // --- Constructor ---

  public ServicioMeteorologicoAccuWeather(AccuWeatherApi api, Duration periodoDeValidez) {
    this.api = api;
    this.periodoDeValidez = periodoDeValidez;
    this.ultimasRespuestas = new HashMap<String, RespuestaAccuWeather>();
  }

  // --- Getters ---

  public Map<String, Object> getCondicionesClimaticas(String direccion) {
    if (!this.ultimasRespuestas.containsKey(direccion)
        || this.ultimasRespuestas.get(direccion).expiro()) {
      ultimasRespuestas.put(
          direccion, new RespuestaAccuWeather(this.consultarApi(direccion), proximaExpiracion()));
    }
    return this.ultimasRespuestas.get(direccion).getEstadoDelTiempo();
  }

  public int getTemperaturaEnFarenheit(String direccion) {
    return (int)
        ((Map<String, Object>) getCondicionesClimaticas(direccion).get("Temperature")).get("Value");
  }

  public int getTemperaturaEnCelsius(String direccion) {
    return (this.getTemperaturaEnFarenheit(direccion) - 32) * 5 / 9;
  }

  // --- Metodos ---

  private LocalDateTime proximaExpiracion() {
    return LocalDateTime.now().plus(this.periodoDeValidez);
  }

  private Map<String, Object> consultarApi(String direccion) {
    return this.api.getWeather(direccion).get(0);
  }
}
