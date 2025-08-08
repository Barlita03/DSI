package org.suenos.personalidades;

import org.suenos.Usuario;

public class Alocado implements Personalidad {
  @Override
  public void cumplirUnSueño(Usuario usuario) {
    usuario.getSueños().pickAny().serCumplido();
  }
}