package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.MensajeModerado;
import org.example.mensajes.Borrador;

public class ModeracionNuevosMiembros extends Moderada {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    if (lista.getNuevosUsuarios().contains(borrador.getOrigen())) {
      lista.agregarMensajeAEspera(new MensajeModerado(lista, borrador));
      super.recibirMensaje(lista, borrador);
    } else {
      lista.recibirMensaje(borrador);
    }
  }
}
