package org.example.usuarios;

public class ValidacionPendiente {
  private final Usuario usuario;
  private final String foto;

  public ValidacionPendiente(Usuario usuario, String foto) {
    this.usuario = usuario;
    this.foto = foto;
  }

  public void aceptarFoto() {
    usuario.aceptarFoto();
  }

  public void rechazarFoto() {
    usuario.rechazarFoto();
  }
}
