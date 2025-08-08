package org.tatsy;

import org.tatsy.alertaVehiculoCercano.RepoAlertas;

public class Vehiculo {
  private Posicion posicion;
  private Usuario propietario;
  private boolean ocupado;

  public Long distanciaA(Posicion posicion) {
    return 0L; // TODO
  }

  public boolean estaDisponible() {
    return !ocupado;
  }

  public void marcarNoDisponible() {
    setOcupado(false);
  }

  public void marcarDisponible() {
    setOcupado(true);
    RepoAlertas.getInstancia().enviarAlertas(this);
  }

  public void setOcupado(boolean ocupado) {
    this.ocupado = ocupado;
  }

  public void serAsignado() {
    marcarNoDisponible();
  }

  public void notificarPropietario(String mensaje) {
    propietario.recibirNotificacion(mensaje);
  }

  public boolean estaCerca(Posicion posicion) {
    return true;
  }
}
