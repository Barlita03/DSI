package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.mensajes.Mensaje;

public class Libre implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Mensaje mensaje) {
    lista.enviarTodosLosMiembros(mensaje);
  }
}
