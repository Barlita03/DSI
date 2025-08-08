package org.suenos.personalidades;

import org.suenos.Usuario;

public class Inconformista implements Personalidad {
  @Override
  public void cumplirUnSueño(Usuario usuario) {
    usuario.getSueños().forEach(s -> s.serCumplido(s));
  }
}
