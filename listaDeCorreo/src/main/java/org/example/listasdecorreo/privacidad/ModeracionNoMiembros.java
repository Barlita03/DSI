package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.MensajeModerado;
import org.example.mensajes.Borrador;

public class ModeracionNoMiembros extends Moderada {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    if (!lista.getMiembros().contains(borrador.getOrigen())) {
      lista.agregarMensajeAEspera(new MensajeModerado(lista, borrador));
      super.recibirMensaje(lista, borrador);
    } else {
      lista.recibirMensaje(borrador);
    }
  }
}
