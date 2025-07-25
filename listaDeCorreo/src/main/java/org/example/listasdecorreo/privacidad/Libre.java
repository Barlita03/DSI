package org.example.listasdecorreo.privacidad;

import org.example.Mensaje;
import org.example.listasdecorreo.ListaDeCorreo;

public class Libre implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Mensaje mensaje) {
    lista.enviarTodosLosMiembros(mensaje);
  }
}
