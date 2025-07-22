package org.qmp.sugeridores;

import java.util.List;
import org.qmp.Usuario;
import org.qmp.prendas.Atuendo;

public class SugeridorPorFormalidad extends SugeridorBasico {

  @Override
  public List<Atuendo> generarSugerencias(Usuario usuario) {
    List<Atuendo> atuendos = super.generarSugerencias(usuario);

    if (usuario.getEdad() > 55) {
      return atuendos.stream().filter(Atuendo::esFormal).toList();
    }

    return atuendos;
  }
}
