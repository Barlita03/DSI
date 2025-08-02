package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.MensajeModerado;
import org.example.mensajes.Borrador;

public class ModeracionNuevosMiembros implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    if(lista.getNuevosUsuarios().contains(borrador.getOrigen())) {
      lista.agregarMensajeAEspera(new MensajeModerado(lista, borrador));
    } else {
      lista.recibirMensaje(borrador);
    }
  }
}
