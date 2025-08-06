package org.firmadocs.acciones;

import org.firmadocs.procesos.Proceso;
import org.firmadocs.usuarios.Usuario;

public abstract class Accion {
  protected final Usuario usuario;
  protected final Proceso proceso;
  private EstadoAccion estado = EstadoAccion.PENDIENTE;

  public Accion(Usuario usuario, Proceso proceso) {
    this.usuario = usuario;
    this.proceso = proceso;
  }

  public void serRealizada() {
    marcarRealizada();
    proceso.recibirAccionCompleta();
  }

  public void serEnviadaAUsuario() {
    usuario.agregarAccion(this);
    usuario.recibirNotificacion("Tenes una nueva accion pendiente");
  }

  public boolean estaRealizada() {
    return estado.equals(EstadoAccion.COMPLETADA);
  }

  private void marcarRealizada() {
    setEstado(EstadoAccion.COMPLETADA);
  }

  public Proceso getProceso() {
    return proceso;
  }

  public void setEstado(EstadoAccion estado) {
    this.estado = estado;
  }

  public Usuario getUsuario() {
    return usuario;
  }
}
