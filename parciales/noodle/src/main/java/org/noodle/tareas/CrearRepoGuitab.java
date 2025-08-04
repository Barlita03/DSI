package org.noodle.tareas;

import org.noodle.Usuario;
import org.noodle.grupos.Grupo;

public class CrearRepoGuitab implements Tarea{
  @Override
  public void serRealizada(Grupo grupo, Usuario usuario) {
    grupo.getGuitab().crearRepositorioConAccesos(grupo.getNombre(), grupo.getIntegrantes().stream().map(Usuario::getUsername).toList());
  }
}
