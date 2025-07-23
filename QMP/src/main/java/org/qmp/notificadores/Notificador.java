package org.qmp.notificadores;

import java.util.ArrayList;
import java.util.List;
import org.qmp.usuarios.Usuario;

public abstract class Notificador {
  private final List<Usuario> usuarios = new ArrayList<>();
  protected String mensaje;

  public Notificador(String mensaje) {
    this.mensaje = mensaje;
  }

  public void suscribirse(Usuario usuario) {
    usuarios.add(usuario);
  }

  public void desuscribirse(Usuario usuario) {
    usuarios.remove(usuario);
  }

  public void notificar() {
    usuarios.forEach(this::ejecutarNotificacion);
  }

  public abstract void ejecutarNotificacion(Usuario usuario);
}
