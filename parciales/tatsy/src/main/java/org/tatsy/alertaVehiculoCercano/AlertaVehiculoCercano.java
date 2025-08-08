package org.tatsy.alertaVehiculoCercano;

import org.tatsy.Vehiculo;
import org.tatsy.solicitudesDeVehiculo.SolicitudDeVehiculo;

public class AlertaVehiculoCercano {
  SolicitudDeVehiculo solicitud;

  public void notificar(Vehiculo vehiculo) {
    if(vehiculo.distanciaA(solicitud.getPosicion()) <
        solicitud.getVehiculo().distanciaA(solicitud.getPosicion())) {
      solicitud.cambiarVehiculo(vehiculo);
    }
  }

  public SolicitudDeVehiculo getSolicitud() {
    return solicitud;
  }
}
