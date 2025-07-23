package org.qmp;

import java.util.List;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;
import org.qmp.usuarios.GestorDeUsuarios;

public class GestorDeAlertas {
  private final ServicioMeteorologico servicioMeteorologico;
  private List<String> alertas;

  public GestorDeAlertas(ServicioMeteorologico servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

  public void actualizarAlertas() {
    alertas = servicioMeteorologico.getAlertasMeteorologicas();
  }

  public void lanzarAlertas() {
    if (alertas.contains("tormenta")) {
      GestorDeUsuarios.getInstancia()
          .getUsuarios()
          .forEach(u -> u.recibirNotificacion("Recorda llevar un paraguas"));
    }
    if (alertas.contains("granizo")) {
      GestorDeUsuarios.getInstancia()
          .getUsuarios()
          .forEach(u -> u.recibirNotificacion("Evita salir con el auto"));
    }
  }
}
