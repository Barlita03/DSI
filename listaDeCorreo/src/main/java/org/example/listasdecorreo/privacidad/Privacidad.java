package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.mensajes.Mensaje;

public interface Privacidad {
  void recibirMensaje(ListaDeCorreo lista, Mensaje mensaje);
}
