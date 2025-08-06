package org.firmadocs.usuarios;

import java.util.ArrayList;
import java.util.List;

public class RepoUsuarios {
  private static final RepoUsuarios instancia = new RepoUsuarios();
  private static final List<Usuario> usuarios = new ArrayList<>();

  public static RepoUsuarios getInstancia() {
    return instancia;
  }

  public static List<Usuario> getUsuarios() {
    return usuarios;
  }

  public void recordarAcciones() {
    usuarios.stream().filter(Usuario::tieneAccionesPendientes).forEach(u -> u.recibirNotificacion("Tenes acciones pendientes"));
  }
}
