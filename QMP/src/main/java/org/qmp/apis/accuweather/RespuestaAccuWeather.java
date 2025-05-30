package org.qmp.apis.accuweather;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RespuestaAccuWeather {
  private Map<String, Object> estadoDelTiempo;
  private LocalDateTime expiracion;

  // --- Constructor ---

  public RespuestaAccuWeather(Map<String, Object> estadoDelTiempo, LocalDateTime expiracion) {
    this.estadoDelTiempo = new HashMap<>(estadoDelTiempo);
    this.expiracion = expiracion;
  }

  // --- Getters ---

  public Map<String, Object> getEstadoDelTiempo() {
    return new HashMap<>(this.estadoDelTiempo);
  }

  // --- Metodos ---

  public boolean expiro() {
    return this.expiracion.isAfter(LocalDateTime.now());
  }
}
