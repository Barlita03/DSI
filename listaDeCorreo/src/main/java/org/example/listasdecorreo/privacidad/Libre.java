package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.mensajes.Borrador;

public class Libre implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    lista.enviarTodosLosMiembros(borrador);
  }
}
