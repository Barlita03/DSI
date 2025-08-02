package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.mensajes.Borrador;

public abstract class Moderada implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    notificarAdministradores(lista, borrador.getOrigen().getEmailPrincipal());
  }

  protected void notificarAdministradores(ListaDeCorreo lista, String direccionUsuario) {
    lista.enviarTodosLosAdministradores(
        new Borrador(
            lista.getAdministradores().get(0), "Nuevo mensaje pendiente de moderacion",
            "El usuario " + direccionUsuario + "desea enviar un mensaje a la lista " + lista.getDireccion()));
  }
}