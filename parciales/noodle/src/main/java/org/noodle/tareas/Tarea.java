package org.noodle.tareas;

import org.noodle.Usuario;
import org.noodle.grupos.Grupo;

public interface Tarea {
  void serRealizada(Grupo grupo, Usuario usuario);
}
