package org.example.listasdecorreo.privacidad;

import org.example.Mensaje;
import org.example.listasdecorreo.ListaDeCorreo;

public interface Privacidad {
  void recibirMensaje(ListaDeCorreo lista, Mensaje mensaje);
}
