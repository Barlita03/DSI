package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.mensajes.Borrador;

public class Privada implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    if (lista.esMiembro(borrador.getOrigen())) {
      lista.enviarTodosLosMiembros(borrador);
    } else {
      throw new RuntimeException("La lista a la que intentas enviar el mensaje es privada");
    }
  }
}
