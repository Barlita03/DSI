package org.example.listasdecorreo.modosdesuscripcion;

import org.example.Usuario;
import org.example.listasdecorreo.ListaDeCorreo;

public class Abierta implements ModoDeSuscripcion {
  @Override
  public void suscribirse(ListaDeCorreo lista, Usuario usuario) {
    lista.agregarMiembro(usuario);
  }
}
