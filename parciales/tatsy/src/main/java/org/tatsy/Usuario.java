package org.tatsy;

import org.tatsy.notificador.Notificador;

public class Usuario {
  private String email;
  private String telefono;
  private String username;
  private Notificador notificador;

  public String getEmail() {
    return email;
  }

  public String getTelefono() {
    return telefono;
  }

  public String getUsername() {
    return username;
  }

  public void recibirNotificacion(String mensaje) {
    notificador.notificar(this, mensaje);
  }
}
