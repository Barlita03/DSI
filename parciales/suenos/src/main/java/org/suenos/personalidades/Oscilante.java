package org.suenos.personalidades;

import org.suenos.Usuario;

import java.util.List;

public class Oscilante implements  Personalidad {
  private List<Personalidad> personalidades;

  @Override
  public void cumplirUnSueño(Usuario usuario) {
    personalidades.pickAny().cumplirUnSueño(usuario);
  }
}
