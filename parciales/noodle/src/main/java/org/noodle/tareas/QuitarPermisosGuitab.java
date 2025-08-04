package org.noodle.tareas;

import org.noodle.Usuario;
import org.noodle.grupos.Grupo;

public class QuitarPermisosGuitab implements Tarea {
  @Override
  public void serRealizada(Grupo grupo, Usuario usuario) {
    grupo.getGuitab().quitarAcceso(grupo.getNombre(), usuario.getUsername());
  }
}
