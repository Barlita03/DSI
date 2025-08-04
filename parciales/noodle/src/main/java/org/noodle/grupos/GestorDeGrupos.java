package org.noodle.grupos;

import java.util.ArrayList;
import java.util.List;

public class GestorDeGrupos {
  private static final GestorDeGrupos instancia = new GestorDeGrupos();
  private final List<Grupo> grupos = new ArrayList<>();

  public static GestorDeGrupos getInstancia() {
    return instancia;
  }

  public void agregarGrupo(Grupo grupo) {
    grupos.add(grupo);
  }

  public void removerGrupo(Grupo grupo) {
    grupos.remove(grupo);
  }
}
