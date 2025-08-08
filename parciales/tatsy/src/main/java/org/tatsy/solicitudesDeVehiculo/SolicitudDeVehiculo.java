package org.tatsy.solicitudesDeVehiculo;

import org.tatsy.Flota;
import org.tatsy.Posicion;
import org.tatsy.Vehiculo;
import org.tatsy.alertaVehiculoCercano.RepoAlertas;

import java.util.List;

public abstract class SolicitudDeVehiculo {
  private Vehiculo vehiculo;
  private Posicion posicion;

  public SolicitudDeVehiculo(Posicion posicion) {
    this.posicion = posicion;
    buscarVehiculo();
  }

  public void buscarVehiculo() {
    List<Vehiculo> vehiculosDisponibles =
        Flota.getVehiculos().stream().filter(v -> v.estaCerca(posicion))
            .toList().sort(v -> v.distanciaA(posicion));

    if (!vehiculosDisponibles.isEmpty()) {
      setVehiculo(vehiculosDisponibles.get(0));
      vehiculo.serAsignado();
    }
  }

  public void setVehiculo(Vehiculo vehiculo) {
    this.vehiculo = vehiculo;
  }

  public void cambiarVehiculo(Vehiculo vehiculo) {
    this.vehiculo.marcarDisponible();
    setVehiculo(vehiculo);
    vehiculo.serAsignado();
  }

  public Vehiculo getVehiculo() {
    return vehiculo;
  }

  public Posicion getPosicion() {
    return posicion;
  }

  public void finalizarSolicitud() {
    RepoAlertas.getInstancia().removerAlerta(this);
  }
}