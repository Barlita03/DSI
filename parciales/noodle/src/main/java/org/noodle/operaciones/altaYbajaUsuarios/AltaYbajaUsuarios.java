package org.noodle.operaciones.altaYbajaUsuarios;

import org.noodle.Usuario;
import org.noodle.grupos.Grupo;
import org.noodle.operaciones.Operacion;

public abstract class AltaYbajaUsuarios extends Operacion {
  protected final Usuario usuario;

  public AltaYbajaUsuarios(Grupo grupo, Usuario usuario) {
    super(grupo);
    this.usuario = usuario;
  }
}
