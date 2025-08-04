package org.noodle.operaciones.altaYbajaUsuarios;

import org.noodle.Usuario;
import org.noodle.grupos.Grupo;
import org.noodle.operaciones.Operacion;

public class AgregarUsuario extends AltaYbajaUsuarios {

  public AgregarUsuario(Grupo grupo, Usuario usuario) {
    super(grupo, usuario);
  }

  @Override
  public void serAceptada() {
    grupo.agregarIntegrante(usuario);
  }
}
