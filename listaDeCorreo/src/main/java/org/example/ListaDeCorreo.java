package org.example;

import java.util.ArrayList;
import java.util.List;

public class ListaDeCorreo {
  private final List<Usuario> miembros = new ArrayList<>();
  private final List<Mensaje> correos = new ArrayList<>();
  private boolean privacidad; // True = privado, False = libre
  private boolean suscripcion; // True = cerrada, False = abierta

  // --- Constructor ---

  public ListaDeCorreo(List<Usuario> miembros, boolean privacidad) {
    this.miembros.addAll(miembros);
    this.privacidad = privacidad;
  }

  // --- Metodos ---

  public void suscribirse(Usuario usuario) {
    if (suscripcion) {
      new PedidoDeSuscripcion(this, usuario);
    } else {
      agregarMiembro(usuario);
    }
  }

  public void agregarMiembro(Usuario usuario) {
    miembros.add(usuario);
  }

  public void quitarMiembro(Usuario usuario) {
    miembros.remove(usuario);
  }

  public void recibirMensaje(Mensaje mensaje) {
    if (privacidad) {
      if (esMiembro(mensaje.getOrigen())) {
        correos.add(mensaje);
      }

      throw new RuntimeException("La lista a la que intentas enviar el mensaje es privada");
    }

    correos.add(mensaje);
  }

  private boolean esMiembro(Usuario usuario) {
    return miembros.contains(usuario);
  }

  public void cambiarPrivacidad(boolean privacidad) {
    this.privacidad = privacidad;
  }

  public void cambiarSuscripcion(boolean suscripcion) {
    this.suscripcion = suscripcion;
  }
}
