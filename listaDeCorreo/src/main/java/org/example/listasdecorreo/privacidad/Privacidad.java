package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.mensajes.Borrador;

public interface Privacidad {
  void recibirMensaje(ListaDeCorreo lista, Borrador borrador);
}
