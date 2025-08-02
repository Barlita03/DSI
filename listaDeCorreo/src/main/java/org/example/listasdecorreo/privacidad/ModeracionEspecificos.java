package org.example.listasdecorreo.privacidad;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.MensajeModerado;
import org.example.mensajes.Borrador;

public class ModeracionEspecificos implements Privacidad {
  @Override
  public void recibirMensaje(ListaDeCorreo lista, Borrador borrador) {
    if(lista.getUsuariosModerados().contains(borrador.getOrigen())) {
      lista.agregarMensajeAEspera(new MensajeModerado(lista, borrador));
    } else {
      lista.enviarTodosLosMiembros(borrador);
    }
  }
}
