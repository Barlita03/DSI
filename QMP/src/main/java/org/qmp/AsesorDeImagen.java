package org.qmp;

import java.util.List;
import org.qmp.prendas.Atuendo;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;
import org.qmp.usuarios.Usuario;

public class AsesorDeImagen {
  private final ServicioMeteorologico servicioMeteorologico;

  public AsesorDeImagen(ServicioMeteorologico servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

  public List<Atuendo> sugerirAtuendos(String direccion, Usuario usuario) {
    int temperatura = this.servicioMeteorologico.getTemperaturaEnCelsius(direccion);

    List<Atuendo> combinaciones = usuario.todasLasCombinaciones();

    return combinaciones.stream().filter(a -> a.aptoParaTemperatura(temperatura)).toList();
  }
}
