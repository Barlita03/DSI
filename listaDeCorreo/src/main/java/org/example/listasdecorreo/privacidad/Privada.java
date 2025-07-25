package org.example.listasdecorreo.privacidad;

import org.example.Mensaje;
import org.example.listasdecorreo.ListaDeCorreo;

public class Privada implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Mensaje mensaje) {
    if (lista.esMiembro(mensaje.getOrigen())) {
      lista.enviarTodosLosMiembros(mensaje);
    } else {
      throw new RuntimeException("La lista a la que intentas enviar el mensaje es privada");
    }
  }
}
