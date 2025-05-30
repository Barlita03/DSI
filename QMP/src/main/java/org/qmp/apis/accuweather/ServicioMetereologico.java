package org.qmp.apis.accuweather;

import java.util.Map;

public interface ServicioMetereologico {
  Map<String, Object> getCondicionesClimaticas(String direccion);

  int getTemperaturaEnFarenheit(String direccion);

  int getTemperaturaEnCelsius(String direccion);
}
