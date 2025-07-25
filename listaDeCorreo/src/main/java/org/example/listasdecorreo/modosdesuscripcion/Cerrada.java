package org.example.listasdecorreo.modosdesuscripcion;

import org.example.PedidoDeSuscripcion;
import org.example.Usuario;
import org.example.listasdecorreo.ListaDeCorreo;

public class Cerrada implements ModoDeSuscripcion {
  @Override
  public void suscribirse(ListaDeCorreo lista, Usuario usuario) {
    lista.agregarEspera(new PedidoDeSuscripcion(lista, usuario));
  }
}
