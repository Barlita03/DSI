package org.qmp.serviciosmeteorologicos;

import java.util.List;
import java.util.Map;

public interface ServicioMeteorologico {
  Map<String, Object> getCondicionesClimaticas(String direccion);

  int getTemperaturaEnFarenheit(String direccion);

  int getTemperaturaEnCelsius(String direccion);

  List<String> getAlertasMeteorologicas(String ciudad);
}
