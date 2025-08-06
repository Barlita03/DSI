package org.firmadocs;

import org.firmadocs.usuarios.RepoUsuarios;

// Se ejecuta con un Cron
public class RecordarAcciones {
  public static void main(String[] args) {
    RepoUsuarios.getInstancia().recordarAcciones();
  }
}
