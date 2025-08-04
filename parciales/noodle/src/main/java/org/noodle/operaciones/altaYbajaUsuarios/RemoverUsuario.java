package org.noodle.operaciones.altaYbajaUsuarios;

import org.noodle.Usuario;
import org.noodle.grupos.Grupo;
import org.noodle.operaciones.Operacion;

public class RemoverUsuario extends AltaYbajaUsuarios {

  public RemoverUsuario(Grupo grupo, Usuario usuario) {
    super(grupo, usuario);
  }

  @Override
  public void serAceptada() {
    grupo.removerIntegrante(usuario);
  }
}
