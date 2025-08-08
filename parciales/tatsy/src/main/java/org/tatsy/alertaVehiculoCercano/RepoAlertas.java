package org.tatsy.alertaVehiculoCercano;

import org.tatsy.Vehiculo;
import org.tatsy.solicitudesDeVehiculo.SolicitudDeVehiculo;

import java.util.ArrayList;
import java.util.List;

public class RepoAlertas {
  private static final RepoAlertas instancia = new RepoAlertas();
  private static final List<AlertaVehiculoCercano> alertas = new ArrayList<>();

  public static RepoAlertas getInstancia() {
    return instancia;
  }

  public void agregarAlerta(AlertaVehiculoCercano alerta) {
    alertas.add(alerta);
  }

  public void removerAlerta(AlertaVehiculoCercano alerta) {
    alertas.remove(alerta);
  }

  public void removerAlerta(SolicitudDeVehiculo solicitud) {
    removerAlerta(alertas.stream().filter(a -> a.getSolicitud().equals(solicitud)).toList().get(0));
  }

  public void enviarAlertas(Vehiculo vehiculo) {
    alertas.stream().filter(a -> vehiculo.estaCerca(a.solicitud.getPosicion())).forEach(a -> a.notificar(vehiculo));
  }
}
