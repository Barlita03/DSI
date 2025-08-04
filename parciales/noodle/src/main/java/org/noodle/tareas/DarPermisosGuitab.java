package org.noodle.tareas;

import org.noodle.Usuario;
import org.noodle.grupos.Grupo;

public class DarPermisosGuitab implements Tarea {
  @Override
  public void serRealizada(Grupo grupo, Usuario usuario) {
    grupo.getGuitab().darAcceso(grupo.getNombre(), usuario.getUsername());
  }
}
