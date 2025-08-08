package org.suenos.personalidades;

import org.suenos.Usuario;

public class Realista implements Personalidad {
  @Override
  public void cumplirUnSueño(Usuario usuario) {
    usuario.sueñoDeMasValor().serCumplido();
  }
}
