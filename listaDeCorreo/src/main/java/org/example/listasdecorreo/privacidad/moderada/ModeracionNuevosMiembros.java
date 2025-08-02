package org.example.listasdecorreo.privacidad.moderada;

import org.example.listasdecorreo.ListaDeCorreo;
import org.example.listasdecorreo.MensajeModerado;
import org.example.mensajes.Borrador;

public class ModeracionNuevosMiembros extends Moderada {
  @Override
  public void aplicar(ListaDeCorreo lista, Borrador borrador) {
    if (lista.getNuevosUsuarios().contains(borrador.getOrigen())) {
      lista.agregarMensajeEspera(new MensajeModerado(lista, borrador));
      notificarAdministradores(lista, borrador.getOrigen().getEmailPrincipal());
    } else {
      lista.enviarTodosLosMiembros(borrador);
    }
  }
}
