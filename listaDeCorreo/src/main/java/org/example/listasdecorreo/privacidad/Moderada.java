package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.MensajeModerado;
import org.example.mensajes.Borrador;

public abstract class Moderada implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    if (!lista.getMensajesEnEspera().stream()
        .map(MensajeModerado::getBorrador)
        .toList()
        .contains(borrador)) {
      aplicar(lista, borrador);
    }
  }

  protected void notificarAdministradores(ListaDeCorreo lista, String direccionUsuario) {
    lista.enviarTodosLosAdministradores(
        new Borrador(
            lista.getAdministradores().get(0),
            "Nuevo mensaje pendiente de moderacion",
            "El usuario "
                + direccionUsuario
                + "desea enviar un mensaje a la lista "
                + lista.getDireccion()));
  }

  protected abstract void aplicar(ListaDeCorreo lista, Borrador borrador);
}
