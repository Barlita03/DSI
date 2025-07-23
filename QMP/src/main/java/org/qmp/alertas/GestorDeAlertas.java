package org.qmp.alertas;

import java.util.ArrayList;
import java.util.List;
import org.qmp.serviciosmeteorologicos.ServicioMeteorologico;
import org.qmp.usuarios.GestorDeUsuarios;

public class GestorDeAlertas {
  private final ServicioMeteorologico servicioMeteorologico;
  private List<String> alertas = new ArrayList<>();

  public GestorDeAlertas(ServicioMeteorologico servicioMeteorologico) {
    this.servicioMeteorologico = servicioMeteorologico;
  }

  public void actualizarAlertas() {
    alertas = servicioMeteorologico.getAlertasMeteorologicas();
  }

  public List<String> getAlertas() {
    return new ArrayList<>(alertas);
  }

  public void lanzarAlertas() {
    if (alertas.contains("Tormenta")) {
      GestorDeUsuarios.getInstancia()
          .getUsuarios()
          .forEach(u -> u.recibirNotificacion("Recorda llevar un paraguas"));
    }
    if (alertas.contains("Granizo")) {
      GestorDeUsuarios.getInstancia()
          .getUsuarios()
          .forEach(u -> u.recibirNotificacion("Evita salir con el auto"));
    }
  }

  public void vaciarLista() {
    alertas.clear();
  }
}
