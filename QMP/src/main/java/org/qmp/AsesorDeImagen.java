package org.qmp;

import java.util.List;
import org.qmp.apis.accuweather.ServicioMeteorologicoAccuWeather;
import org.qmp.prendas.Atuendo;

public class AsesorDeImagen {
  private final ServicioMeteorologicoAccuWeather servicioMeteorologicoAccuWeather;

  public AsesorDeImagen(ServicioMeteorologicoAccuWeather servicioMeteorologicoAccuWeather) {
    this.servicioMeteorologicoAccuWeather = servicioMeteorologicoAccuWeather;
  }

  public Atuendo sugerirAtuendo(String direccion, Usuario usuario) {
    int temperatura = this.servicioMeteorologicoAccuWeather.getTemperaturaEnCelsius(direccion);

    List<Atuendo> combinaciones = usuario.generarSugerencias();

    return combinaciones.stream().filter(a -> a.aptoParaTemperatura(temperatura)).toList().get(0);
  }
}
